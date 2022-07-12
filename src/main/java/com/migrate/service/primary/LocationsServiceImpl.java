package com.migrate.service.primary;

import com.migrate.entity.primary.Locations;
import com.migrate.repository.primary.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsServiceImpl implements LocationsService{
    private final LocationsRepository locationsRepository;

    @Autowired
    public LocationsServiceImpl(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Override
    public List<Locations> fetchAllLists() {
        return locationsRepository.findAll();
    }
}