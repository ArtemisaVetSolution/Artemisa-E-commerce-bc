package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

//    @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY)
//    private List<Product> products;

//    @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY)
//    private List<Medication> medication;

}