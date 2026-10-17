package com.thifuge.kaala_plus.orders;

import com.thifuge.kaala_plus.orders.dto.CreateCompleteOrderRequest;
import com.thifuge.kaala_plus.orders.dto.CreateOrderRequest;
import com.thifuge.kaala_plus.orders.dto.CreateOrderResponse;
import com.thifuge.kaala_plus.orders.dto.PendingOrderResponse;
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

    @PostMapping
    public ResponseEntity<CreateOrderResponse> create(@RequestBody CreateOrderRequest request) {
        CreateOrderResponse response = this.orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PendingOrderResponse>> getPendingOrders(@RequestParam(required = false) Integer page, @RequestParam(required = true) OrderState state) {
        return ResponseEntity.ok(this.orderService.getPendingOrders(state));
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

    @PostMapping("/complete")
    public ResponseEntity<CreateOrderResponse> createCompleteOrder(@RequestBody CreateCompleteOrderRequest request){
        CreateOrderResponse response = this.orderService.createCompleteOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
