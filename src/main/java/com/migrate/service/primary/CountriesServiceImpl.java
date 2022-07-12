package com.migrate.service.primary;

import com.migrate.entity.primary.Countries;
import com.migrate.repository.primary.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesServiceImpl implements CountriesService{
    private final CountriesRepository countriesRepository;

    @Autowired
    public CountriesServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public List<Countries> fetchAllLists() {
        return countriesRepository.findAll();
    }
}