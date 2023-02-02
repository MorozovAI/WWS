package com.morozov.warrantywebsystem;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.morozov.warrantywebsystem.*"})
@EntityScan("com.morozov.warrantywebsystem.model")
@EnableJpaRepositories(basePackages = "com.morozov.warrantywebsystem.repository")
public class WarrantyWebSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarrantyWebSystemApplication.class, args);
    }
}
