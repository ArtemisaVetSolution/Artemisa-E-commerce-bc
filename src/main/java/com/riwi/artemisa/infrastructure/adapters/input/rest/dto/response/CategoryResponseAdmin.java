package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.domain.models.ProductModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseAdmin {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean deleted ;

}
