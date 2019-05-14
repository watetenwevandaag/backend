package com.wewv.controllers;

import com.google.gson.Gson;
import com.wewv.dal.RecipeSpringRepository;
import com.wewv.models.Ingredient;
import com.wewv.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/test")
public class TestControllers {

    @Autowired
    private RecipeSpringRepository recipeRepository;

    private Gson gson = new Gson();

    @RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping(value = "/tests")
    public ResponseEntity<Ingredient> tests(){
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
        recipe.setName("TEst");
        recipe.setLikes(123);
        recipe.setForNumberOfPeople(1);
        recipe.setIsVegan(false);
        recipe.setEquipmentUsed(null);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredient1.setRecipe(recipe);
        ingredient2.setRecipe(recipe);
        ingredient3.setRecipe(recipe);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        recipe.setIngredients(ingredients);
        recipeRepository.save(recipe);
        return new ResponseEntity<Ingredient>(ingredient1, HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Recipe> lists(){
        List<Recipe> recipeList = recipeRepository.findAll();
        Recipe recipe = recipeList.get(0);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(recipe.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(recipe);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestParam(name = "firstName", required = true) String firstName, @RequestParam(name = "lastName", required = true) String lastName, @RequestParam(name = "username", required = true) String username, @RequestParam(name = "password", required = true) String password){
        return new ResponseEntity<String>("First name: " + firstName + ", last name: " + lastName + ", username: " + username + ", password : " + password, HttpStatus.OK);
    }

    @RequestMapping(value="test", method =  RequestMethod.GET)
    public ResponseEntity test(){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
