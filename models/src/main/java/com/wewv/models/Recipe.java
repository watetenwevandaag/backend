package com.wewv.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity(name = "recipe")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
    @ElementCollection
    private List<String> equipmentUsed;
    private String image;
    private String description;
    private Boolean isVegan;
    private int likes;
    private int dislikes;
    private int forNumberOfPeople;
    private Time cookingTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Cook owner;

    public Recipe(Cook owner, String name, List<Ingredient>ingredients, List<String> equipmentUsed, String image, String description, boolean isVegan, int likes, int dislikes, int forNumberOfPeople, Time cookingTime ){
        this.owner = owner;
        this.name = name;
        this.ingredients = ingredients;
        this.equipmentUsed = equipmentUsed;
        this.image = image;
        this.description = description;
        this.isVegan = isVegan;
        this.likes = likes;
        this.dislikes = dislikes;
        this.forNumberOfPeople = forNumberOfPeople;
        this.cookingTime = cookingTime;
    }
}

