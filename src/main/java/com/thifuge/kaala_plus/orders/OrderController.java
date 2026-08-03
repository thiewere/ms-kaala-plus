package com.thifuge.kaala_plus.orders;

import com.thifuge.kaala_plus.shared.constants.ApiEndpoints;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.ORDERS)
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

    @PutMapping(path = "{reference}")
    public Order addContainer(@PathVariable String reference, @RequestBody Order order) {
        return this.orderService.addContainer(reference, order);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable Integer id) {
        this.orderService.deleteOrder(id);
    }
}
