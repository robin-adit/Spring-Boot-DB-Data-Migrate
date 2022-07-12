package com.migrate.entity.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString //Added just for testing. TODO : Remove
public class ProductsOra {

    @Id
    @Column(name="product_id")
    private Long productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="description")
    private String description;

    @Column(name="standard_cost")
    private Long standardCost;

    @Column(name="list_price")
    private Long listPrice;

    @Column(name="category_id")
    private Long categoryId;
}