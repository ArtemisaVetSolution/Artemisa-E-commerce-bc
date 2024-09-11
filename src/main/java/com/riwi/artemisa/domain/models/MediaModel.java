package com.riwi.artemisa.domain.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaModel {

    private Long id;
    private String type;
    private String url;
    private ProductModel productModel;
    private Medication medication;

}
