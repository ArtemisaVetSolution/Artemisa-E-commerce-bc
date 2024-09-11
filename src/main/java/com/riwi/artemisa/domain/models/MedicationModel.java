package com.riwi.artemisa.domain.models;


import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
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
    private List<Media> medias;
    private CategoryModel category;
}