package com.thifuge.kaala_plus.orders;

import com.thifuge.kaala_plus.orders.dto.*;
import com.thifuge.kaala_plus.shared.constants.ApiEndpoints;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.ORDERS)
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/confirmed")
    public ResponseEntity<List<ConfirmedOrderResponse>> findOrdersByStates(
            @RequestParam List<OrderState> states
    ){
        return ResponseEntity.ok(
                this.orderService.findOrdersByStates(states)
        );
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> create(@RequestBody CreateOrderRequest request) {
        CreateOrderResponse response = this.orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PendingOrderResponse>> getPendingOrders(@RequestParam(required = true) OrderState state) {
        return ResponseEntity.ok(this.orderService.getPendingOrders(state));
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<OrderDetailsResponse> findOrder(@PathVariable Integer id) {
        OrderDetailsResponse orderDetailsResponse = this.orderService.findOrderById(id);
        return ResponseEntity.ok(orderDetailsResponse);
    }

    @PutMapping("{id}/complete")
    public ResponseEntity<CreateOrderResponse> completeExistingOrder(@PathVariable Integer id, @RequestBody CompleteExistingOrderRequest request) {
        return ResponseEntity.ok(
                this.orderService.completeExistingOrder(id, request)
        );
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

    @PostMapping("/complete")
    public ResponseEntity<CreateOrderResponse> createCompleteOrder(@RequestBody CreateCompleteOrderRequest request){
        CreateOrderResponse response = this.orderService.createCompleteOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
