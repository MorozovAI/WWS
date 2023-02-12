package com.morozov.warrantywebsystem.repository;

import com.morozov.warrantywebsystem.model.Claim;
import com.morozov.warrantywebsystem.model.ClaimStatus;
import com.morozov.warrantywebsystem.model.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRepository extends BaseRepository<Claim> {

    Page<Claim> findClaimsByStatusAndDealer(ClaimStatus status, Dealer dealer,
                                            Pageable pageable);

    Page<Claim> findClaimsByStatus(ClaimStatus status,
                                   Pageable pageable);

    Optional<Claim> findClaimByIdAndDealer(int id, Dealer dealer);

    boolean deleteClaimByIdAndDealer(int id, Dealer dealer);
}