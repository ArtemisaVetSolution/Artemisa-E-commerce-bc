package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByDeletedFalse();


}
