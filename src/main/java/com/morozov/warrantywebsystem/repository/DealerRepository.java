package com.morozov.warrantywebsystem.repository;

import com.morozov.warrantywebsystem.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerRepository extends BaseRepository<Dealer> {

    Dealer getByDealerCode(Integer dealerCode);

}