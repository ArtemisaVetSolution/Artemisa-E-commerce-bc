package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity(name = "product_inventory")
@Table(name = "product_inventory")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(name = "supplier", nullable = false)
    private String supplier;

    @Column(name = "supplier_price", nullable = false)
    private float supplierPrice;

    @Column(name = "selling_price", nullable = false)
    private float sellingPrice;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "state_product", nullable = false)
    private boolean stateProduct;

    @OneToOne
    @JoinColumn(name = "productinventory_id")
    private Product product;

}