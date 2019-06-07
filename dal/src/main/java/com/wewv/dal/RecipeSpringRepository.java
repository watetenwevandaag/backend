package com.wewv.dal;

import com.wewv.models.Cook;
import com.wewv.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeSpringRepository extends JpaRepository<Recipe,Integer> {

    List<Recipe> findByNameContains(String name);

    List<Recipe> findByOwner(Cook owner);
}
