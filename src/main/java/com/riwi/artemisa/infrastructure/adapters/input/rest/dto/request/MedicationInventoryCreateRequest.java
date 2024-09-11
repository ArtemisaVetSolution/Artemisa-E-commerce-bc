package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import com.riwi.artemisa.domain.models.MedicationModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventoryCreateRequest {

    @NotNull(message = "The update date of the medication is required")
    private Date updateDate;
    @NotNull(message = "The prescribed of the medication is required")
    private boolean prescribed;
    @NotNull(message = "The stock of the medication is required")
    private int stock;
    @NotNull(message = "The method Use of the medication is required")
    private String methodUse;
    @NotBlank(message = "The supplier of the product is required")
    private String supplier;
    @NotNull(message = "The supplier price of the product is required")
    private float supplierPrice;
    @NotNull(message = "The selling price of the product is required")
    private float sellingPrice;
    @NotNull(message = "The dueDate of the product is required")
    private Date dueDate;
    @NotNull(message = "The medication is required")
    @Valid
    private MedicationCreateRequest medication;
}
