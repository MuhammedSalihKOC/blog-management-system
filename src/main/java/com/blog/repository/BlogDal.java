package com.blog.repository;

import com.blog.model.Blog;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogDal implements IBlogDal{
    private EntityManager entityManager;

    @Autowired
    public BlogDal(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Blog> getAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Blog> blogs = session.createQuery("from Blog", Blog.class).getResultList();
        return blogs;
    }

    @Override
    @Transactional
    public void add(Blog blog) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(blog);
    }

    @Override
    @Transactional
    public void update(Blog blog) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(blog);
    }

    @Override
    @Transactional
    public void delete(Blog blog) {
        Session session = entityManager.unwrap(Session.class);
        Blog blogToDelete = session.get(Blog.class, blog.getId());
        session.remove(blogToDelete);
    }

    @Override
    @Transactional
    public Blog getById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Blog blog = session.get(Blog.class, id);
        return blog;
    }
}
