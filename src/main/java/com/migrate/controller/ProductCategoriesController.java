package com.migrate.controller;

import com.migrate.entity.primary.ProductCategories;
import com.migrate.entity.primary.Regions;
import com.migrate.entity.secondary.ProductCategoriesOra;
import com.migrate.entity.secondary.RegionsOra;
import com.migrate.service.primary.ProductCategoriesService;
import com.migrate.service.primary.RegionsService;
import com.migrate.service.secondary.ProductCategoriesOraService;
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
public class ProductCategoriesController implements CommonConstantsIF {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductCategoriesService productCategoriesService;

    @Autowired
    private ProductCategoriesOraService productCategoriesOraService;

    @GetMapping(value = "/activateProductCategoriesScan/{activationToken}")
    public List<ProductCategories> ActivateProductCategoriesScan(@PathVariable("activationToken") String activationToken)
    {
        if(null != activationToken && activationToken.equalsIgnoreCase(CommonConstantsIF.VALIDATION_TOKEN))
        {
            List<ProductCategories> productCategoriesList = productCategoriesService.fetchAllLists();
            logger.info("Product Categories fetched=" + productCategoriesList.size());
            productCategoriesList.forEach(productCategories -> logger.info(productCategories.toString()));
            return productCategoriesList;
        }
        else
            return null;
    }

    private List<ProductCategoriesOra> convertProductCategoriesDataForMigration(List<ProductCategories> productCategoriesList)
    {
        List<ProductCategoriesOra> productCategoriesOraList = new ArrayList<ProductCategoriesOra>();
        for(ProductCategories productCategories : productCategoriesList)
        {
            ProductCategoriesOra productCategoriesOra = DataMigrationController.modelMapper().map(productCategories,ProductCategoriesOra.class);
            productCategoriesOraList.add(productCategoriesOra);
        }

        logger.info("Converted Product Categories=" + productCategoriesOraList.size());
        productCategoriesOraList.forEach(productCategoriesOra -> logger.info(productCategoriesOra.toString()));

        return productCategoriesOraList;
    }

    private List<ProductCategoriesOra> ActivateProductCategoriesTransfer(List<ProductCategoriesOra> productCategoriesOraList)
    {
        if(!productCategoriesOraList.isEmpty())
            return productCategoriesOraService.persistProductCategoriesOra(productCategoriesOraList);
        else
            return null;
    }

    @GetMapping(value = "/migrateProductCategories/{activationToken}")
    public List<ProductCategoriesOra> migrateProductCategories(@PathVariable("activationToken") String activationToken)
    {
        List<ProductCategories> productCategoriesList = ActivateProductCategoriesScan(activationToken);
        if (!productCategoriesList.isEmpty())
        {
            List<ProductCategoriesOra> productCategoriesOraList = convertProductCategoriesDataForMigration(productCategoriesList);
            return ActivateProductCategoriesTransfer(productCategoriesOraList);
        }
        else
            return null;
    }
}