package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Category;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MedicationCreateRequest {
    private String name;
    private String description;
    private List<Media> medias;
    private Category categoryId;
}
