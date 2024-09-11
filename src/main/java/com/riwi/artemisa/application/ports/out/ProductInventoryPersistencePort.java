package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.Save;
import com.riwi.artemisa.domain.models.ProductInventoryModel;

public interface ProductInventoryPersistencePort extends
        Save<ProductInventoryModel> {
}
