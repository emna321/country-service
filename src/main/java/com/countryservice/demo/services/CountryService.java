package com.countryservice.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repositories.CountryRepository;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRep;

    public List<Country> getAllCountries() {
        return countryRep.findAll();
    }

    public Country getCountryById(int id) {
        return countryRep.findById(id).orElse(null);
    }

    public Country getCountryByName(String name) {
        return countryRep.findByCountryName(name).orElse(null);
    }

    public Country addCountry(Country country) {
        return countryRep.save(country);
    }

    public Country updateCountry(int id, Country country) {
        Country existing = countryRep.findById(id).orElse(null);
        if (existing == null) return null;
        existing.setCountryName(country.getCountryName());
        existing.setCapital(country.getCapital());
        return countryRep.save(existing);
    }

    public boolean deleteCountry(int id) {
        if (!countryRep.existsById(id)) return false;
        countryRep.deleteById(id);
        return true;
    }
}