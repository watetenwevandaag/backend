package com.wewv.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wewv.models.Cook;
import com.wewv.models.Ingredient;
import com.wewv.models.Recipe;
import com.wewv.service.CookService;
import com.wewv.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/recipe")
@CrossOrigin("http://localhost:4200")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    CookService cookService;

    @GetMapping(value = "/")
    public ResponseEntity<Recipe> getById(@RequestParam(name = "id", required = true) int id){
        Recipe recipe = recipeService.getById(id);
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Recipe>> getAll(){
        List<Recipe> recipes = recipeService.getAll();
        tests();
        tests();
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    @GetMapping(value = "/byName/{name}")
    public ResponseEntity<List<Recipe>> getByName(@PathVariable String name){
        System.out.println(name);
        List<Recipe> recipes = recipeService.getByName(name);
        return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
    }

    @GetMapping(value = "/weekmenu")
    public ResponseEntity<List<Recipe>> getWeekMenu(){
        List<Recipe> recipes = recipeService.getAll();
        List<Recipe> recipeList = new ArrayList<>();
        if(recipes.size() < 8){
            recipeList = null;
        }else{
            Random rnd = new Random();
            for (int i = 0; i < 7; i++) {
                recipeList.add(recipes.get(rnd.nextInt(recipes.size())));
            }
        }

        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Recipe> create(@RequestBody Recipe recipeToSave){
        cookService.getById(recipeToSave.getOwner().getId()).getOwnRecipes().add(recipeToSave);
        recipeToSave.setOwner(cookService.getById(recipeToSave.getOwner().getId()));
        for (Ingredient i :recipeToSave.getIngredients()) {
            i.setRecipe(recipeToSave);
        }
        recipeService.create(recipeToSave);
        return new ResponseEntity<>(recipeToSave, HttpStatus.OK);
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
