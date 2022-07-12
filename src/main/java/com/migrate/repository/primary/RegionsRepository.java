package com.migrate.repository.primary;

import com.migrate.entity.primary.Regions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionsRepository extends JpaRepository<Regions,Long> {
}
