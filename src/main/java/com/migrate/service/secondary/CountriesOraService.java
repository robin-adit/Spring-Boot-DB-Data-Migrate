package com.migrate.service.secondary;

import com.migrate.entity.secondary.CountriesOra;
import com.migrate.entity.secondary.RegionsOra;

import java.util.List;

public interface CountriesOraService {
    public List<CountriesOra> persistCountriesOra(List<CountriesOra> regionsOraList);
}
