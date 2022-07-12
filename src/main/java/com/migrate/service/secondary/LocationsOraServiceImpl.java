package com.migrate.service.secondary;

import com.migrate.entity.secondary.LocationsOra;
import com.migrate.repository.secondary.LocationsOraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsOraServiceImpl implements LocationsOraService{

    private final LocationsOraRepository locationsOraRepository;

    @Autowired
    public LocationsOraServiceImpl(LocationsOraRepository locationsOraRepository) {
        this.locationsOraRepository = locationsOraRepository;
    }

    @Override
    public List<LocationsOra> persistLocationsOra(List<LocationsOra> locationsOraList) {
        return locationsOraRepository.saveAll(locationsOraList);
    }
}