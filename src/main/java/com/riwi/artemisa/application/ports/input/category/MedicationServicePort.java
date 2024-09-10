package com.riwi.artemisa.application.ports.input.category;

import com.riwi.artemisa.domain.models.MedicationModel;

import java.util.List;

public interface MedicationServicePort{

    MedicationModel findByName(String name);
    List<MedicationModel> findAll();
    MedicationModel save(MedicationModel medicationModel);
    MedicationModel update(String name, MedicationModel medicationModel);

}
