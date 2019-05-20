package com.wewv.service;

import com.wewv.dal.CookRepository;
import com.wewv.models.Cook;
import com.wewv.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookService {

    @Autowired
    CookRepository cookRepository;

    public Cook create (Cook cook){
        cookRepository.save(cook);
        return cook;
    }

    public boolean delete(int cookId){
        cookRepository.deleteById(cookId);
        return true;
    }

    public Cook getById(int cookId){
        return cookRepository.getOne(cookId);
    }

    public List<Cook> getAll(){
        return  cookRepository.findAll();
    }

    public Cook getByUsername(String username){
        return cookRepository.getByUsername(username);
    }
}
