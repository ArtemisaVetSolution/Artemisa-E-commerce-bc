package com.riwi.artemisa.domain.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventoryModel {

    private long id;
    private boolean prescribed;
    private int stock;
    private String methodUse;
    private String supplier;
    private float supplierPrice;
    private float sellingPrice;
    private Date dueDate;
    private boolean stateMedication = true;
    private MedicationModel medication;

}
