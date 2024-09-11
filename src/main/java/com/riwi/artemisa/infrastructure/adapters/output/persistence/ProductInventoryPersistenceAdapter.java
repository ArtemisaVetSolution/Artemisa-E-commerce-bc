package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.ProductInventoryPersistencePort;
import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Category;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Product;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.ProductInventoryPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.CategoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MediaRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.ProductInventoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ProductInventoryPersistenceAdapter implements ProductInventoryPersistencePort {

    private final ProductInventoryRepository productInventoryRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MediaRepository mediaRepository;

    private final ProductInventoryPersistenceMapper productInventoryPersistenceMapper;

    @Override
    public ProductInventoryModel save(ProductInventoryModel productInventoryModel) {

        ProductInventory productInventory = ProductInventory.builder().
                stock(productInventoryModel.getStock()).
                updateDate(LocalDate.now()).
                supplier(productInventoryModel.getSupplier()).
                supplierPrice(productInventoryModel.getSupplierPrice()).
                sellingPrice(productInventoryModel.getSellingPrice()).
                dueDate(productInventoryModel.getDueDate()).
                stateProduct(true).
                build();


        Product product = Product.builder().
                name(productInventoryModel.getProduct().getName()).
                description(productInventoryModel.getProduct().getDescription())
            .build();


        Category category = categoryRepository.findById(productInventoryModel.getProduct().getCategory().getId()).orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategoryId(category);

        List<Media> media = productInventoryModel.getProduct().getMedia().stream().map(
                mediaModel -> {

                    Media mediaEntity = Media.builder()
                            .type(mediaModel.getType())
                            .url(mediaModel.getUrl())
                            .build();

                    return mediaRepository.save(mediaEntity);

                }
        ).toList();


        product.setMedia(media);

        Product savedProduct = productRepository.save(product);

        productInventory.setProduct(savedProduct);

        return productInventoryPersistenceMapper.toProductInventoryModel(productInventoryRepository.save(productInventory));
    }
}
