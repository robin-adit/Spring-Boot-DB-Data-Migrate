package com.migrate.repository.primary;

import com.migrate.entity.primary.Warehouses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehousesRepository extends JpaRepository<Warehouses,Long> {
}
