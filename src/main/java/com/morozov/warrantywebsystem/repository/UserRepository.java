package com.morozov.warrantywebsystem.repository;

import com.morozov.warrantywebsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
