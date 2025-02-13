package com.blog.repository;

import com.blog.model.Blog;

import java.util.List;

public interface IBlogDal {
    List<Blog> getAll();
    void add(Blog blog);
    void update(Blog blog);
    void delete(Blog blog);
    Blog getById(int id);
}
