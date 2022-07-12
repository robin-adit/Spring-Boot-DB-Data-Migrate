package com.migrate.entity.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="locations")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString //Added just for testing. TODO : Remove
public class LocationsOra {

    @Id
    //@GeneratedValue
    @Column(name="location_id")
    private Long locationId;

    @Column(name="address")
    private String address;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country_id")
    private String countryId;
}