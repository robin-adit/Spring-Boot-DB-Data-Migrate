package com.migrate.repository.primary;

import com.migrate.entity.primary.ProductCategories;
import com.migrate.entity.primary.Regions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategories,Long> {
}
