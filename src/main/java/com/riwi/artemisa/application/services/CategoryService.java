package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.CategoryServicePort;
import com.riwi.artemisa.application.ports.out.CategoryPersistencePort;
import com.riwi.artemisa.domain.exception.CategoryNotFoundException;
import com.riwi.artemisa.domain.models.CategoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {

    private final CategoryPersistencePort persistencePort;


    @Override
    public CategoryModel createCategory(CategoryModel categoryModel) {
        return persistencePort.save(categoryModel);
    }

    @Override
    public CategoryModel editCategory(Long id, CategoryModel categoryModel) {
        CategoryModel existingCategory = getCategoryById(id);
        existingCategory.setName(categoryModel.getName());
        existingCategory.setDescription(categoryModel.getDescription());
        return persistencePort.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        CategoryModel categoryModel = getCategoryById(id);
        categoryModel.setDeletedAt(LocalDateTime.now());
        categoryModel.setDeleted(true);
        persistencePort.save(categoryModel);
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return persistencePort.findAll();
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
       return persistencePort.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
}
