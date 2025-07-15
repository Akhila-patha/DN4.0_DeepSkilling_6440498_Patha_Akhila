package com.cognizant.springsecurity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @GetMapping("/countries")
    public List<Map<String, String>> getCountries() {
        List<Map<String, String>> countries = new ArrayList<>();
        countries.add(new HashMap<>() {{
            put("code", "US");
            put("name", "United States");
        }});
        countries.add(new HashMap<>() {{
            put("code", "DE");
            put("name", "Germany");
        }});
        countries.add(new HashMap<>() {{
            put("code", "IN");
            put("name", "India");
        }});
        countries.add(new HashMap<>() {{
            put("code", "JP");
            put("name", "Japan");
        }});
        return countries;
    }
}