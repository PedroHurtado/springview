package com.example.demo.infraestructura;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Ingredient;

@Component
public class IngredientRepositoryImpl implements IngredientRepository {
    public static List<Ingredient> ingredients = new ArrayList<>();
    @Override
    public void add(Ingredient ingredient){
        ingredients.add(ingredient);
    }
    @Override
    public Optional<Ingredient> get(UUID id){
        return ingredients.stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
