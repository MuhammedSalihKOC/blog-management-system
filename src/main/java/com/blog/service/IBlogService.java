package com.blog.service;

import com.blog.model.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> getAll();
    void add(Blog blog);
    void update(Blog blog);
    void delete(Blog blog);
    Blog getById(int id);

    List<Blog> searchBlogs(String keyword);

}
