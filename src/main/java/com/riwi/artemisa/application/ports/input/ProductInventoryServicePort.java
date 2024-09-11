package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.application.ports.CRUD.Save;
import com.riwi.artemisa.domain.models.ProductInventoryModel;

public interface ProductInventoryServicePort extends
        Save<ProductInventoryModel> {}
