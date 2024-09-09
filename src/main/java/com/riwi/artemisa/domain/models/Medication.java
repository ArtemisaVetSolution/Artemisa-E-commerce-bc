package com.riwi.artemisa.domain.models;


import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    private Long id;
    private String name;
    private String description;
    private CategoryModel categoryModel;

    public boolean requiresPrescription() {
        return name.toLowerCase().contains("prescription");
    }

}