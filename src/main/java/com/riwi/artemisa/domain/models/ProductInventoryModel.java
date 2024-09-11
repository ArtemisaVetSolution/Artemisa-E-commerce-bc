package com.riwi.artemisa.domain.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventoryModel {

    private Long id;
    private int stock;
    private LocalDate updateDate;
    private String supplier;
    private float supplierPrice;
    private float sellingPrice;
    private LocalDate dueDate;
    private boolean stateProduct;
    private ProductModel product;

}
