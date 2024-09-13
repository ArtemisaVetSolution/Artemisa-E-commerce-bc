package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.MediaPersistenPort;
import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.MediaPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MediaPersisterAdapter implements MediaPersistenPort {

    private final MediaRepository repository;
    private final MediaPersistenceMapper mapper;

    @Override
    public MediaModel save(MediaModel mediaModel) {
        return mapper.toMediaModel(repository.save(mapper.toMedia(mediaModel)));
    }


    @Override
    public String deletebyId(Long aLong) {
        return null;
    }

    @Override
    public List<MediaModel> findAll() {
        return List.of();
    }

    @Override
    public MediaModel readById(Long aLong) {
        return null;
    }

    @Override
    public MediaModel update(Long aLong, MediaModel mediaModel) {
        return null;
    }
}
