package com.thifuge.kaala_plus.orders;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void create(@RequestBody Order order) {
        this.orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> findAll() {
        return this.orderService.findAllOrders();
    }

    @GetMapping(path = "{id}")
    public Order findOrder(@PathVariable Integer id) {
        return this.orderService.findOrder(id);
    }

    @PutMapping(path = "{id}")
    public Order updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        return this.orderService.updateOrder(id, order);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable Integer id) {
        this.orderService.deleteOrder(id);
    }
}
