package com.blog.service;

import com.blog.model.Blog;
import com.blog.repository.IBlogDal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogManager implements IBlogService{
    IBlogDal blogDal;

    @Autowired
    public BlogManager(IBlogDal blogDal) {
        this.blogDal = blogDal;
    }

    @Override
    @Transactional
    public List<Blog> getAll() {
        return this.blogDal.findAll();
    }

    @Override
    @Transactional
    public void add(Blog blog) {
        this.blogDal.save(blog);
    }

    @Override
    @Transactional
    public void update(Blog blog) {
        this.blogDal.save(blog);
    }

    @Override
    @Transactional
    public void delete(Blog blog) {
        this.blogDal.delete(blog);
    }

    @Override
    @Transactional
    public Blog getById(int id) {
        return blogDal.findById((long) id).orElse(null);    }

    @Override
    public List<Blog> searchBlogs(String keyword) {
        return this.blogDal.findByTitleContainingIgnoreCase(keyword);
    }
}
