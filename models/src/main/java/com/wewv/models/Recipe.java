package com.wewv.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Cook owner;
}

