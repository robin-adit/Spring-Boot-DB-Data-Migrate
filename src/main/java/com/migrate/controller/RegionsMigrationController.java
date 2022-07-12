package com.migrate.controller;

import com.migrate.entity.primary.Regions;
import com.migrate.entity.secondary.RegionsOra;
import com.migrate.service.primary.RegionsService;
import com.migrate.service.secondary.RegionsOraService;
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
public class RegionsMigrationController implements CommonConstantsIF {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegionsService regionsService;

    @Autowired
    private RegionsOraService regionsOraService;

    @GetMapping(value = "/activateRegionScan/{activationToken}")
    public List<Regions> ActivateRegionScan(@PathVariable("activationToken") String activationToken)
    {
        if(null != activationToken && activationToken.equalsIgnoreCase(CommonConstantsIF.VALIDATION_TOKEN))
        {
            List<Regions> regionList = regionsService.fetchAllLists();
            logger.info("Regions fetched for Migration/Updation=" + regionList.size());
            regionList.forEach(regions -> logger.info(regions.toString()));
            return regionList;
        }
        else
            return null;
    }

    private List<RegionsOra> convertRegionDataForMigration(List<Regions> regionsList)
    {
        List<RegionsOra> regionsOraList = new ArrayList<RegionsOra>();
        for(Regions region : regionsList)
        {
            RegionsOra regionsOra = DataMigrationController.modelMapper().map(region,RegionsOra.class);
            regionsOraList.add(regionsOra);
        }
        logger.info("Regions Converted for Secondary DB = " + regionsOraList.size());
        regionsOraList.forEach(regionsOra -> logger.info(regionsOra.toString()));

        return regionsOraList;
    }

    private List<RegionsOra> ActivateRegionTransfer(List<RegionsOra> regionsOraList)
    {
        if(!regionsOraList.isEmpty())
            return regionsOraService.persistRegionsOra(regionsOraList);
        else
            return null;
    }

    @GetMapping(value = "/migrateRegions/{activationToken}")
    public List<RegionsOra> migrateRegions(@PathVariable("activationToken") String activationToken)
    {
        List<Regions> regionsList = ActivateRegionScan(activationToken);
        if (!regionsList.isEmpty())
        {
            List<RegionsOra> regionsOraList = convertRegionDataForMigration(regionsList);
            return ActivateRegionTransfer(regionsOraList);
        }
        else
            return null;
    }
}