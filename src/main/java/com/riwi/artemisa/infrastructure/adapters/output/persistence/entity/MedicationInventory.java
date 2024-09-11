package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Builder
@Entity(name = "medication_inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "prescribed", nullable = false)
    private boolean prescribed;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "method_use", nullable = false)
    private String methodUse;

    @Column(name = "supplier", nullable = false)
    private String supplier;


    @Column(name = "supplier_price", nullable = false)
    private float supplierPrice;

    @Column(name = "selling_price", nullable = false)
    private float sellingPrice;

    @Column(name="due_date")
    private Date dueDate;

    @Column(name="state_medication", nullable = false)
    private boolean stateMedication = true;

    @OneToOne(mappedBy="medicationInventory", fetch = FetchType.EAGER)
    private Medication medication;

}