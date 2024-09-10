package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Medication;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationPersistenceMapper {

    Medication toMedication(MedicationModel medicationModel);

    MedicationModel toMedicationModel(Medication  medication);

    List<MedicationModel> toMedicationModels(List<Medication> medications);
}
