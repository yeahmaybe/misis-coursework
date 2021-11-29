package com.misis.coursework.service;

import com.misis.coursework.entity.UserEntity;
import com.misis.coursework.exceptions.NullParameterException;
import com.misis.coursework.exceptions.UserAlreadyExistsException;
import com.misis.coursework.exceptions.UserNotFoundException;
import com.misis.coursework.model.User;
import com.misis.coursework.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity registerUser(UserEntity user) throws UserAlreadyExistsException, NullParameterException {
        if(user.getUsername() == null) {
            throw new NullParameterException("Необходимо имя пользователя");
        }
        if(user.getPassword() == null) {
            throw new NullParameterException("Необходим пароль");
        }
        if(userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует.");
        }
        return userRepo.save(user);
    }

    public User getUser(Long id) throws UserNotFoundException {
        try {
            return User.toModel(userRepo.findById(id).get());
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Пользователь не найден");
        }
    }

    public Long deleteById(Long id) throws UserNotFoundException {
        try {
            userRepo.deleteById(id);
            return id;
        } catch(NoSuchElementException ex) {
            throw new UserNotFoundException("Пользователь не найден");
        }
    }

}
