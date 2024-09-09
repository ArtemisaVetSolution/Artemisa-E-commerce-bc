package com.riwi.artemisa.application.ports.input.CRUD;

public interface ReadById<Entity,ID>{
    public Entity findById(ID id);
}
