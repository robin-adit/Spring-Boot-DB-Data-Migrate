package com.migrate.repository.secondary;

import com.migrate.entity.primary.ProductCategories;
import com.migrate.entity.secondary.ProductCategoriesOra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoriesOraRepository extends JpaRepository<ProductCategoriesOra,Long> {
}
