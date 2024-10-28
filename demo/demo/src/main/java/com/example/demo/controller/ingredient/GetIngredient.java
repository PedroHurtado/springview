package com.example.demo.controller.ingredient;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.infraestructura.IngredientRepository;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Configuration
public class GetIngredient {
    public record Response(
        UUID id,
        String name,
        double cost
    ) {
    }

    @RestController
    @RequestMapping("/ingredients")
    public class Controller{
        private final UseCase service;
        public Controller(final UseCase service){
            this.service = service;
        }
        @GetMapping("/{id}")
        public ResponseEntity<Response> getMethodName(@PathVariable UUID id) {
            var body = this.service.handle(id);
            return ResponseEntity.status(200).body(body);
        }
        
    }
    public interface UseCase {
        
        Response handle(UUID id);
    }
    @Component
    public class UseCaseImpl implements UseCase{
        private final IngredientRepository repository;
        public UseCaseImpl(final IngredientRepository repository){
            this.repository = repository;
        }
        @Override
        public Response handle(UUID id) {
            var ingredient = this.repository.get(id).orElseThrow(()->{
                throw new RuntimeException();
            });

            return new Response(ingredient.getId(), ingredient.getName(), ingredient.getCost());
        }

    }
    
}
