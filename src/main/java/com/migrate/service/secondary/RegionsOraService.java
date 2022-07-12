package com.migrate.service.secondary;

import com.migrate.entity.secondary.RegionsOra;

import java.util.List;

public interface RegionsOraService {
    public List<RegionsOra> persistRegionsOra(List<RegionsOra> regionsOraList);
}
