package com.example.demo.infraestructura;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.Ingredient;

public interface IngredientRepository {

    void add(Ingredient ingredient);
    Optional<Ingredient> get(UUID id);

}