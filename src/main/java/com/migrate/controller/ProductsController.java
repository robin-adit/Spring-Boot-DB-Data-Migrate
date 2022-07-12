package com.migrate.controller;

import com.migrate.entity.primary.ProductCategories;
import com.migrate.entity.primary.Products;
import com.migrate.entity.secondary.ProductCategoriesOra;
import com.migrate.entity.secondary.ProductsOra;
import com.migrate.service.primary.ProductCategoriesService;
import com.migrate.service.primary.ProductsService;
import com.migrate.service.secondary.ProductCategoriesOraService;
import com.migrate.service.secondary.ProductsOraService;
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
public class ProductsController implements CommonConstantsIF {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsOraService productsOraService;

    @GetMapping(value = "/activateProductsScan/{activationToken}")
    public List<Products> ActivateProductsScan(@PathVariable("activationToken") String activationToken)
    {
        if(null != activationToken && activationToken.equalsIgnoreCase(CommonConstantsIF.VALIDATION_TOKEN))
        {
            List<Products> productsList = productsService.fetchAllLists();
            logger.info("Fetched Products =" + productsList.size());
            productsList.forEach(products -> logger.info(products.toString()));
            return productsList;
        }
        else
            return null;
    }

    private List<ProductsOra> convertProductsDataForMigration(List<Products> productsList)
    {
        List<ProductsOra> productsOraList = new ArrayList<ProductsOra>();
        for(Products products : productsList)
        {
            ProductsOra productsOra = DataMigrationController.modelMapper().map(products,ProductsOra.class);
            productsOraList.add(productsOra);
        }

        logger.info("Converted Products=" + productsOraList.size());
        productsOraList.forEach(productsOra -> System.out.println(productsOra.toString()));

        return productsOraList;
    }

    private List<ProductsOra> ActivateProductsTransfer(List<ProductsOra> productsOraList)
    {
        if(!productsOraList.isEmpty())
            return productsOraService.persistProductsOra(productsOraList);
        else
            return null;
    }

    @GetMapping(value = "/migrateProducts/{activationToken}")
    public List<ProductsOra> migrateProducts(@PathVariable("activationToken") String activationToken)
    {
        List<Products> productsList = ActivateProductsScan(activationToken);
        if (!productsList.isEmpty())
        {
            List<ProductsOra> productsOraList = convertProductsDataForMigration(productsList);
            return ActivateProductsTransfer(productsOraList);
        }
        else
            return null;
    }
}