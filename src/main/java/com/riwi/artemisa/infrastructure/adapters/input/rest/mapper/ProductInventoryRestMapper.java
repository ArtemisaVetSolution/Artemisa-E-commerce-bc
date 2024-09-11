package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.ProductInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductInventoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductInventoryRestMapper {

    @Mapping(source = "product", target = "product")
    ProductInventoryModel toProductInventoryModel(ProductInventoryCreateRequest productInventoryCreateRequest);
    ProductInventoryResponse toProductInventoryResponse(ProductInventoryModel productInventoryModel);
    List<ProductInventoryResponse> toProductInventoryResponseList(List<ProductInventoryModel> productInventoryModels);

}
