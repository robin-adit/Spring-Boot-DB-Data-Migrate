package com.migrate.service.primary;

import com.migrate.entity.primary.Regions;
import com.migrate.repository.primary.RegionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionsServiceImpl implements RegionsService {

    private final RegionsRepository regionsRepository;

    @Autowired
    public RegionsServiceImpl(RegionsRepository regionsRepository) {
        this.regionsRepository = regionsRepository;
    }

    @Override
    public List<Regions> fetchAllLists() {
        return regionsRepository.findAll();
    }
}