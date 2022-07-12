package com.migrate.repository.secondary;

import com.migrate.entity.primary.Countries;
import com.migrate.entity.secondary.CountriesOra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesOraRepository extends JpaRepository<CountriesOra,String> {
}
