package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.CategoryPersistencePort;
import com.riwi.artemisa.domain.models.CategoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.CategoryPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPersistencePort {

    private final CategoryRepository repository;

    private final CategoryPersistenceMapper mapper;


    @Override
    public Optional<CategoryModel> findByName(String name) {
        return repository.findByName(name)
                .map(mapper::toCategoryModel);
    }

    @Override
    public List<CategoryModel> findAll() {
        return mapper.toCategoryModels(repository.findAll());
    }

    @Override
    public CategoryModel save(CategoryModel categoryModel) {
        return mapper.toCategoryModel(repository.save(mapper.toCategory(categoryModel)));
    }

    @Override
    public void deleteByName(String name) {
        repository.findByName(name);
    }
}
