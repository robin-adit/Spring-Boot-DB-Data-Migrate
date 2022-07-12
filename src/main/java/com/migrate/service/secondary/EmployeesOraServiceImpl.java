package com.migrate.service.secondary;

import com.migrate.entity.secondary.EmployeesOra;
import com.migrate.repository.secondary.EmployeesOraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesOraServiceImpl implements EmployeesOraService{

    private final EmployeesOraRepository employeesOraRepository;

    @Autowired
    public EmployeesOraServiceImpl(EmployeesOraRepository employeesOraRepository) {
        this.employeesOraRepository = employeesOraRepository;
    }

    @Override
    public List<EmployeesOra> persistEmployeesOra(List<EmployeesOra> employeesOraList) {
        return employeesOraRepository.saveAll(employeesOraList);
    }
}