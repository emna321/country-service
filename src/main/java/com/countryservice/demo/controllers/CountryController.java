package com.countryservice.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.countryservice.demo.beans.Country;
import com.countryservice.demo.services.CountryService;

@RestController
public class CountryController {

    @Autowired
    CountryService countryservice;

    @GetMapping("/getcountries")
    public ResponseEntity<List<Country>> getCountries() {
        try {
            return new ResponseEntity<>(countryservice.getAllCountries(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable int id) {
        Country c = countryservice.getCountryById(id);
        return c != null ? new ResponseEntity<>(c, HttpStatus.OK)
                         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam("name") String name) {
        Country c = countryservice.getCountryByName(name);
        return c != null ? new ResponseEntity<>(c, HttpStatus.OK)
                         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addcountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        return new ResponseEntity<>(countryservice.addCountry(country), HttpStatus.CREATED);
    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable int id, @RequestBody Country country) {
        Country c = countryservice.updateCountry(id, country);
        return c != null ? new ResponseEntity<>(c, HttpStatus.OK)
                         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletecountry/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable int id) {
        return countryservice.deleteCountry(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}