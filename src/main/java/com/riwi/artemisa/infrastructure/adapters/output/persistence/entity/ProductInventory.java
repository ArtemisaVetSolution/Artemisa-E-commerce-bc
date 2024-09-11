package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity(name = "product_inventoty")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "spplier", nullable = false)
    private String supplier;

    @Column(name = "supplier_price", nullable = false)
    private float supplierPrice;

    @Column(name = "selling_price", nullable = false)
    private float sellingPrice;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "state_product", nullable = false)
    private boolean stateProduct;

    @OneToMany(mappedBy = "productInventory",fetch = FetchType.LAZY)
    private List<Product> product;

}