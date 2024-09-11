package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
