package com.riwi.artemisa.application.ports.input;



import java.util.List;

public interface MedicationInventoryServicePort {
    MedicationInventoryModel findById(Long id);
    List<MedicationInventoryModel> findAll();
    MedicationInventoryModel save(MedicationInventoryModel medicationInventoryModel);
    MedicationInventoryModel update(Long id, MedicationInventoryModel medicationInventoryModel);
    void deleteById(Long id);
}
