package com.migrate.service.primary;

import com.migrate.entity.primary.Warehouses;
import com.migrate.repository.primary.WarehousesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehousesServiceImpl implements WarehousesService{
    private final WarehousesRepository warehousesRepository;

    @Autowired
    public WarehousesServiceImpl(WarehousesRepository warehousesRepository) {
        this.warehousesRepository = warehousesRepository;
    }

    @Override
    public List<Warehouses> fetchAllLists() {
        return warehousesRepository.findAll();
    }
}