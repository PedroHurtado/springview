package com.example.demo.controller.ingredient;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Ingredient;
import com.example.demo.infraestructura.IngredientRepository;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
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
        public ResponseEntity<Response> handle(@RequestBody Request request) {
            var body = this.service.handle(request);
            return ResponseEntity.status(201).body(body);
        }        

    }

    public interface UseCase {
        public Response handle(Request request);
    }

    @Component
    public class UseCaseImpl implements UseCase {

        private final IngredientRepository repository;
        public UseCaseImpl(final IngredientRepository repository){
            this.repository = repository;
        }
        @Override
        public Response handle(Request request) {
            var ingredient = Ingredient.create(request.name(), request.cost());
            this.repository.add(ingredient);
            return new Response(ingredient.getId(), ingredient.getName(), ingredient.getCost());
        }
    }
}
