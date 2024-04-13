package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestApiDemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        coffees.addAll(List.of(
           new Coffee("кофи"),
            new Coffee("кофе"),
            new Coffee("кофэ"),
            new Coffee("какава"),
            new Coffee("капучина")
        ));
    }

    @GetMapping("/coffees")
    Iterable<Coffee> getCoffees() {
        return coffees;
    }

    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return coffees.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}
