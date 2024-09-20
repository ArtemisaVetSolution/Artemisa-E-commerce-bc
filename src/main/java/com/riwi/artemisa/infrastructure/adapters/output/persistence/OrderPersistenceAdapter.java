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

        Order order = orderRepository.findByIdUserAndByStateOrder(orderModel.getIdUser(), StatesOrder.PENDING);

        if (order != null) {
            order.setTotalOrder(0.0f);

            // Obtener la lista existente de orderDetails
            List<OrderDetails> existingOrderDetails = order.getOrderDetails();

            orderModel.getOrderDetails().forEach(od -> {
                ProductInventory product = productInventoryRepository.findById(od.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                OrderDetails findOrderDetails = existingOrderDetails.stream()
                        .filter(detail -> detail.getProduct().getId().equals(product.getId()))
                        .findFirst().orElse(null);

                if (findOrderDetails != null) {
                    if(product.getStock() - od.getQuantity() >= 0){
                        // Actualiza el OrderDetails existente
                        findOrderDetails.setQuantity(od.getQuantity());
                        findOrderDetails.setTotalPriceProduct(od.getQuantity() * product.getSellingPrice());
                    }else{
                        throw new RuntimeException("we count only a quantity of " + product.getStock() + " products");
                    }
                } else {
                    if(product.getStock() - od.getQuantity() >= 0){
                        // Agrega un nuevo OrderDetails si no existe
                        OrderDetails newOrderDetail = OrderDetails.builder()
                                .quantity(od.getQuantity())
                                .unitPrice(product.getSellingPrice())
                                .totalPriceProduct(od.getQuantity() * product.getSellingPrice())
                                .product(product)
                                .order(order)
                                .build();
                        existingOrderDetails.add(newOrderDetail);  // Agrega el nuevo detalle a la lista existente
                    }else{
                        throw new RuntimeException("we count only a quantity of " + product.getStock() + " products");
                    }
                }
            });

            // Actualiza el total de la orden basado en los detalles existentes
            existingOrderDetails.forEach(detail ->
                order.setTotalOrder(order.getTotalOrder() + detail.getTotalPriceProduct())
            );

            order.setOrderDate(LocalDate.now());

            // Guarda la orden actualizada con los detalles correctamente referenciados
            orderRepository.save(order);

            return orderPersistenceMapper.toOrderModel(orderRepository.findById(order.getId()).orElseThrow());
        } else {

            Order createOrder = orderRepository.save(Order.builder()
                    .idUser(orderModel.getIdUser())
                    .statesOrder(StatesOrder.PENDING)
                    .totalOrder(0.0f)
                    .orderDate(LocalDate.now())
                    .build());

            orderModel.getOrderDetails().forEach(
                    od -> {
                        ProductInventory product = productInventoryRepository.findById(od.getProduct().getId())
                                .orElseThrow(() -> new RuntimeException("Product not found"));

                        if (product.getStock() - od.getQuantity() >= 0) {

                            orderDetailsRepository.save(OrderDetails.builder()
                                    .quantity(od.getQuantity())
                                    .unitPrice(product.getSellingPrice())
                                    .totalPriceProduct(od.getQuantity() * product.getSellingPrice())
                                    .product(product)
                                    .order(createOrder)
                                    .build());

                        }else{
                            throw new RuntimeException("we count only a quantity of " + product.getStock() + " products");
                        }

                    });
            List<OrderDetails> newOrderDetails = orderDetailsRepository.findAllByOrderId(createOrder.getId());

            createOrder.setOrderDetails(newOrderDetails);

            newOrderDetails.forEach(update -> createOrder.setTotalOrder(createOrder.getTotalOrder() + update.getTotalPriceProduct()));

            return orderPersistenceMapper.toOrderModel(orderRepository.save(createOrder));
        }
    }


    @Override
    public List<OrderModel> findAll() {
        return orderPersistenceMapper.toOrderModelList(orderRepository.findAll());
    }

    @Override
    public OrderModel readByIdUserAndOrderDate(Long id, LocalDate date) {
        return orderPersistenceMapper.toOrderModel(orderRepository.findByIdUserAndByOrderDate(id,date));
    }

    @Override
    public OrderModel readById(Long id) {
        return orderPersistenceMapper.toOrderModel(orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @Override
    public String updateStatesOrder(Long id, StatesOrder statesOrder) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatesOrder(statesOrder);
        orderRepository.save(order);
        return "Updated successfully";
    }

    @Override
    public String deleteOrderDetails(Long idOrder, Long idOrderDetails) {

        Order order = orderRepository.findById(idOrder).orElseThrow(() -> new RuntimeException("Order not found"));

        order.getOrderDetails().removeIf(detail -> detail.getId().equals(idOrderDetails));

        if (order.getOrderDetails().isEmpty()) {
            orderRepository.delete(order);
            return "Delete Order Details successfully";

        } else {
            orderRepository.save(order);
            return "Delete Order successfully";

        }
    }
}

