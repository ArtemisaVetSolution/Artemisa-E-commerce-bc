package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.domain.models.MedicationModel;

import java.util.List;
import java.util.Optional;

public interface MedicationPersistencePort {

    Optional<MedicationModel> findByName(String name);

    List<MedicationModel> findAll();

    MedicationModel save(MedicationModel medicationModel);

    MedicationModel update(String name, MedicationModel medicationModel);


}
