package com.riwi.artemisa.domain.models;

import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel extends Auditable{

    private Long id;
    private String name;
    private String description;

    public boolean isControlledSubstance() {
        return name.toLowerCase().contains("controlled");
    }
}