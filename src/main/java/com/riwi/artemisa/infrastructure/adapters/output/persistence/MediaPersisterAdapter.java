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
    public void deletebyId(Long id) {
        repository.findById(id);
    }

    @Override
    public List<MediaModel> findAll() {
        return mapper.toMediaModelList(repository.findAll());
    }

    @Override
    public Optional<MediaModel> findById(Long id) {
        return repository.findById(id).map(mapper::toMediaModel);
    }

    @Override
    public MediaModel update(Long id, MediaModel model) {
        return repository.findById(id).map(mapper::toMediaModel).orElseThrow();
    }
}
