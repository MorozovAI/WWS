package com.morozov.warrantywebsystem.repository;

import com.morozov.warrantywebsystem.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DealerRepository extends JpaRepository<Dealer, Integer> {

    Dealer getByDealerCode(Integer dealerCode);

}