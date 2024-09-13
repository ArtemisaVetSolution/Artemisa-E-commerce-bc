package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.Save;
import com.riwi.artemisa.domain.models.MedicationModel;


public interface MedicationPersistencePort extends
        Save<MedicationModel> {
        MedicationModel save(MedicationModel medicationModel);

}
