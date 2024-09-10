package com.riwi.artemisa.application.ports.input.category;

import com.riwi.artemisa.domain.models.CategoryModel;


import java.util.List;

public interface CategoryServicePort {
    CategoryModel findByName(String name);
    List<CategoryModel> findAll();
    CategoryModel save(CategoryModel categoryModel);
    CategoryModel update(String name, CategoryModel categoryModel);
    void deleteByName(String name);
}
