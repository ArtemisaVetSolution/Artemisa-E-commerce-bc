package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.application.ports.CRUD.Save;
import com.riwi.artemisa.domain.models.MedicationModel;

public interface MedicationServicePort extends
        Save<MedicationModel> {
}
