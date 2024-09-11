package com.riwi.artemisa.domain.models;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel extends Auditable{

    private Long id;
    private String name;
    private String description;
    private List<ProductModel> products;
    private List<Medication> medications;

    public boolean isControlledSubstance() {
        return name.toLowerCase().contains("controlled");
    }
}