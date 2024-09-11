package com.riwi.artemisa.application.ports.CRUD;

import java.util.Optional;

public interface ReadById<Entity,ID>{
    public Optional<InventoryMedicationModel> findById(ID id);
}
