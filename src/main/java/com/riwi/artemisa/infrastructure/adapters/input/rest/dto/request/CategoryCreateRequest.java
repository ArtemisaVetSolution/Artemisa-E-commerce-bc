package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequest {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotEmpty
    private String description;

}
