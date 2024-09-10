package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends Auditable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "categoryId",fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany(mappedBy = "categoryId",fetch = FetchType.LAZY)
    private List<Medication> medication;

}