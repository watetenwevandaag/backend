package com.wewv.service;

import com.wewv.dal.UserRepository;
import com.wewv.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create (User user){
        userRepository.save(user);
        return user;
    }

    public boolean delete(int userId){
        userRepository.deleteById(userId);
        return true;
    }

    public User getById(int recipeId){
        return userRepository.getOne(recipeId);
    }

    public List<User> getAll(){
        return  userRepository.findAll();
    }

    public User getByUsername(String username){
        return userRepository.getByUsername(username);
    }


}
