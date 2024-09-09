package com.riwi.artemisa.application.ports.input.CRUD;

import java.util.List;

public interface ReadAll<Entity>{
    public List<Entity> findAll();
}
