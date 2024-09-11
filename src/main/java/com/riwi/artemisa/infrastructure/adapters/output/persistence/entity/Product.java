package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "product", targetEntity = ProductInventory.class, fetch = FetchType.LAZY)
    private ProductInventory productInventory;  // Revisa este mapeo

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "media", fetch = FetchType.EAGER)
    private List<Media> media;

    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;
}
