package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.MediaServicePort;
import com.riwi.artemisa.application.ports.out.MediaPersistenPort;
import com.riwi.artemisa.domain.models.MediaModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaSerivice implements MediaServicePort {

    private final MediaPersistenPort mediaPersistenPort;

    @Override
    public MediaModel save(MediaModel mediaModel) {
        return mediaPersistenPort.save(mediaModel);
    }
}
