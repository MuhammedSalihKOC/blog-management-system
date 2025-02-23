package com.blog.service;

import com.blog.model.User;
import com.blog.repository.IUserDal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserManager implements IUserService{

    IUserDal userDal;

    @Autowired
    public UserManager(IUserDal userDal) {
        this.userDal = userDal;
    }

    @Override
    public String register(User user) {
        User existingUsername = userDal.findByUsername(user.getUsername());
        User existingEmail = userDal.findByEmail(user.getEmail());
        if (existingUsername != null || existingEmail != null) {
            return "Kullanıcı adı veya Email zaten alınmış.";
        }
        userDal.save(user);
        return "Kayıt başarılı! Lütfen giriş yapın.";
    }
    @Override
    public User login(String username, String password) {
        User user = userDal.findByUsername(username);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userDal.findByUsername(username);
    }

}
