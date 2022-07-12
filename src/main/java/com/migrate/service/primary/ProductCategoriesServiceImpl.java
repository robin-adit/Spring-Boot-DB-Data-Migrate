package com.migrate.service.primary;

import com.migrate.entity.primary.ProductCategories;
import com.migrate.repository.primary.ProductCategoriesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoriesServiceImpl implements ProductCategoriesService {

    private final ProductCategoriesRepository productCategoriesRepository;

    @Autowired
    public ProductCategoriesServiceImpl(ProductCategoriesRepository productCategoriesRepository) {
        this.productCategoriesRepository = productCategoriesRepository;
    }

    @Override
    public List<ProductCategories> fetchAllLists() {
        return productCategoriesRepository.findAll();
    }
}