package com.migrate.service.secondary;

import com.migrate.entity.secondary.EmployeesOra;

import java.util.List;

public interface EmployeesOraService {
    public List<EmployeesOra> persistEmployeesOra(List<EmployeesOra> employeesOraList);
}
