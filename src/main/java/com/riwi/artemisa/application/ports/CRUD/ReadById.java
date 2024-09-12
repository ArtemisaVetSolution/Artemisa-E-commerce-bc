package com.riwi.artemisa.application.ports.CRUD;

import com.riwi.artemisa.domain.models.MediaModel;

import java.util.Optional;

public interface ReadById<Entity,ID>{
    Optional<MediaModel> findById(ID id);

}
