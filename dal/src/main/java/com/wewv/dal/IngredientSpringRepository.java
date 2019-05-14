package com.wewv.dal;

import com.wewv.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientSpringRepository extends JpaRepository<Ingredient, Integer> {
}
