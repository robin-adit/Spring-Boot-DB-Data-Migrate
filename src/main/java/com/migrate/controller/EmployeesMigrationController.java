package com.migrate.controller;

import com.migrate.entity.primary.Employees;
import com.migrate.entity.secondary.EmployeesOra;
import com.migrate.service.primary.EmployeesService;
import com.migrate.service.secondary.EmployeesOraService;
import com.migrate.utility.CommonConstantsIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class EmployeesMigrationController implements CommonConstantsIF {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private EmployeesOraService employeesOraService;

    @GetMapping(value = "/activateEmployeesScan/{activationToken}")
    public List<Employees> ActivateEmployeesScan(@PathVariable("activationToken") String activationToken)
    {
        if(null != activationToken && activationToken.equalsIgnoreCase(CommonConstantsIF.VALIDATION_TOKEN))
        {
            List<Employees> employeesList = employeesService.fetchAllLists();
            logger.info("Employees fetched =" + employeesList.size());
            employeesList.forEach(employees -> logger.info(employees.toString()));
            return employeesList;
        }
        else
            return null;
    }

    private List<EmployeesOra> convertEmployeesDataForMigration(List<Employees> employeesList)
    {
        List<EmployeesOra> employeesOraList = new ArrayList<EmployeesOra>();
        for(Employees employees : employeesList)
        {
            EmployeesOra employeesOra = DataMigrationController.modelMapper().map(employees,EmployeesOra.class);
            employeesOraList.add(employeesOra);
        }

        logger.info("Converted Employees=" + employeesOraList.size());
        employeesOraList.forEach(employeesOra -> logger.info(employeesOra.toString()));

        return employeesOraList;
    }

    private List<EmployeesOra> ActivateEmployeesTransfer(List<EmployeesOra> employeesOraList)
    {

        List<EmployeesOra> presidentList = employeesOraList
                .stream()
                .filter(employeesOra -> null == employeesOra.getManagerId()).collect(Collectors.toList());
        presidentList = employeesOraService.persistEmployeesOra(presidentList);
        List<EmployeesOra> employeesOraListFinal = new ArrayList<EmployeesOra>(presidentList);
        for(EmployeesOra subEmployeeOra : presidentList)
        {
            employeesOraListFinal.addAll(
            AddAllEmployeesForThisManager(employeesOraList,
                    subEmployeeOra.getEmployeeId(),
                    employeesOraListFinal));
        }
        return employeesOraListFinal;
    }

    private List<EmployeesOra> AddAllEmployeesForThisManager(List<EmployeesOra> allEmployeesOraList,
                                               Long managerEmployeeId,
                                               List<EmployeesOra> finalEmployeeOraList)
    {
        List<EmployeesOra> tempEmployeesOraList =
                allEmployeesOraList.stream()
                        .filter(employeesOra ->
                                Objects.equals(managerEmployeeId, employeesOra
                                        .getManagerId()))
                                                        .collect(Collectors.toList());

        logger.info("For ManagerID = " + managerEmployeeId + " found" + tempEmployeesOraList.size());
        finalEmployeeOraList.addAll(employeesOraService.persistEmployeesOra(tempEmployeesOraList));
        logger.info("Final List Size = " + finalEmployeeOraList.size());
        for(EmployeesOra employeesOra : tempEmployeesOraList)
        {

          AddAllEmployeesForThisManager(allEmployeesOraList,
                                        employeesOra.getEmployeeId(),
                                        finalEmployeeOraList);
        }

        return finalEmployeeOraList;
    }
    @GetMapping(value = "/migrateEmployees/{activationToken}")
    public List<EmployeesOra> migrateEmployees(@PathVariable("activationToken") String activationToken)
    {
        List<Employees> employeesList = ActivateEmployeesScan(activationToken);

        if (!employeesList.isEmpty())
        {
            List<EmployeesOra> employeesOraList = convertEmployeesDataForMigration(employeesList);
            return ActivateEmployeesTransfer(employeesOraList);
        }
        else
            return null;
    }
}