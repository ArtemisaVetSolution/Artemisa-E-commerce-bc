package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,String> {
    Optional<Medication> findByNameIgnoreCase(String name);
}
