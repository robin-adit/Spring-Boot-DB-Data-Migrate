package com.migrate.repository.primary;

import com.migrate.entity.primary.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees,Long> {
}
