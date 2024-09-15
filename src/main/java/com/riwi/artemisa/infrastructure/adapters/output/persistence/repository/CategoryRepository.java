package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Category;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    Category findByDescription(String description);
    Category findByNameIgnoreCase(String name);

    @Query("SELECT m FROM category m WHERE m.name = :name AND m.deleted = false")
    Optional<Category> findByIdAndNotDeleted(@Param("name") String name);

    List<Category> findAllByDeletedIsFalse();
}
