package com.cognizant.springlearn;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @GetMapping("/countries/{code}")
    public Country getCountryByCode(@PathVariable String code) {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> list = (List<Country>) context.getBean("countryList");

        return list.stream()
                   .filter(c -> c.getCode().equalsIgnoreCase(code))
                   .findFirst()
                   .orElseThrow(() -> new CountryNotFoundException("Country not found: " + code));
    }
}
