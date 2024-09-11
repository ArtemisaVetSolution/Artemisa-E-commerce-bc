package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventoryResponse {

    private boolean prescribed;
    private int stock;
    private String methodUse;
    private float sellingPrice;
    private boolean StateMedication;
    private MedicationResponse medication;
}
