package com.riwi.artemisa.media.application;

import com.riwi.artemisa.application.ports.input.category.MedicationInventoryServicePort;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.StatusOrder;
import com.riwi.artemisa.media.infrastructure.MediaDTONoId;
import com.riwi.artemisa.media.infrastructure.MediaDTOWithId;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MediaService implements InputMediaUseCaseInterface {
    @Autowired
    private MedicationInventoryServicePort medicationRepository;
    @Autowired
    private final OutputMediaRepository outputMediaRepository;


    public MediaService(OutputMediaRepository outputMediaRepository) {
        this.outputMediaRepository = outputMediaRepository;
    }

    private MediaDTOWithId mapToMediaDTOWithId(StatusOrder.Media media) {
        return new MediaDTOWithId(
                media.getId(),
                media.getUrl(),
                media.getType(),
                media.getProductId() != null ? media.getProductId() : null,
                media.getMedicationId() != null ? media.getMedicationId() : null
        );
    }
    @Override
    public MediaDTOWithId createMedia(MediaDTONoId mediaDTONoId) {

        if (mediaDTONoId.getProductId() == null && mediaDTONoId.getMedicationId() == null) {
            throw new IllegalArgumentException("MediaCore is not associated with products or medications");
        }

        if (mediaDTONoId.getProductId() != null && mediaDTONoId.getMedicationId() != null) {
            throw new IllegalArgumentException("MediaCore is associated with both products and medications");
        }

        StatusOrder.Media media = new StatusOrder.Media();
        media.setUrl(mediaDTONoId.getUrl());
        media.setType(mediaDTONoId.getType());

        if (mediaDTONoId.getProductId() != null) {
            //Hacer validacion
            media.setProductId(mediaDTONoId.getProductId());
        }

        if (mediaDTONoId.getMedicationId() != null) {
            var matchValidator = medicationRepository.findById(mediaDTONoId.getMedicationId());
            if (matchValidator.equals(null)) {
                throw new EntityNotFoundException("Medication not found");
            }
            media.setMedicationId(mediaDTONoId.getMedicationId());
        }

        StatusOrder.Media savedMedia = outputMediaRepository.save(media);
        return mapToMediaDTOWithId(savedMedia);
    }


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
        StatusOrder.Media media = outputMediaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MediaCore not found"));
        media.setUrl(mediaDTOWithId.getUrl());
        media.setType(mediaDTOWithId.getType());
        media.setProductId(mediaDTOWithId.getProductId());
        media.setMedicationId(mediaDTOWithId.getMedicationId());
        outputMediaRepository.save(media);
        return mediaDTOWithId;
    }

    @Override
    public void deleteMedia(Long id) {
        outputMediaRepository.deleteById(id);
    }
}
