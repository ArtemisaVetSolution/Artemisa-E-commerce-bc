package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "medications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medication extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;


    @OneToMany(mappedBy = "medication", fetch = FetchType.LAZY)  // Corrected mappedBy value
    private List<Media> medias;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(targetEntity = MedicationInventory.class)
    @JoinColumn(name = "medication_inventory")
    private MedicationInventory medicationInventory;

    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;


