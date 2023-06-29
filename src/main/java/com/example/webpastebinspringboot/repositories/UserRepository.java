package com.example.webpastebinspringboot.repositories;

import com.example.webpastebinspringboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
