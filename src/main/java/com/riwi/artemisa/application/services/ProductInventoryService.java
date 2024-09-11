package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.ProductInventoryServicePort;
import com.riwi.artemisa.application.ports.out.ProductInventoryPersistencePort;
import com.riwi.artemisa.domain.models.ProductInventoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductInventoryService implements ProductInventoryServicePort {

    private final ProductInventoryPersistencePort productInventoryPersistencePort;

    @Override
    public ProductInventoryModel save(ProductInventoryModel productInventoryModel) {
        return productInventoryPersistencePort.save(productInventoryModel);
    }
}
