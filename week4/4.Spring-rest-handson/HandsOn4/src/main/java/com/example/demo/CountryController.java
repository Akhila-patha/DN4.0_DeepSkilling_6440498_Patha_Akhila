package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final List<Country> countries = new ArrayList<>();

    @GetMapping
    public List<Country> getAllCountries() {
        return countries;
    }

    @PostMapping
    public Country addCountry(@RequestBody @Valid Country country) {
        countries.add(country);
        return country;
    }
}
