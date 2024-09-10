package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import com.riwi.artemisa.media.domain.Media;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "products")
@Getter
@Setter
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

    @ManyToOne(targetEntity = ProductInventory.class)
    @JoinColumn(name = "product_inventory_id")
    private ProductInventory productInventory;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY)
    private List<Media> media;

    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

}