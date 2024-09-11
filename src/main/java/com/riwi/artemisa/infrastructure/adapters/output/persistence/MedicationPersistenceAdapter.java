package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.MedicationPersistencePort;
import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.MedicationPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicationPersistenceAdapter implements MedicationPersistencePort {

    private final MedicationRepository repository;
    private final MedicationPersistenceMapper mapper;
    @Override
    public MedicationModel save(MedicationModel medicationModel) {
        return mapper.toMedicationModel(repository.save(mapper.toMedication(medicationModel)));
    }
}
