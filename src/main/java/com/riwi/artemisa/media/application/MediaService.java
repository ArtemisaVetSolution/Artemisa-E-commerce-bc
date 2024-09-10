package com.riwi.artemisa.media.application;

import java.util.Optional;

public class MediaService implements InputMediaUseCaseInterface {
    @Override
    public Optional<MediaDTOWithId> getMediaByProductId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<MediaDTOWithId> getMediaByMedicationId(Long id) {
        return Optional.empty();
    }

    @Override
    public MediaDTOWithId updateMedia(Long id, MediaDTOWithId mediaDTOWithId) {
        return null;
    }

    @Override
    public void deleteMedia(Long id) {

    }

    private final OutputMediaRepository outputMediaRepository;

    public MediaService(OutputMediaRepository outputMediaRepository) {
        this.outputMediaRepository = outputMediaRepository;
    }

    @Override
    public MediaDTONoId createMedia(MediaDTOWithId mediaDTOWithId) {
        return null;
    }
}
