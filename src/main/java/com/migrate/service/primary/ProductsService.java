package com.migrate.service.primary;

import com.migrate.entity.primary.ProductCategories;
import com.migrate.entity.primary.Products;

import java.util.List;

public interface ProductsService {

    public List<Products> fetchAllLists();
}