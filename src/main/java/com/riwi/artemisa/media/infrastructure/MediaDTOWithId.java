package com.riwi.artemisa.media.infrastructure;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Medication;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTOWithId {
    private long id;
    private String url;
    private String type;
    private Product productId;
    private Medication medicationId;
}
