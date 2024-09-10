package com.riwi.artemisa.domain.models;


import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MedicationInventoryModel {

    private Long id;
    private LocalDate updateDate;
    private boolean prescribed;
    private int stock;
    private String methodUse;
    private String supplier;
    private float supplierPrice;
    private float sellingPrice;
    private LocalDate dueDate;
    private boolean stateMedication;


    public void updateStock(int quantity) {
        this.stock += quantity;
        this.updateDate = LocalDate.now();
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(this.dueDate);
    }


}
