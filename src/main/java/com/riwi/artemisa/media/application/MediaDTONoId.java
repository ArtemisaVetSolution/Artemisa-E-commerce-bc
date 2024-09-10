package com.riwi.artemisa.media.application;

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
public class MediaDTONoId {
        private String url;
        private String type;
        private Product productId;
        private Medication medicationId;
}
