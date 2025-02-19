package com.blog.repository;

import com.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBlogDal extends JpaRepository<Blog, Long> {
    List<Blog> findAll();
    Blog save(Blog blog);
    void delete(Blog blog);
    List<Blog> findByTitleContainingIgnoreCase(String keyword);
}
