package com.thifuge.kaala_plus.order_items;

import com.thifuge.kaala_plus.orders.Order;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("order_items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping()
    public void createOrderItem(@RequestBody OrderItem orderItem) {
        this.orderItemService.createOrderItem(orderItem);
    }

    @GetMapping()
    public List<OrderItem> findAllOrderItems() {
        return this.orderItemService.findAllOrderItems();
    }

    @GetMapping(path = "{id}")
    public OrderItem findOrderItem(@PathVariable int id) {
        return this.orderItemService.findOrderItem(id);
    }

    @PutMapping(path = "{id}")
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem, @PathVariable int id) {
        return this.orderItemService.updateOrderItem(id, orderItem);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrderItem(@PathVariable int id) {
        this.orderItemService.deleteOrderItem(id);
    }
}
