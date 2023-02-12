package com.morozov.warrantywebsystem.web.dealer;

import com.morozov.warrantywebsystem.model.Dealer;
import com.morozov.warrantywebsystem.repository.DealerRepository;
import com.morozov.warrantywebsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@Slf4j
public abstract class AbstractDealerController {

    @Autowired
    protected DealerRepository dealerRepository;

    @Autowired
    protected UserRepository userRepository;


    public ResponseEntity<Dealer> get(int id) {
        log.info("get user {}", id);
        return ResponseEntity.of(dealerRepository.findById(id));
    }


}