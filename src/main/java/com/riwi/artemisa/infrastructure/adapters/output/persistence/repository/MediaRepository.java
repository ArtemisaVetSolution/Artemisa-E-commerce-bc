package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    @Query("SELECT m FROM medias m WHERE m.id = :id AND m.deleted = false")
    Optional<Media> findByIdAndNotDeleted(@Param("id") Long id);

    List<Media> findAllByDeletedIsFalse();
}
