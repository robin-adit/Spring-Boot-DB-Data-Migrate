package com.migrate.service.secondary;

import com.migrate.entity.secondary.RegionsOra;
import com.migrate.repository.secondary.RegionsOraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionsOraServiceImpl implements RegionsOraService{

    private final RegionsOraRepository regionsOraRepository;

    @Autowired
    public RegionsOraServiceImpl(RegionsOraRepository regionsOraRepository) {
        this.regionsOraRepository = regionsOraRepository;
    }

    @Override
    public List<RegionsOra> persistRegionsOra(List<RegionsOra> regionsOraList) {
        return regionsOraRepository.saveAll(regionsOraList);
    }
}