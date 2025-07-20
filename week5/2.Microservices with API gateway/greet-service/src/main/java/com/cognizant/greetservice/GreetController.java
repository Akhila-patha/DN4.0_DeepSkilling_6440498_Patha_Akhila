package com.cognizant.greet;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greet")
public class GreetController {

    @GetMapping
    public String greet() {
        return "Hello World";
    }
}