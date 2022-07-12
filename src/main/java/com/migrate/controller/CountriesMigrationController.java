package com.migrate.controller;

import com.migrate.entity.primary.Countries;
import com.migrate.entity.secondary.CountriesOra;
import com.migrate.service.primary.CountriesService;
import com.migrate.service.secondary.CountriesOraService;
import com.migrate.utility.CommonConstantsIF;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountriesMigrationController implements CommonConstantsIF {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CountriesService countriesService;

    @Autowired
    private CountriesOraService countriesOraService;

    @GetMapping(value = "/activateCountriesScan/{activationToken}")
    public List<Countries> ActivateCountriesScan(@PathVariable("activationToken") String activationToken)
    {
        if(null != activationToken && activationToken.equalsIgnoreCase(CommonConstantsIF.VALIDATION_TOKEN))
        {
            List<Countries> countriesList = countriesService.fetchAllLists();
            logger.info("Countries fetched = " + countriesList.size());
            countriesList.forEach(countries -> logger.info(countries.toString()));
            return countriesList;
        }
        else
            return null;
    }


    private List<CountriesOra> convertCountriesDataForMigration(List<Countries> countriesList)
    {
        List<CountriesOra> countriesOraList = new ArrayList<CountriesOra>();
        for(Countries countries : countriesList)
        {
            CountriesOra countriesOra = DataMigrationController.modelMapper().map(countries,CountriesOra.class);
            countriesOraList.add(countriesOra);
        }

        logger.info("Converted Countries=" + countriesList.size());
        countriesOraList.forEach(countriesOra -> logger.info(countriesOra.toString()));

        return countriesOraList;
    }
    private List<CountriesOra> ActivateCountriesTransfer(List<CountriesOra> countriesOraList)
    {
        if(!countriesOraList.isEmpty())
            return countriesOraService.persistCountriesOra(countriesOraList);
        else
            return null;
    }

    @GetMapping(value = "/migrateCountries/{activationToken}")
    public List<CountriesOra> migrateCountries(@PathVariable("activationToken") String activationToken)
    {
        List<Countries> countriesList = ActivateCountriesScan(activationToken);
        if (!countriesList.isEmpty())
        {
            List<CountriesOra> regionsOraList = convertCountriesDataForMigration(countriesList);
            return ActivateCountriesTransfer(regionsOraList);
        }
        else
            return null;
    }
}