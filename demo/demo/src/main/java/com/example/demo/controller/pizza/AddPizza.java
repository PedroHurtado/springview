package com.example.demo.controller.pizza;

import java.util.UUID;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Pizza;
import com.example.demo.infraestructura.IngredientRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
public class AddPizza {

    public record RequestIngredient(UUID id) {

    }

    public record Request(String name, String description, String url, List<RequestIngredient> ingredients) {

    }

    public record ResponseIngredient(UUID id, String name) {
    }

    public record Response(
            UUID id,
            String name,
            String description,
            String url,
            double price,
            List<ResponseIngredient> ingredients) {
    }

    @RestController
    @RequestMapping("/pizzas")
    public class Controller {
        private final UseCase service;

        public Controller(final UseCase service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<Response> handle(@RequestBody Request request) {
            var body = this.service.handle(request);
            return ResponseEntity.status(201).body(body);
        }

    }

    public interface UseCase {
        Response handle(Request request);
    }

    @Component
    public class UseCaseImpl implements UseCase {

        private final IngredientRepository repository;

        public UseCaseImpl(final IngredientRepository repository) {
            this.repository = repository;
        }

        @Override
        public Response handle(Request request) {
            var pizza = Pizza.Create(request.name(), request.description(), request.url());

            request.ingredients.forEach(i -> {
                var ingredient = this.repository.get(i.id()).orElseThrow(() -> {
                    throw new RuntimeException();
                });
                pizza.AddIngredient(ingredient);
            });

            return new Response(
                    pizza.getId(),
                    pizza.getName(),
                    pizza.getDescription(),
                    pizza.getUrl(),
                    pizza.getPrice(),
                    pizza.getIngredients().stream()
                            .map(i -> new ResponseIngredient(i.getId(), i.getName())).toList());

        }
    }
}
