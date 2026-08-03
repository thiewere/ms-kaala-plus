package com.thifuge.kaala_plus.order_items;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public void createOrderItem(OrderItem orderItem) {
        log.info("Creating a new order_item: {}", orderItem);
        this.orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findAllOrderItems() {
        log.info("Finding all order items");
        return this.orderItemRepository.findAll();
    }

    public OrderItem findOrderItem(int id) {
        log.info("Finding order_item by id: {}", id);
        Optional<OrderItem> orderItemInDB = this.orderItemRepository.findById(id);
        return orderItemInDB.orElseThrow(() -> new EntityNotFoundException(
                String.format("OrderItem with id: %d not found", id)
        ));
    }

    public OrderItem updateOrderItem(int id, OrderItem orderItem) {
        OrderItem orderItemDB = this.findOrderItem(id);
        log.info("Updating order_item: {}", orderItemDB);
        orderItemDB.setOrder(orderItem.getOrder());
        orderItemDB.setQuantity(orderItem.getQuantity());
        orderItemDB.setUnitPrice(orderItem.getUnitPrice());
        orderItemDB.setTotalPrice(orderItem.getTotalPrice());
        orderItemDB.setCurrency(orderItem.getCurrency());
        orderItemDB.setSupplierProduct(orderItem.getSupplierProduct());

        orderItemDB = this.orderItemRepository.save(orderItemDB);
        return orderItemDB;
    }

    public void deleteOrderItem(int id) {
        OrderItem orderItemDB = this.findOrderItem(id);
        log.info("Deleting order_item: {}", orderItemDB);
        this.orderItemRepository.delete(orderItemDB);
    }
}
