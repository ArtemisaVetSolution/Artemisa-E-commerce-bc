package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToOne(targetEntity = ProductInventory.class)
    @JoinColumn(name = "product_inventory")
    private ProductInventory productInventory;


    @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category categoryId;


    @OneToMany(mappedBy = "productId", fetch = FetchType.EAGER)
    private List<Media> media;

    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;
}
