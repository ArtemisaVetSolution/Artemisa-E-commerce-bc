package com.riwi.artemisa.domain.models;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationModel{
    private Long id;
    private String name;
    private String description;
    private List<MediaModel> media;
    private CategoryModel category;
}