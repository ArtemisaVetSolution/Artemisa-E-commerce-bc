package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity(name = "petshop_payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetshopPayments {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    private float amount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

//    private List<Order> purchase_order;

}
