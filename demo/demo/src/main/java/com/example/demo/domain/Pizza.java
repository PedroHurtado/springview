package com.example.demo.domain;

import java.util.Set;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;

@Getter
public class Pizza {
    private final double PROFIT =  1.2;
    private String name;
    private String description;
    private String url;
    @Getter(AccessLevel.NONE)
    private Set<Ingredient> ingredients = new HashSet<>();

    public Pizza(UUID id, String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public void Update(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public static Pizza Create(String name, String description, String url) {
        return new Pizza(UUID.randomUUID(), name, description, url);
    }

    public void AddIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void RemoveIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredients.stream().toList();
    }

    public double getPrice() {
        return ingredients.stream().map(i -> i.getCost()).reduce(0.0,(a,v)->a+v) * PROFIT;
    }
}
