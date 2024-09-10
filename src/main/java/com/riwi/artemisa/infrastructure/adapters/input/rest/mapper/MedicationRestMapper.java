package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponse;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationRestMapper {

    MedicationModel toMedication(MedicationCreateRequest request);

    MedicationResponse toMedicationResponse(MedicationModel medication);

    List<MedicationResponse> toMedicationResponseList(List<MedicationModel> medicationModelList);

}
