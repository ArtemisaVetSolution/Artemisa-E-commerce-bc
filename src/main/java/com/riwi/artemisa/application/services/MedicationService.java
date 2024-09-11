package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.MedicationServicePort;
import com.riwi.artemisa.application.ports.out.MedicationPersistencePort;
import com.riwi.artemisa.domain.models.MedicationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationService  implements MedicationServicePort {

    private final MedicationPersistencePort persistencePort;

    @Override
    public MedicationModel save(MedicationModel medicationModel) {
        return persistencePort.save(medicationModel);
    }
}
