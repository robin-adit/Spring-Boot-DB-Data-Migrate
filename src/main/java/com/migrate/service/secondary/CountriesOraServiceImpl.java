package com.migrate.service.secondary;

import com.migrate.entity.secondary.CountriesOra;
import com.migrate.entity.secondary.RegionsOra;
import com.migrate.repository.secondary.CountriesOraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesOraServiceImpl implements CountriesOraService{

    private final CountriesOraRepository countriesOraRepository;

    @Autowired
    public CountriesOraServiceImpl(CountriesOraRepository countriesOraRepository) {
        this.countriesOraRepository = countriesOraRepository;
    }

    @Override
    public List<CountriesOra> persistCountriesOra(List<CountriesOra> countriesOraList) {
        return countriesOraRepository.saveAll(countriesOraList);
    }
}