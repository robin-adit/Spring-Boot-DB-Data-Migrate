package com.migrate.controller;

import com.migrate.entity.primary.Regions;
import com.migrate.entity.primary.Warehouses;
import com.migrate.entity.secondary.RegionsOra;
import com.migrate.entity.secondary.WarehousesOra;
import com.migrate.service.primary.RegionsService;
import com.migrate.service.primary.WarehousesService;
import com.migrate.service.secondary.RegionsOraService;
import com.migrate.service.secondary.WarehousesOraService;
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
public class WarehousesMigrationController implements CommonConstantsIF {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WarehousesService warehousesService;

    @Autowired
    private WarehousesOraService warehousesOraService;

    @GetMapping(value = "/activateWarehousesScan/{activationToken}")
    public List<Warehouses> ActivateWarehousesScan(@PathVariable("activationToken") String activationToken)
    {
        if(null != activationToken && activationToken.equalsIgnoreCase(CommonConstantsIF.VALIDATION_TOKEN))
        {
            List<Warehouses> warehousesList = warehousesService.fetchAllLists();
            logger.info("Warehouses fetched=" + warehousesList.size());
            warehousesList.forEach(warehouses -> logger.info(warehouses.toString()));
            return warehousesList;
        }
        else
            return null;
    }

    private List<WarehousesOra> convertWarehousesDataForMigration(List<Warehouses> warehousesList)
    {
        List<WarehousesOra> warehousesOraList = new ArrayList<WarehousesOra>();
        for(Warehouses warehouses : warehousesList)
        {
            WarehousesOra warehousesOra = DataMigrationController.modelMapper().map(warehouses,WarehousesOra.class);
            warehousesOraList.add(warehousesOra);
        }

        logger.info("Converted Warehouses=" + warehousesOraList.size());
        warehousesOraList.forEach(warehousesOra -> logger.info(warehousesOra.toString()));

        return warehousesOraList;
    }

    private List<WarehousesOra> ActivateWarehousesTransfer(List<WarehousesOra> warehousesOraList)
    {
        if(!warehousesOraList.isEmpty())
            return warehousesOraService.persistWarehousesOra(warehousesOraList);
        else
            return null;
    }

    @GetMapping(value = "/migrateWarehouses/{activationToken}")
    public List<WarehousesOra> migrateWarehouses(@PathVariable("activationToken") String activationToken)
    {
        List<Warehouses> warehousesList = ActivateWarehousesScan(activationToken);
        if (!warehousesList.isEmpty())
        {
            List<WarehousesOra> warehousesOraList = convertWarehousesDataForMigration(warehousesList);
            return ActivateWarehousesTransfer(warehousesOraList);
        }
        else
            return null;
    }
}