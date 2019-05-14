package com.wewv.dal;

import com.wewv.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeSpringRepository extends JpaRepository<Recipe,Integer> {
}
