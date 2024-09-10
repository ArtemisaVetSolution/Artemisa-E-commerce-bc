package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.MedicationServicePort;
import com.riwi.artemisa.application.ports.out.MedicationPersistencePort;
import com.riwi.artemisa.domain.exception.MedicationNotFoundException;
import com.riwi.artemisa.domain.models.MedicationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MedicationService implements MedicationServicePort {

    private final MedicationPersistencePort persistencePort;

    @Override
    public MedicationModel findByName(String name) {
        return persistencePort.findByName(name)
                .orElseThrow();
    }

    @Override
    public List<MedicationModel> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public MedicationModel save(MedicationModel medicationModel) {
        return persistencePort.save(medicationModel);
    }

    @Override
    public MedicationModel update(String name, MedicationModel medication) {
        return persistencePort.findByName(name)
                .map(medicationDB ->{
                    medicationDB.setName(medication.getName());
                    medicationDB.setDescription(medication.getDescription());
                    return persistencePort.save(medicationDB);
                }).orElseThrow(MedicationNotFoundException::new);
    }
}
