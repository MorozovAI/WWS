package com.morozov.warrantywebsystem.repository;

import com.morozov.warrantywebsystem.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByEmailIgnoreCase(String email);
}
