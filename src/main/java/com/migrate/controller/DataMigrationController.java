package com.migrate.controller;

import com.migrate.entity.secondary.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataMigrationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Bean
    public static ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Autowired
    private RegionsMigrationController regionsMigrationController;
    @Autowired
    private CountriesMigrationController countriesMigrationController;
    @Autowired
    private LocationsMigrationController locationsMigrationController;
    @Autowired
    private WarehousesMigrationController warehousesMigrationController;
    @Autowired
    private EmployeesMigrationController employeesMigrationController;
    @Autowired
    private ProductCategoriesController productCategoriesController;
    @Autowired
    private ProductsController productsController;

    @GetMapping(value = "/migrate/{activationToken}")
    public String migrate(@PathVariable("activationToken") String activationToken)
    {
        logger.info("In DataMigrationController.migrate");

        List<RegionsOra> regionsOraList =
                regionsMigrationController.migrateRegions(activationToken);
        if(null != regionsOraList && !regionsOraList.isEmpty()) {
            List<CountriesOra> countriesOraList =
                    countriesMigrationController.migrateCountries(activationToken);
                if(null != countriesOraList && !countriesOraList.isEmpty()) {
                    List<LocationsOra> locationsOraList =
                            locationsMigrationController.migrateLocations(activationToken);
                    if(null != locationsOraList && !locationsOraList.isEmpty()) {
                        List<WarehousesOra> warehousesOraList =
                                warehousesMigrationController.migrateWarehouses(activationToken);
                        if (null != warehousesOraList && !warehousesOraList.isEmpty()) {
                            List<EmployeesOra> employeesOraList =
                                    employeesMigrationController.migrateEmployees(activationToken);
                            if(null != employeesOraList && !employeesOraList.isEmpty()) {
                                List<ProductCategoriesOra> productCategoriesOraList =
                                        productCategoriesController.migrateProductCategories(activationToken);
                                if(null != productCategoriesOraList && !productCategoriesOraList.isEmpty()) {
                                    List<ProductsOra> productsOraList =
                                            productsController.migrateProducts(activationToken);
                                    logger.info("Regions=" + regionsOraList.size()
                                            + "\nCountries=" + countriesOraList.size()
                                            + "\nLocations=" + locationsOraList.size()
                                            + "\n Warehouses=" + warehousesOraList.size()
                                            + "\n Employees=" + employeesOraList.size() / 2       //WHY
                                            + "\n Product Categories=" + productCategoriesOraList.size()
                                            + "\n Products=" + productsOraList.size()
                                            + " Migrated");

                                    return ("Regions=" + regionsOraList.size()
                                            + "\nCountries=" + countriesOraList.size()
                                            + "\nLocations=" + locationsOraList.size()
                                            + "\n Warehouses=" + warehousesOraList.size()
                                            + "\n Employees=" + employeesOraList.size() / 2       //WHY
                                            + "\n Product Categories=" + productCategoriesOraList.size()
                                            + "\n Products=" + productsOraList.size()
                                            + " Migrated");
                                }
                            }

                        }
                    }
                }
        }
        return "Job Incomplete";
    }
}