package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.MediaModel;

import java.util.Optional;

public interface MediaServicePort extends
        Save<MediaModel>
        , Delete<Long>
        , ReadAll<MediaModel>
        , Update<MediaModel, Long> {
    MediaModel findById(Long id);
}
