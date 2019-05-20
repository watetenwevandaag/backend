package com.wewv.controllers;

import com.wewv.models.Cook;
import com.wewv.models.Ingredient;
import com.wewv.models.Recipe;
import com.wewv.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping(value = "/")
    public ResponseEntity<Recipe> getById(@RequestParam(name = "id", required = true) int id){
        Recipe recipe = recipeService.getById(id);
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Recipe>> getAll(){
        List<Recipe> recipes = recipeService.getAll();
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<List<Recipe>> tests(){
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("test");
        ingredient1.setQuantity(12);
        ingredient1.setUnit("Lepels");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Kip");
        ingredient2.setQuantity(1);
        ingredient2.setUnit("KG");
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setName("Chocolade");
        ingredient3.setQuantity(12);
        ingredient3.setUnit("Gram");
        Recipe recipe = new Recipe();
        recipe.setName("Mooie Spaghetti");
        recipe.setLikes(123);
        recipe.setForNumberOfPeople(1);
        recipe.setIsVegan(false);
        List<String> stringList = new ArrayList<>();
        stringList.add("gerie");
        stringList.add("beker");
        recipe.setEquipmentUsed(stringList);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredient1.setRecipe(recipe);
        ingredient2.setRecipe(recipe);
        ingredient3.setRecipe(recipe);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        recipe.setIngredients(ingredients);
        recipe.setOwner(new Cook());
        recipeService.create(recipe);
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }
}
