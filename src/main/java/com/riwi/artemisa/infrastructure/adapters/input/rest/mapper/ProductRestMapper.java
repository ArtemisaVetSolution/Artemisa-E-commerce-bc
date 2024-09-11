package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.ProductModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.ProductCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductRestMapper {

    ProductModel toProductModel(ProductCreateRequest productCreateRequest);
    ProductResponse toProductResponse(ProductModel productModel);
    List<ProductResponse> toProductResponseList(List<ProductModel> productModels);

}
