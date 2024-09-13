package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.ProductInventoryPersistencePort;
import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductInventoryResponse;
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

    @Override
    public ProductInventoryModel update(Long id, ProductInventoryModel productInventoryModel) {

        productInventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product inventory not found"));

        ProductInventory productInventory = ProductInventory.builder()
                .id(productInventoryModel.getId())
                .stock(productInventoryModel.getStock())
                .updateDate(LocalDate.now())
                .supplier(productInventoryModel.getSupplier())
                .supplierPrice(productInventoryModel.getSupplierPrice())
                .sellingPrice(productInventoryModel.getSellingPrice())
                .dueDate(productInventoryModel.getDueDate())
                .stateProduct(true)
                .build();

        Product product = Product.builder()
                .id(productInventoryModel.getProduct().getId())
                .name(productInventoryModel.getProduct().getName())
                .description(productInventoryModel.getProduct().getDescription())
                .build();

        Category category = categoryRepository.findById(productInventoryModel.getProduct().getCategory().getId()).orElseThrow(() -> new RuntimeException("The category does not exist"));
        product.setCategoryId(category);

        List<Media> media = productInventoryModel.getProduct().getMedia().stream().map(
                mediaModel -> {

                    Media mediaEntity = Media.builder()
                            .id(mediaModel.getId())
                            .type(mediaModel.getType())
                            .url(mediaModel.getUrl())
                            .build();

                    return mediaRepository.save(mediaEntity);

                }
        ).toList();

        product.setMedia(media);

        productInventory.setProduct(productRepository.save(product));

        return productInventoryPersistenceMapper.toProductInventoryModel(productInventoryRepository.save(productInventory));
    }

    @Override
    public String updateStatusProduct(Long id) {
        ProductInventory productInventory = productInventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("the product does not exist, therefore it could not be eliminated."));
        productInventory.setStateProduct(!productInventory.isStateProduct());
        productInventoryRepository.save(productInventory);
        return productInventory.isStateProduct() ? "Product restore successfully" : "Product deleted successfully";
    }

    @Override
    public List<ProductInventoryModel> findAll() {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findAll());
    }

    @Override
    public ProductInventoryModel readById(Long id) {
        return productInventoryPersistenceMapper.toProductInventoryModel(productInventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("The Product not found")));
    }

    @Override
    public List<ProductInventoryModel> readAllCategory(Long id) {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findByCategoryId(id));
    }

    @Override
    public List<ProductInventoryModel> findAllByName(String name) {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findAllByName(name));
    }

    @Override
    public String updateStock(Integer stock, Long id) {
        ProductInventory productInventory = productInventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product inventory not found"));
        productInventory.setStock(productInventory.getStock() + stock);
        productInventory.setUpdateDate(LocalDate.now());
        productInventoryRepository.save(productInventory);
        return "Stock updated successfully";
    }

    @Override
    public List<ProductInventoryModel> readAllProductStock(Integer stock) {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findAllProductInventoryStock(stock));
    }

    @Override
    public List<ProductInventoryModel> readAllIfAvailable() {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findAllProductInventoryAvailable());
    }
}
