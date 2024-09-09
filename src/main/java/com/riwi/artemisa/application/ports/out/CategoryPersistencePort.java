package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.domain.models.CategoryModel;

import java.util.List;
import java.util.Optional;

public interface CategoryPersistencePort {

    Optional<CategoryModel> findByName(String name);

    List<CategoryModel> findAll();

    CategoryModel save(CategoryModel category);

    void deleteByName(String name);

}
