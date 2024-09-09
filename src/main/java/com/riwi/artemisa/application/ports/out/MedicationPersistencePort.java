package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.domain.models.Medication;

import java.util.List;
import java.util.Optional;

public interface MedicationPersistencePort {

    Optional<Medication> findByName(String name);

    List<Medication> findAll();

    Medication save(Medication medication);


}
