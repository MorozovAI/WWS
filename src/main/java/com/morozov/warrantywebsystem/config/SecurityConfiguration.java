package com.morozov.warrantywebsystem.config;

import com.morozov.warrantywebsystem.model.Role;
import com.morozov.warrantywebsystem.model.User;
import com.morozov.warrantywebsystem.repository.UserRepository;
import com.morozov.warrantywebsystem.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
public class SecurityConfiguration {

    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PASSWORD_ENCODER;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            log.debug("Authenticating '{}'", email);
            Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
            User user = optionalUser.orElseThrow(
                    () -> new UsernameNotFoundException("User '" + email + "' was not found"));
            if (!user.isEnabled()) throw new UsernameNotFoundException("User '" + email + "' is disabled");
            return new AuthUser(user);
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringAntMatchers("/rest/**")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/users", "/dealers").hasRole(Role.ADMIN.name())
                .antMatchers("/rest/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/rest/adviser/**").hasRole(Role.ADVISER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/login")
                .and().httpBasic()
                .and()
                .logout()
                .permitAll();
        return http.build();
    }
}