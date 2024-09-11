package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.domain.models.Medication;

import java.util.List;

public interface MedicationServicePort {

    Medication findByName(String name);
    List<Medication> findAll();
    Medication save(Medication medication);
    Medication update(String name, Medication medication);
    void deleteByName(String name);
}
