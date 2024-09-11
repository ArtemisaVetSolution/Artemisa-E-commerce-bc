package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventoryResponse {

    private Long id;
    private int stock;
    private float sellingPrice;
    private ProductResponse product;

}
