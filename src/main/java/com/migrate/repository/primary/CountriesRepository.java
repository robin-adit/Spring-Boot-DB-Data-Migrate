package com.migrate.repository.primary;

import com.migrate.entity.primary.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<Countries,String> {
}
