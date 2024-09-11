package com.riwi.artemisa.domain.models;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationModel extends Auditable{
    private Long id;
    private String name;
    private String description;
    private List<MedicationModel> medias;
    private CategoryModel category;
}