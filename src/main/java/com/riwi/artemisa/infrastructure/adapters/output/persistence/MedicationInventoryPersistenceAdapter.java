package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.MedicationInventoryPersistencePort;
import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.*;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.MedicationInventoryPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.CategoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MediaRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MedicationInventoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MedicationInventoryPersistenceAdapter implements MedicationInventoryPersistencePort {

    private final MedicationInventoryRepository repository;
    private final MedicationInventoryPersistenceMapper mapper;
    private final CategoryRepository categoryRepository;
    private final MediaRepository mediaRepository;
    private final MedicationRepository medicationRepository;

    @Override
    public MedicationInventoryModel save(MedicationInventoryModel medicationInventoryModel) {

        // Crear y guardar la entidad Medication
        Medication medication = Medication.builder()
                .name(medicationInventoryModel.getMedication().getName())
                .description(medicationInventoryModel.getMedication().getDescription())
                .build();

        // Establecer la categoría
        Category category = categoryRepository.findById(
                        medicationInventoryModel.getMedication().getCategory().getId())
                .orElseThrow(RuntimeException::new);
        medication.setCategoryId(category);

        // Guardar media
        List<Media> media = medicationInventoryModel.getMedication().getMedia().stream()
                .map(mediaModel -> Media.builder()
                        .type(mediaModel.getType())
                        .url(mediaModel.getUrl())
                        .build())
                .toList();
        media = mediaRepository.saveAll(media);
        medication.setMedia(media);

        // Guardar el medicamento
        Medication savedMedication = medicationRepository.save(medication);

        // Crear y guardar la entidad MedicationInventory
        MedicationInventory medicationInventory = MedicationInventory.builder()
                .updateDate(medicationInventoryModel.getUpdateDate())
                .stock(medicationInventoryModel.getStock())
                .methodUse(medicationInventoryModel.getMethodUse())
                .supplier(medicationInventoryModel.getSupplier())
                .supplierPrice(medicationInventoryModel.getSupplierPrice())
                .sellingPrice(medicationInventoryModel.getSellingPrice())
                .dueDate(medicationInventoryModel.getDueDate())
                .stateMedication(true)
                .medication(savedMedication) // Establece la relación con Medication
                .build();

        // Guardar el inventario de medicamentos
        MedicationInventory savedMedicationInventory = repository.save(medicationInventory);

        // Establecer la relación bidireccional
        savedMedication.setMedicationInventory(savedMedicationInventory);
        medicationRepository.save(savedMedication); // Actualizar el medicamento con la relación

        // Mapear el inventario guardado al modelo
        return mapper.toMedicationInventoryModel(savedMedicationInventory);
    }
}
