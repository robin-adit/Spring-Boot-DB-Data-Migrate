package com.migrate.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="warehouses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString //Added just for testing. TODO : Remove
public class Warehouses {

    @Id
    //@GeneratedValue
    @Column(name="warehouse_id")
    private Long warehouseId;

    @Column(name="warehouse_name")
    private String warehouseName;

    @Column(name="location_id")
    private Long locationId;
}