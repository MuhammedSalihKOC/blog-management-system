package com.blog.controller;

import com.blog.model.Blog;
import com.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @GetMapping("/blogs/{id}")
    public String listBlogs(@PathVariable int id, Model model) {
        Blog blog = blogService.getById(id);
        model.addAttribute("blogs", blog);
        return "blogs";
    }
    @GetMapping("blogs/add")
    public String blogAdder(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog-add";
    }
    @PostMapping("/blogs/add/succeeded")
    public String addBlog(@ModelAttribute Blog blog) {
        blog.setAuthorId(1L);
        blog.setCreatedAt(LocalDateTime.now());
        blogService.add(blog);
        return "redirect:/blogs";
    }
    @PostMapping("/blogs/update/{id}")
    public String updateBlog(@PathVariable int id, @ModelAttribute Blog blog) {
        Blog existingBlog = blogService.getById(id);
        blog.setAuthorId(existingBlog.getAuthorId());
        blog.setCreatedAt(LocalDateTime.now());
        blogService.update(blog);
        return "redirect:/blogs";
    }
    @GetMapping("/blogs/edit/{id}")
    public String editBlog(@PathVariable int id, Model model) {
        Blog blog = blogService.getById(id);
        model.addAttribute("blog", blog);
        return "blog-edit";
    }
}
