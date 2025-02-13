package com.blog.controller;

import com.blog.model.Blog;
import com.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BlogRestController {
    private IBlogService blogService;

    @Autowired
    public BlogRestController(IBlogService blogService) {
        this.blogService = blogService;
    }
    @GetMapping("/list")
    public List<Blog> get(){
        return blogService.getAll();
    }
    @GetMapping("/list/{id}")
    public Blog getById(@PathVariable int id){
        return blogService.getById(id);
    }
    @PostMapping("/add")
    public void add(@RequestBody Blog blog){
        blogService.add(blog);
    }
    @PostMapping("/update")
    public void update(@RequestBody Blog blog){
        blogService.update(blog);
    }
    @PostMapping("/delete")
    public void delete(@RequestBody Blog blog){
        blogService.delete(blog);
    }
}
