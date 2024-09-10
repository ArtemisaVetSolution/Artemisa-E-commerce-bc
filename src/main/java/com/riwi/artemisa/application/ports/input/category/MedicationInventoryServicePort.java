package com.riwi.artemisa.application.ports.input.category;



import com.riwi.artemisa.domain.models.MedicationInventory;

import java.util.List;

public interface MedicationInventoryServicePort {
    MedicationInventory findById(Long id);
    List<MedicationInventory> findAll();
    MedicationInventory save(MedicationInventory medicationInventory);
    MedicationInventory update(Long id, MedicationInventory medicationInventory);
    void deleteById(Long id);
}
