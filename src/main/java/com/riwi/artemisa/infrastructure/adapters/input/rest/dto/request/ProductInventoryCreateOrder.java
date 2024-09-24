package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInventoryCreateOrder {

    private Long Id;

}
