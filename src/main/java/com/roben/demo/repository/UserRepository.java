package com.roben.demo.repository;

import com.roben.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User>findUserByDocument(String document);

    Optional<User>findUserByID(Long id);
}