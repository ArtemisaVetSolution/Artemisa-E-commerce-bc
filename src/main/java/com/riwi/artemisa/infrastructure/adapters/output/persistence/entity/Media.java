package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity(name = "medias")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column ( name = "type", nullable = false)
    private String type;

    @Column (name = "url", nullable = false)
    private String url;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne(targetEntity = Medication.class)
    @JoinColumn(name = "medication_id")
    private Medication medication;

}

