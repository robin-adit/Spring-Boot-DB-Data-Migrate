package com.migrate.repository.secondary;

import com.migrate.entity.primary.Locations;
import com.migrate.entity.secondary.LocationsOra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsOraRepository extends JpaRepository<LocationsOra,Long> {
}
