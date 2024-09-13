package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationInventoryRestMapper {

    MedicationInventoryModel toMedicationInventoryModel(MedicationInventoryCreateRequest medicationInventoryCreateRequest);

    MedicationInventoryResponse toMedicationInventoryResponse(MedicationInventoryModel medicationInventoryModel);

    List<MedicationInventoryResponse> toMedicationInventoryResponseList(List<MedicationInventoryModel> medicationInventoryModels);
}
