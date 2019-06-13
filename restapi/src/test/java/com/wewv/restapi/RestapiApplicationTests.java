package com.wewv.restapi;

import com.wewv.dal.CookRepository;
import com.wewv.dal.RecipeSpringRepository;
import com.wewv.models.Cook;
import com.wewv.models.Ingredient;
import com.wewv.models.Recipe;
import com.wewv.service.CookService;
import com.wewv.service.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestapiApplicationTests {

    @Autowired
    CookService cookService;

    private Cook cook = new Cook();

    @Before
    public void setUp() throws Exception {
        cook.setUsername("test");
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCreateCookAndGetUsername(){
        System.out.println(cookService.create(cook).getId());
        Assert.assertEquals(cook.getUsername(),cookService.getById(1).getUsername());
    }

}
