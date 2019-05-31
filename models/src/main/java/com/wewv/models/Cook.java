package com.wewv.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "cook")
@Setter
@Getter
@NoArgsConstructor
public class Cook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Recipe>ownRecipes;

}
