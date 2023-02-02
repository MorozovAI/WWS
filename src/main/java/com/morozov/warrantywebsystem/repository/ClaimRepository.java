package com.morozov.warrantywebsystem.repository;

import com.morozov.warrantywebsystem.model.Claim;
import com.morozov.warrantywebsystem.model.ClaimStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends PagingAndSortingRepository<Claim, Integer> {


    Page<Claim> findClaimsByStatus(ClaimStatus status,
                                   Pageable pageable);
}