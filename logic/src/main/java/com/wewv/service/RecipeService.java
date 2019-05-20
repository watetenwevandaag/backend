package com.wewv.service;

import com.wewv.dal.RecipeSpringRepository;
import com.wewv.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeSpringRepository recipeRepository;

    public Recipe create (Recipe recipe){
        recipeRepository.save(recipe);
        return recipe;
    }

    public boolean delete(int recipeID){
        recipeRepository.deleteById(recipeID);
        return true;
    }

    public Recipe getById(int recipeId){
        return recipeRepository.getOne(recipeId);
    }

    public List<Recipe> getAll(){
        return  recipeRepository.findAll();
    }

}
