package com.riwi.artemisa.application.ports.CRUD;

import com.riwi.artemisa.domain.models.ProductInventoryModel;

import java.util.List;

public interface ReadAllByName<Entity, Name> {
    public List<Entity> findAllByName(Name name);
}
