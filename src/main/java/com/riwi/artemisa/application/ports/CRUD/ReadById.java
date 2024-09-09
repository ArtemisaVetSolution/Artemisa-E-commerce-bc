package com.riwi.artemisa.application.ports.CRUD;

public interface ReadById<Entity,ID>{
    public Entity findById(ID id);
}
