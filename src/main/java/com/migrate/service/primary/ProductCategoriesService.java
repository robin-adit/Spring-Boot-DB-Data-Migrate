package com.migrate.service.primary;

import com.migrate.entity.primary.ProductCategories;
import com.migrate.entity.primary.Regions;

import java.util.List;

public interface ProductCategoriesService {

    public List<ProductCategories> fetchAllLists();
}