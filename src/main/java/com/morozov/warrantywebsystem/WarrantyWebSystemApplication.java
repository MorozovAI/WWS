package com.morozov.warrantywebsystem;

import com.morozov.warrantywebsystem.model.*;
import com.morozov.warrantywebsystem.repository.ClaimRepository;
import com.morozov.warrantywebsystem.repository.DealerRepository;
import com.morozov.warrantywebsystem.repository.PartRepository;
import com.morozov.warrantywebsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@AllArgsConstructor
@ComponentScan(basePackages = {"com.morozov.warrantywebsystem.model"})
@EnableJpaRepositories(basePackages = "com.morozov.warrantywebsystem.repository")
public class WarrantyWebSystemApplication implements ApplicationRunner {
    private final UserRepository userRepository;
    private final DealerRepository dealerRepository;
    private final ClaimRepository claimRepository;
    private final PartRepository partRepository;


    public static void main(String[] args) {
        SpringApplication.run(WarrantyWebSystemApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

/*        Map<Part, Integer> parts = new HashMap<>();
        parts.put(partRepository.getPartById(100), 1);
        parts.put(partRepository.getPartById(1050), 1);
        dealerRepository.save(new Dealer("New dealer1", 25482));
        System.out.println(dealerRepository.getByDealerCode(92320));
        Claim claim = new Claim(dealerRepository.getByDealerCode(92320),
                "Kamaz", "ro2346", "86523695", 10098, MileageType.KM, ApplicationType.AUTOMOTIVE,
                "ISB6.7", LocalDate.of(2022, 7, 25),
                LocalDateTime.now(), null, null, null,
                parts, 0d, 0d, userRepository.getReferenceById(1), null, "Faulty of alternator", null);

        claimRepository.save(claim);


        System.out.println(userRepository.findAll());
        System.out.println(claimRepository.findAll());*/
    }
}
