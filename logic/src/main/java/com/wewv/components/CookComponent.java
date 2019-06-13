package com.wewv.components;

import com.wewv.models.Cook;
import com.wewv.models.User;
import com.wewv.service.CookService;
import com.wewv.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CookComponent{

    @Autowired
    private CookService cookService;
    @Autowired
    private UserService userService;

    public Cook regiser(Cook cook, String password){
        try{
            User user = new User();
            user.setUsername(cook.getUsername());
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            userService.create(user);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
        cookService.create(cook);
        return cook;
    }

    public Cook login(String username, String password){
        User dbUser = userService.getByUsername(username);
        if(BCrypt.checkpw(password, dbUser.getPassword())){
            return cookService.getByUsername(username);
        }else{
            return null;
        }
    }
}
