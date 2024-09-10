package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.category.CategoryServicePort;
import com.riwi.artemisa.application.ports.out.CategoryPersistencePort;
import com.riwi.artemisa.domain.exception.CategoryNotFoundException;
import com.riwi.artemisa.domain.models.CategoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {

    private final CategoryPersistencePort persistencePort;

    @Override
    public CategoryModel findByName(String name) {
        return persistencePort.findByName(name)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public List<CategoryModel> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public CategoryModel save(CategoryModel categoryModel) {
        return persistencePort.save(categoryModel);
    }

    @Override
    public CategoryModel update(String name, CategoryModel categoryModel) {
        return persistencePort.findByName(name)
                .map(categoryDB ->{
                    categoryDB.setDescription(categoryModel.getDescription());
                    categoryDB.setName(categoryModel.getName());
                    return persistencePort.save(categoryDB);
                })
                .orElseThrow();
    }

    @Override
    public void deleteByName(String name) {
        if (persistencePort.findByName(name).isEmpty()){
            throw new CategoryNotFoundException();
        }
        persistencePort.deleteByName(name);
    }
}
