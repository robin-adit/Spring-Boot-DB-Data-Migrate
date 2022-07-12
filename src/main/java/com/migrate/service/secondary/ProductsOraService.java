package com.migrate.service.secondary;

import com.migrate.entity.secondary.ProductCategoriesOra;
import com.migrate.entity.secondary.ProductsOra;

import java.util.List;

public interface ProductsOraService {
    public List<ProductsOra> persistProductsOra(List<ProductsOra> productsOraList);
}
