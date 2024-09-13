package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.domain.models.CategoryModel;


import java.util.List;

public interface CategoryServicePort {
    CategoryModel createCategory(CategoryModel categoryModel);
    CategoryModel editCategory(Long id, CategoryModel categoryModel);
    void deleteCategory(Long id);
    List<CategoryModel>getAllCategories();
    CategoryModel getCategoryById(Long id);
}
