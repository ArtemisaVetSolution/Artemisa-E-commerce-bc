package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import com.riwi.artemisa.media.domain.Media;
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
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "medication", fetch = FetchType.LAZY)
    private List<Media> medias;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @ManyToOne(targetEntity = MedicationInventory.class)
    @JoinColumn(name = "medication_inventory")
    private MedicationInventory medicationInventory;

    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

}