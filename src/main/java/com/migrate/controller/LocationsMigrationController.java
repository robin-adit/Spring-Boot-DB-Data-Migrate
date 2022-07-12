package com.migrate.controller;

import com.migrate.entity.primary.Locations;
import com.migrate.entity.secondary.LocationsOra;
import com.migrate.service.primary.LocationsService;
import com.migrate.service.secondary.LocationsOraService;
import com.migrate.utility.CommonConstantsIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationsMigrationController implements CommonConstantsIF {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LocationsService locationsService;

    @Autowired
    private LocationsOraService locationsOraService;

    @GetMapping(value = "/activateLocationsScan/{activationToken}")
    public List<Locations> ActivateLocationsScan(@PathVariable("activationToken") String activationToken)
    {
        if(null != activationToken && activationToken.equalsIgnoreCase(CommonConstantsIF.VALIDATION_TOKEN))
        {
            List<Locations> locationsList = locationsService.fetchAllLists();
            logger.info("Locations fetched ="+locationsList.size());
            locationsList.forEach(locations -> logger.info(locations.toString()));
            return locationsList;
        }
        else
            return null;
    }


    public List<LocationsOra> convertLocationsDataForMigration(List<Locations> locationsList)
    {
        List<LocationsOra> locationsOraList = new ArrayList<LocationsOra>();
        for(Locations locations : locationsList)
        {
            LocationsOra locationsOra = DataMigrationController.modelMapper().map(locations,LocationsOra.class);
            locationsOraList.add(locationsOra);
        }

        logger.info("Converted Locations=" + locationsOraList.size());
        locationsOraList.forEach(locationsOra -> logger.info(locationsOra.toString()));

        return locationsOraList;
    }
    public List<LocationsOra> ActivateLocationsTransfer(List<LocationsOra> locationsOraList)
    {
        if(!locationsOraList.isEmpty())
            return locationsOraService.persistLocationsOra(locationsOraList);
        else
            return null;
    }

    @GetMapping(value = "/migrateLocations/{activationToken}")
    public List<LocationsOra> migrateLocations(@PathVariable("activationToken") String activationToken)
    {
        List<Locations> locationsList = ActivateLocationsScan(activationToken);
        if (!locationsList.isEmpty())
        {
            List<LocationsOra> locationsOraList = convertLocationsDataForMigration(locationsList);
            return ActivateLocationsTransfer(locationsOraList);
        }
        else
            return null;
    }
}