package com.wewv.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    private String username;

    @Getter
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
