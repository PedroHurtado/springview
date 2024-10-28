package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Ingredient;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
public class IngredientAdd {

    public record Request(String name, double cost) {
    }

    public record Response(UUID id, String name, double cost) {
    }
    @RestController
    @RequestMapping("/ingredients")
    public class Controller {


        private final UseCase service;

        public Controller(UseCase service) {
            this.service = service;
        }

        @PostMapping()
        public Response handle(@RequestBody Request request) {
            return this.service.handle(request);
        }        

    }

    public interface UseCase {
        public Response handle(Request request);
    }

    @Component
    public class UseCaseImpl implements UseCase {

        @Override
        public Response handle(Request request) {
            var ingredient = Ingredient.create(request.name(), request.cost());
            return new Response(ingredient.getId(), ingredient.getName(), ingredient.getCost());
        }
    }
}
