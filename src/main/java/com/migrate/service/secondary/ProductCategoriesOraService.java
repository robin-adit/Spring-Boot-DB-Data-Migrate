package com.migrate.service.secondary;

import com.migrate.entity.secondary.ProductCategoriesOra;
import com.migrate.entity.secondary.RegionsOra;

import java.util.List;

public interface ProductCategoriesOraService {
    public List<ProductCategoriesOra> persistProductCategoriesOra(List<ProductCategoriesOra> productCategoriesOraList);
}
