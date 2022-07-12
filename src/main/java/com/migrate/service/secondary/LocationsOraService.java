package com.migrate.service.secondary;

import com.migrate.entity.secondary.LocationsOra;

import java.util.List;

public interface LocationsOraService {
    public List<LocationsOra> persistLocationsOra(List<LocationsOra> locationsOraList);
}
