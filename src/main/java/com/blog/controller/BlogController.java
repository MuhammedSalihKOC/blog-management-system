package com.blog.controller;

import com.blog.model.Blog;
import com.blog.model.User;
import com.blog.service.IBlogService;
import com.blog.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogController {
    private IBlogService blogService;
    private IUserService userService;

    @Autowired
    public BlogController(IBlogService blogService, IUserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Blog> blogs = blogService.getAll();
        model.addAttribute("blogs", blogs);
        return "main";
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
        model.addAttribute("blog", blog);
        return "blog";
    }
    @GetMapping("blogs/add")
    public String blogAdder(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("blog", new Blog());
        return "blog-add";
    }
    @PostMapping("/blogs/add/succeeded")
    public String addBlog(@ModelAttribute Blog blog, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/user/login";
        }
        blog.setAuthor((User)session.getAttribute("loggedInUser"));
        blog.setCreatedAt(LocalDateTime.now());
        blogService.add(blog);
        return "redirect:/blogs";
    }
    @PostMapping("/blogs/update/{id}")
    public String updateBlog(@PathVariable int id, @ModelAttribute Blog blog, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/user/login";
        }
        Blog existingBlog = blogService.getById(id);
        blog.setAuthor(existingBlog.getAuthor());
        blog.setCreatedAt(LocalDateTime.now());
        blogService.update(blog);
        return "redirect:/blogs/" + id ;
    }
    @GetMapping("/blogs/edit/{id}")
    public String editBlog(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/user/login";
        }
        Blog blog = blogService.getById(id);
        model.addAttribute("blog", blog);
        return "blog-edit";
    }
    @GetMapping("/blogs/delete/{id}")
    public String showDeleteConfirmPage(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/user/login";
        }
        Blog blog = blogService.getById(id);
        model.addAttribute("blog", blog);
        return "blog-delete";
    }
    @GetMapping("/blogs/deleted/{id}")
    public String deleteBlog(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/user/login";
        }
        Blog deleteToBlog = blogService.getById(id);
        blogService.delete(deleteToBlog);
        return "redirect:/blogs";
    }
    @GetMapping("/blogs/search")
    public String searchBlogs(@RequestParam("keyword") String keyword, Model model){
        List<Blog> searchResults = blogService.searchBlogs(keyword);
        model.addAttribute("blogs", searchResults);
        model.addAttribute("keyword", keyword);
        return "blogs";
    }
}
