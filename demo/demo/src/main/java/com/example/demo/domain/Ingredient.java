package com.example.demo.domain;

import java.util.UUID;

import com.example.demo.core.EntityBase;

import lombok.Getter;

@Getter
public class Ingredient extends EntityBase {
    private UUID id;
    private String name;
    private double cost;
    protected Ingredient(UUID id,String name,double cost) {
        super(id);
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
    public static Ingredient create(String name, double cost){
        return new Ingredient(UUID.randomUUID(), name,cost);
    }
    public void Update(String name, double cost){
        this.name=name;
        this.cost = cost;
    }
}
