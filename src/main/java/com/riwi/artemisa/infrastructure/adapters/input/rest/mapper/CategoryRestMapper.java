package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.CategoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.CategoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRestMapper {

    CategoryModel toCategory(CategoryCreateRequest request);

    CategoryResponse toCategoryResponse(CategoryModel category);

    List<CategoryResponse> toCategoryResponseList(List<CategoryModel> categoryList);


}

