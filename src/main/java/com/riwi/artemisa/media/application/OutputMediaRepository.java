package com.riwi.artemisa.media.application;

import com.riwi.artemisa.media.domain.Media;

import java.util.Optional;

public interface OutputMediaRepository {
    Media save (Media media);
    Optional<Media> findById(Long id);
    void deleteById(Long id);
    Optional<Media> findByProductId(Long id);
    Optional<Media> findByMedicationId(Long id);

}
