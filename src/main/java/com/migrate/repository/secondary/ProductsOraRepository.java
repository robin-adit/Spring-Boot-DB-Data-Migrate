package com.migrate.repository.secondary;

import com.migrate.entity.secondary.ProductCategoriesOra;
import com.migrate.entity.secondary.ProductsOra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsOraRepository extends JpaRepository<ProductsOra,Long> {
}
