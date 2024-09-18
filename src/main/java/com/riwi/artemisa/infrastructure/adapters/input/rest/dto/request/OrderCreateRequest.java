package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequest {

    @NotNull(message = "The id user is required")
    private Long idUser;

    @NotNull(message = "the order details us required")
    private List<OrderDetailsCreateRequest> orderDetails;

}
