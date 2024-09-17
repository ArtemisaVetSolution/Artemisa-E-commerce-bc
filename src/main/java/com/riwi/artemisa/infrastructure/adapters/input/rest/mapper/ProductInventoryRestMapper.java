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

    //Admin

    @Mapping(source = "product", target = "product")
    ProductInventoryModel toProductInventoryModel(ProductInventoryCreateRequest productInventoryCreateRequest);

    @Mapping(source = "product", target = "product")
    ProductInventoryCreateRequest toProductInventoryCreateRequest(ProductInventoryModel productInventoryModel);

    List<ProductInventoryCreateRequest> toProductInventoryCreateRequestList(List<ProductInventoryModel> productInventoryModels);

    //User

    @Mapping(source = "product", target = "product")
    ProductInventoryResponse toProductInventoryResponse(ProductInventoryModel productInventoryModel);

    @Mapping(source = "product", target = "product")
//    ProductInventoryModel toProductInventoryModel(ProductInventoryResponse productInventoryResponse);

    List<ProductInventoryResponse> toProductInventoryResponseList(List<ProductInventoryModel> productInventoryModels);

}
