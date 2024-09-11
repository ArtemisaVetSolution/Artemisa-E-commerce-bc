package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationCreateRequest {
    private String name;
    private String description;
    private List<Media> medias;
    private CategoryCreateRequest category;
}
