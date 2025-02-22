package com.blog.service;

import com.blog.model.User;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public interface IUserService {
    public String register(User user);
    public boolean login(String username, String password);
}
