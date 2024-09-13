package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.MedicationInventoryServicePort;
import com.riwi.artemisa.application.ports.out.MedicationInventoryPersistencePort;
import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationInventoryService implements MedicationInventoryServicePort {

    private final MedicationInventoryPersistencePort persistencePort;


    @Override
    public MedicationInventoryModel save(MedicationInventoryModel medicationInventoryModel) {
        return persistencePort.save(medicationInventoryModel);
    }
}

