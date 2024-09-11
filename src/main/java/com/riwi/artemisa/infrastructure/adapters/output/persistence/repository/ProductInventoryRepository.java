package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {}
