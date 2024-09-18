package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.OrderDetailsCreateRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Long id;

    private float totalOrder;

    private List<OrderDetailsResponse> orderDetails;

}
