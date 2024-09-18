package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.OrderPersistencePort;
import com.riwi.artemisa.domain.models.OrderModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Order;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.OrderDetails;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.OrderPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.OrderDetailsRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.OrderRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.ProductInventoryRepository;
import com.riwi.artemisa.utils.enums.StatesOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderPersistencePort {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductInventoryRepository productInventoryRepository;

    private final OrderPersistenceMapper orderPersistenceMapper;


    @Override
    public OrderModel save(OrderModel orderModel) {

        Order findOrder = orderRepository.findByIdUserAndByStateOrder(orderModel.getIdUser());
        if(findOrder != null) {

            List<OrderDetails> orderDetails = orderModel.getOrderDetails().stream().map(
                    or -> {

                        ProductInventory product = productInventoryRepository.findById(or.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));

                        findOrder.getOrderDetails().forEach(od -> {
                            if (od.getProduct().getId() == product.getId()) {

                            }
                        });

                        float priceTotal = product.getSellingPrice()*or.getQuantity();
                        OrderDetails newOrderDetails = OrderDetails.builder()
                                .quantity(or.getQuantity())
                                .unitPrice(product.getSellingPrice())
                                .totalPriceProduct(priceTotal)
                                .product(product)
                                .build();

                        if(findOrder.getTotalOrder() == null) {
                            findOrder.setTotalOrder(0.0f);
                        }
                        findOrder.setTotalOrder(findOrder.getTotalOrder() + newOrderDetails.getTotalPriceProduct());

                        return orderDetailsRepository.save(newOrderDetails);



//                        float priceTotal = product.getSellingPrice()*or.getQuantity();
//                        OrderDetails newOrderDetails = OrderDetails.builder()
//                                .quantity(or.getQuantity())
//                                .unitPrice(product.getSellingPrice())
//                                .totalPriceProduct(priceTotal)
//                                .product(product)
//                                .build();
//
//                        findOrder.setTotalOrder(0);
//                        findOrder.setTotalOrder(findOrder.getTotalOrder() + newOrderDetails.getTotalPriceProduct());
//
//                        return orderDetailsRepository.save(newOrderDetails);



                    }).toList();

            findOrder.setOrderDetails(orderDetails);
            findOrder.setOrderDate(LocalDate.now());
            findOrder.setStatesOrder(StatesOrder.valueOf("PENDING"));
            return orderPersistenceMapper.toOrderModel(orderRepository.save(findOrder));

        }else{

            Order createOrder = new Order();
            List<OrderDetails> orderDetails = orderModel.getOrderDetails().stream().map(
                    or -> {

                        ProductInventory product = productInventoryRepository.findById(or.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));

                        OrderDetails newOrderDetails = OrderDetails.builder()
                                .quantity(or.getQuantity())
                                .unitPrice(product.getSellingPrice())
                                .totalPriceProduct(product.getSellingPrice()*or.getQuantity())
                                .product(product)
                                .build();

                        if(createOrder.getTotalOrder() == null) {
                            createOrder.setTotalOrder(0.0f);
                        }
                        createOrder.setTotalOrder(createOrder.getTotalOrder() + newOrderDetails.getTotalPriceProduct());

                        return orderDetailsRepository.save(newOrderDetails);
                    }).toList();

            createOrder.setIdUser(orderModel.getIdUser());
            createOrder.setOrderDate(LocalDate.now());
            createOrder.setStatesOrder(StatesOrder.valueOf("PENDING"));
            createOrder.setOrderDetails(orderDetails);
            return orderPersistenceMapper.toOrderModel(orderRepository.save(createOrder));

        }

    }
}

