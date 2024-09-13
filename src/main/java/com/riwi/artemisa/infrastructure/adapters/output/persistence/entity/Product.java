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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<Media> media;
}
