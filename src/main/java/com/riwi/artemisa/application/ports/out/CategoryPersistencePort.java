package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.domain.models.CategoryModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CategoryPersistencePort {
    CategoryModel save(CategoryModel category);
    Optional<CategoryModel> findById(Long ID);
    List<CategoryModel> findAll();
    void deleteById(Long ID);

}
