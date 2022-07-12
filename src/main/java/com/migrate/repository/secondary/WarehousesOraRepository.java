package com.migrate.repository.secondary;

import com.migrate.entity.primary.Warehouses;
import com.migrate.entity.secondary.WarehousesOra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehousesOraRepository extends JpaRepository<WarehousesOra,Long> {
}
