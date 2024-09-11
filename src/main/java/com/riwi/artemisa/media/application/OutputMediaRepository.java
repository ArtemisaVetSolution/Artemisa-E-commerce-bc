package com.riwi.artemisa.media.application;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.StatusOrder;

import java.util.Optional;

public interface OutputMediaRepository {
    StatusOrder.Media save (StatusOrder.Media media);
    Optional<StatusOrder.Media> findById(Long id);
    void deleteById(Long id);
    Optional<StatusOrder.Media> findByProductId(Long id);
    Optional<StatusOrder.Media> findByMedicationId(Long id);
    // Para eliminar una URL asociada a un producto
    void deleteByUrlAndProductId(String url, Long productId);
    // Para eliminar una URL asociada a un medicamento
    void deleteByUrlAndMedicationId(String url, Long medicationId);
}
