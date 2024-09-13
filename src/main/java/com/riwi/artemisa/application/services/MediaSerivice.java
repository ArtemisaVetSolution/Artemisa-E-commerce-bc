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
    public MediaModel findById(Long id) {
        return null;
    }

    @Override
    public String deletebyId(Long aLong) {
        return "";
    }

    @Override
    public List<MediaModel> findAll() {
        return List.of();
    }

    @Override
    public MediaModel save(MediaModel mediaModel) {
        return null;
    }

    @Override
    public MediaModel update(Long aLong, MediaModel mediaModel) {
        return null;
    }
}
