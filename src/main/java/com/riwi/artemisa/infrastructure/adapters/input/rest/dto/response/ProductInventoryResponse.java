package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventoryResponse {

    private Long id;
    private int stock;
    private LocalDate updateDate;
    private float sellingPrice;
    private boolean stateProduct;
    private ProductResponse product;

}
