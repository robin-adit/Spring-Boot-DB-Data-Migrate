package com.migrate.service.secondary;

import com.migrate.entity.secondary.ProductCategoriesOra;
import com.migrate.entity.secondary.ProductsOra;
import com.migrate.repository.secondary.ProductCategoriesOraRepository;
import com.migrate.repository.secondary.ProductsOraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsOraServiceImpl implements ProductsOraService{

    private final ProductsOraRepository productsOraRepository;

    @Autowired
    public ProductsOraServiceImpl(ProductsOraRepository productsOraRepository) {
        this.productsOraRepository = productsOraRepository;
    }

    @Override
    public List<ProductsOra> persistProductsOra(List<ProductsOra> productsOraList) {
        return productsOraRepository.saveAll(productsOraList);
    }
}