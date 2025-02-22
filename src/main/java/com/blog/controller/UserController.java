package com.blog.controller;

import com.blog.model.User;
import com.blog.service.IUserService;
import com.blog.service.UserManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) throws InterruptedException {
        String message = userService.register(user);
        model.addAttribute("message", message);
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        boolean success = userService.login(username, password);
        if (success) {
            session.setAttribute("loggedInUser", username);
            return "redirect:/blogs";
        } else {
            model.addAttribute("error", "Geçersiz kullanıcı adı veya şifre.");
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}



