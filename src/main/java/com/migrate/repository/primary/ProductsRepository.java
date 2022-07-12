package com.migrate.repository.primary;

import com.migrate.entity.primary.ProductCategories;
import com.migrate.entity.primary.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,Long> {
}
