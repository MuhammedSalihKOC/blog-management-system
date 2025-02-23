package com.blog.repository;

import com.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDal extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
