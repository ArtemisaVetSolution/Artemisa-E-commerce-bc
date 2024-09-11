package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductInventoryPersistenceMapper {

    ProductInventory toProductInventory(ProductInventoryModel productInventoryModel);

    @Mapping(source = "product.categoryId", target = "product.category")
    ProductInventoryModel toProductInventoryModel(ProductInventory productInventory);

    List<ProductInventoryModel> toProductInventoryModelList(List<ProductInventory> productInventories);

}

