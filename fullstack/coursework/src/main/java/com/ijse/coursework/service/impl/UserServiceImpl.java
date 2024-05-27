package com.ijse.coursework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.coursework.entity.User;
import com.ijse.coursework.repository.UserRepository;
import com.ijse.coursework.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired 
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existUser  =  userRepository.findById(id).orElse(user);
        if(existUser!=null){
            existUser.setUsername(user.getUsername());
            existUser.setPassword(user.getPassword());
            existUser.setEmail(user.getEmail());
            existUser.setAddress(user.getAddress());
            existUser.setRole(user.getRole());
            return userRepository.save(existUser);
        }else{
            return null;
        }

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
