
package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "medias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Media extends Auditable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "url", nullable = false)
    private String url;

    @Column ( name = "type", nullable = false)
    private String type;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne(targetEntity = Medication.class)
    @JoinColumn(name = "medication_id")
    private Medication medication;

}