package com.blog.controller;

import com.blog.model.Blog;
import com.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogController {
    private IBlogService blogService;

    @Autowired
    public BlogController(IBlogService blogService) {
        this.blogService = blogService;
    }
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/blogs")
    public String listBlogs(Model model) {
        List<Blog> blogs = blogService.getAll();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }
}
