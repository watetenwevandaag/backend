package com.wewv.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wewv.components.CookComponent;
import com.wewv.models.Cook;
import com.wewv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cook")
@CrossOrigin("http://localhost:4200")
public class CookController {

    @Autowired
    UserService userService;
    @Autowired
    CookComponent cookComponent;

    @PostMapping(value = "/register")
    public ResponseEntity<Cook> register(@RequestBody ObjectNode node){
        if(userService.getByUsername(node.get("username").asText()) == null){
            Cook cookToCreate = new Cook();
            cookToCreate.setEmail(node.get("email").asText());
            cookToCreate.setUsername(node.get("username").asText());
            Cook cook = cookComponent.regiser(cookToCreate, node.get("password").asText());
            if(cook != null){
                return new ResponseEntity<Cook>(cook, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Cook> login(@RequestBody ObjectNode node){
        if(userService.getByUsername(node.get("username").asText()) != null){
            Cook cook = cookComponent.login(node.get("username").asText(),node.get("password").asText());
            if(cook != null){
                return new ResponseEntity<>(cook,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
