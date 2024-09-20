package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.PetshopPaymentsPersistencePort;
import com.riwi.artemisa.domain.models.PetshopPaymentsModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Order;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.PetshopPayments;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.PetshopPaymentsPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.OrderRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.PetshopPaymentsRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.ProductInventoryRepository;
import com.riwi.artemisa.utils.enums.StatesOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PetshopPaymentsPersistenceAdapter implements PetshopPaymentsPersistencePort {

    private final OrderRepository orderRepository;
    private final PetshopPaymentsRepository petshopPaymentsRepository;
    private final ProductInventoryRepository productInventoryRepository;
    private final PetshopPaymentsPersistenceMapper mapper;

    @Override
    public PetshopPaymentsModel save(PetshopPaymentsModel petshopPaymentsModel) {

        Order order = orderRepository.findById(petshopPaymentsModel.getOrder().getId()).orElseThrow();

        order.getOrderDetails().forEach(
                od -> {
                    if(od.getQuantity() - od.getProduct().getStock() < 0){
                        throw new RuntimeException("we count only a quantity of " + od.getProduct() + " products " + od.getProduct().getProduct().getName());
                    }
                }
        );

        order.setStatesOrder(StatesOrder.COMPLETED);

        order.getOrderDetails().forEach(
                o -> {
                    ProductInventory productInventory = productInventoryRepository.findById(o.getProduct().getId())
                           .orElseThrow(() -> new RuntimeException("Product inventory not found"));

                    productInventory.setStock(productInventory.getStock() - o.getQuantity());
                    productInventoryRepository.save(productInventory);

                    productInventoryRepository.save(productInventory);

                }
        );

        PetshopPayments petshopPayments = PetshopPayments.builder()
                .paymentDate(LocalDate.now())
                .amount(order.getTotalOrder())
                .paymentMethod(petshopPaymentsModel.getPaymentMethod())
                .order(orderRepository.save(order))
                .build();

        return mapper.toPetshopPaymentsModel(petshopPaymentsRepository.save(petshopPayments));
    }

    @Override
    public List<PetshopPaymentsModel> findAll() {
        return mapper.toPetshopPaymentsModelList(petshopPaymentsRepository.findAll());
    }


    @Override
    public List<PetshopPaymentsModel> findByDate(LocalDate localDate) {
        return mapper.toPetshopPaymentsModelList(petshopPaymentsRepository.findByPaymentDate(localDate));
    }

    @Override
    public List<PetshopPaymentsModel> findPetshopPaymentsByUserId(Long idUser) {
        return mapper.toPetshopPaymentsModelList(petshopPaymentsRepository.findPaymentsByUserId(idUser));
    }
}
