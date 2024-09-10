package com.riwi.artemisa.media.application;

import com.riwi.artemisa.application.ports.input.category.MedicationInventoryServicePort;
import com.riwi.artemisa.media.domain.Media;
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

    private MediaDTOWithId mapToMediaDTOWithId(Media media) {
        return new MediaDTOWithId(
                media.getId(),
                media.getUrl(),
                media.getType(),
                media.getProductId() != null ? media.getProductId().getId() : null,
                media.getMedicationId() != null ? media.getMedicationId().getId() : null
        );
    }
    @Override
    public MediaDTONoId createMedia(MediaDTOWithId mediaDTOWithId) {

        if (mediaDTOWithId.getProductId() == null && mediaDTOWithId.getMedicationId() == null) {
            throw new IllegalArgumentException("Media is not associated with products or medications");
        }

        Media media = new Media();
        media.setUrl(mediaDTOWithId.getUrl());
        media.setType(mediaDTOWithId.getType());

        // Aquí simplemente establecemos los IDs sin resolver las entidades completas
//        if (mediaDTOWithId.getProductId() != null) {
//            Product product = new Product();
//            product.setId(mediaDTOWithId.getProductId());
//            media.setProductId(product);
//        }
//        if (mediaDTOWithId.getMedicationId() != null) {
//            Medication medication = new Medication();
//            medication.setId(mediaDTOWithId.getMedicationId());
//            media.setMedication(medication);
//        }

        //Agregarse cuando exista medicamentos y productos

//        if (mediaDTOWithId.getProductId() != null) {
//            Product product = productService.findById(mediaDTOWithId.getProductId())
//                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
//            media.setProductId(product);
//        }
//        if (mediaDTOWithId.getMedicationId() != null) {
//            Medication medication = medicationService.findById(mediaDTOWithId.getMedicationId())
//                    .orElseThrow(() -> new EntityNotFoundException("Medication not found"));
//            media.setMedication(medication);
//        }

        Media savedMedia = outputMediaRepository.save(media);
        return null;
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
        Media media = outputMediaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Media not found"));
        media.setUrl(mediaDTOWithId.getUrl());
        media.setType(mediaDTOWithId.getType());
        media.setProductId(mediaDTOWithId.getProductId().getId());
        media.setMedicationId(mediaDTOWithId.getMedicationId().getId());
        outputMediaRepository.save(media);
        return mapToMediaDTOWithId(media);
    }

    @Override
    public void deleteMedia(Long id) {
        outputMediaRepository.deleteById(id);
    }

}
