package com.migrate.service.secondary;

import com.migrate.entity.secondary.RegionsOra;
import com.migrate.entity.secondary.WarehousesOra;

import java.util.List;

public interface WarehousesOraService {
    public List<WarehousesOra> persistWarehousesOra(List<WarehousesOra> regionsOraList);
}