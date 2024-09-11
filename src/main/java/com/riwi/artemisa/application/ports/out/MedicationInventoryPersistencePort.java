package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.Save;
import com.riwi.artemisa.domain.models.MedicationInventoryModel;

public interface MedicationInventoryPersistencePort extends
        Save<MedicationInventoryModel> {
}
