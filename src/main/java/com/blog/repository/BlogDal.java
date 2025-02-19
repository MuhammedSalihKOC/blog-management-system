package com.blog.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogDal {
    private EntityManager entityManager;

    @Autowired
    public BlogDal(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

