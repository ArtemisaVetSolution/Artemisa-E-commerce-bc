package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.MediaServicePort;
import com.riwi.artemisa.application.ports.out.MediaPersistenPort;
import com.riwi.artemisa.domain.exception.MedicationNotFoundException;
import com.riwi.artemisa.domain.models.MediaModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaSerivice implements MediaServicePort {

    private final MediaPersistenPort persistenPort;

    @Override
    public MediaModel save(MediaModel mediaModel) {
        return persistenPort.save(mediaModel);
    }

    @Override
    public void deletebyId(Long id) {
        if (persistenPort.findById(id) != null) {
            throw new RuntimeException();
        }
        persistenPort.deletebyId(id);
    }

    @Override
    public List<MediaModel> findAll() {
        return persistenPort.findAll();
    }

    @Override
    public MediaModel findById(Long id) {
        return persistenPort.findById(id).orElseThrow();
    }

    @Override
    public MediaModel update(Long id, MediaModel mediaModel) {
        return persistenPort.findById(id).map(
                 mediaDB->{
                    mediaDB.setUrl(mediaModel.getUrl());
                    mediaDB.setType(mediaModel.getType());
                    mediaDB.setMedication(mediaModel.getMedication());
                    mediaDB.setProductModel(mediaModel.getProductModel());
                    return persistenPort.save(mediaDB);
                }
        ).orElseThrow(MedicationNotFoundException::new);
    }
}
