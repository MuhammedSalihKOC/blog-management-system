package com.blog.service;

import com.blog.model.User;
import com.blog.repository.IUserDal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserManager implements IUserService{

    IUserDal userDal;

    @Autowired
    public UserManager(IUserDal userDal) {
        this.userDal = userDal;
    }

    @Override
    public String register(User user) {
        Optional<User> existingUsername = userDal.findByUsername(user.getUsername());
        Optional<User> existingEmail = userDal.findByEmail(user.getEmail());
        if (existingUsername.isPresent() || existingEmail.isPresent()) {
            return "Kullanıcı adı veya Email zaten alınmış.";
        }
        userDal.save(user);
        return "Kayıt başarılı! Lütfen giriş yapın.";
    }
    @Override
    public boolean login(String username, String password) {
        Optional<User> user = userDal.findByUsername(username);
        return (user.isPresent() && user.get().getPassword().equals(password));
    }

}
