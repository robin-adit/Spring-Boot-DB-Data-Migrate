package com.migrate.repository.primary;

import com.migrate.entity.primary.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<Locations,Long> {
}
