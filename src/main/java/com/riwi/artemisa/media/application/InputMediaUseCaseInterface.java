package com.riwi.artemisa.media.application;

import com.riwi.artemisa.media.infrastructure.MediaDTONoId;
import com.riwi.artemisa.media.infrastructure.MediaDTOWithId;

import java.util.Optional;

public interface InputMediaUseCaseInterface {

    public MediaDTONoId createMedia(MediaDTOWithId mediaDTOWithId);

    public Optional<MediaDTOWithId> getMediaByProductId(Long id);

    public Optional<MediaDTOWithId> getMediaByMedicationId(Long id);

    public MediaDTOWithId updateMedia(Long id, MediaDTOWithId mediaDTOWithId);

    public void deleteMedia(Long id);

}
