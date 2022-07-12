package com.migrate.service.secondary;

import com.migrate.entity.secondary.ProductCategoriesOra;
import com.migrate.entity.secondary.RegionsOra;
import com.migrate.repository.secondary.ProductCategoriesOraRepository;
import com.migrate.repository.secondary.RegionsOraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoriesOraServiceImpl implements ProductCategoriesOraService{

    private final ProductCategoriesOraRepository productCategoriesOraRepository;

    @Autowired
    public ProductCategoriesOraServiceImpl(ProductCategoriesOraRepository productCategoriesOraRepository) {
        this.productCategoriesOraRepository = productCategoriesOraRepository;
    }

    @Override
    public List<ProductCategoriesOra> persistProductCategoriesOra(List<ProductCategoriesOra> productCategoriesOraList) {
        return productCategoriesOraRepository.saveAll(productCategoriesOraList);
    }
}