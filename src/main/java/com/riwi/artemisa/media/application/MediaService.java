package com.riwi.artemisa.media.application;

import com.riwi.artemisa.application.ports.input.category.MedicationInventoryServicePort;
import com.riwi.artemisa.media.domain.Media;
import com.riwi.artemisa.media.infrastructure.MediaDTONoId;
import com.riwi.artemisa.media.infrastructure.MediaDTOWithId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MediaService implements InputMediaUseCaseInterface {
    @Autowired
    private MedicationInventoryServicePort medicationReposiroty;
    @Autowired
    private final OutputMediaRepository outputMediaRepository;

    public MediaService(OutputMediaRepository outputMediaRepository) {
        this.outputMediaRepository = outputMediaRepository;
    }

    private MediaDTOWithId mapToMediaDTOWithId(Media media) {
        MediaDTOWithId dto = new MediaDTOWithId();
        dto.setId(media.getId());
        dto.setUrl(media.getUrl());
        dto.setType(media.getType());
//        if (media.getProductId() != null) {
//            dto.setProductId(media.getProductId().getId());
//        }
//        if (media.getMedicationId() != null) {
//            Medication medication = medicationReposiroty.findById(media.getMedicationId())
//                    .orElseThrow(() -> new EntityNotFoundException("Medication not found"));
//            dto.setMedicationId(medication.getId());
//        }
        return dto;
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
        return null;
    }

    @Override
    public void deleteMedia(Long id) {

    }



}
