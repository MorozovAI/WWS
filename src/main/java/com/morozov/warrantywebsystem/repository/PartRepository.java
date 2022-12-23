package com.morozov.warrantywebsystem.repository;

import com.morozov.warrantywebsystem.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PartRepository extends JpaRepository<Part, Integer> {
    @Query("SELECT p FROM Part p WHERE p.id = ?1")
    Part getPartById(int id);
}