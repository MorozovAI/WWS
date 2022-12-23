package com.morozov.warrantywebsystem.repository;

import com.morozov.warrantywebsystem.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {
}