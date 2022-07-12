package com.migrate.entity.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString //Added just for testing. TODO : Remove
public class RegionsOra {

    @Id
    //@GeneratedValue
    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "region_name")
    private String regionName;
}