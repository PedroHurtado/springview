package com.example.demo.controller.pizza;

import java.util.UUID;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Configuration
public class AddPizza {

    public record RequestIngredient(UUID id){

    }
    public record Request(String name,String description,String url,List<RequestIngredient> ingredients){

    }
    public record ResponseIngredient(UUID id, String name){}

    public record Response(
            UUID id,
            String name,
            String description,
            String url,
            List<ResponseIngredient> ingredients
    ){}

    @RestController
    @RequestMapping("/pizzas")
    public class Controller{
        private final UseCase service;
        public Controller(final UseCase service){
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
    public class  UseCaseImpl implements UseCase {

        @Override
        public Response handle(Request request) {           
            throw new UnsupportedOperationException("Unimplemented method 'handle'");
        }
    
        
    }
}
