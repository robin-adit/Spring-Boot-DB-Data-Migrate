package com.migrate.service.secondary;

import com.migrate.entity.secondary.LocationsOra;
import com.migrate.entity.secondary.WarehousesOra;
import com.migrate.repository.secondary.LocationsOraRepository;
import com.migrate.repository.secondary.WarehousesOraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehousesOraServiceImpl implements WarehousesOraService{

    private final WarehousesOraRepository warehousesOraRepository;

    @Autowired
    public WarehousesOraServiceImpl(WarehousesOraRepository warehousesOraRepository) {
        this.warehousesOraRepository = warehousesOraRepository;
    }

    @Override
    public List<WarehousesOra> persistWarehousesOra(List<WarehousesOra> locationsOraList) {
        return warehousesOraRepository.saveAll(locationsOraList);
    }
}