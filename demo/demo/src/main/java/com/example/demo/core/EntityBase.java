package com.example.demo.core;

import java.util.UUID;

import lombok.Getter;

@Getter
public abstract class EntityBase {
    public UUID id; 
    protected EntityBase(UUID id){
        this.id = id;
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EntityBase e){
            return e.id.equals(this.id);
        }
        return false;
    }


}
