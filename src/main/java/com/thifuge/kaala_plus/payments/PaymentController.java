package com.thifuge.kaala_plus.payments;

import com.thifuge.kaala_plus.payments.dto.CreatePaymentResponse;
import com.thifuge.kaala_plus.payments.dto.OrderPaymentSummaryResponse;
import com.thifuge.kaala_plus.payments.dto.request.CreatePaymentRequest;
import com.thifuge.kaala_plus.shared.constants.ApiEndpoints;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.PAYMENTS)
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/orders/{orderReference}/summary")
    public ResponseEntity<OrderPaymentSummaryResponse> getOrderPaymentSummary(
            @PathVariable String orderReference) {
        return ResponseEntity.ok(
                this.paymentService.getOrderPaymentSummary(orderReference)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePaymentResponse createPayment(@RequestBody CreatePaymentRequest request) {
        return  this.paymentService.createPayment(request);
    }

    @GetMapping
    public ResponseEntity<List<CreatePaymentResponse>> findAllPayments() {
        return ResponseEntity.ok(
                this.paymentService.findAllPayments()
        );
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CreatePaymentResponse> findPayment(@PathVariable Integer id) {
        return ResponseEntity.ok(
                this.paymentService.findPaymentById(id)
        );
    }

    @PutMapping(path = "{id}")
    public Payment updatePayment(@PathVariable Integer id, @RequestBody Payment payment) {
        return this.paymentService.updatePayment(id, payment);
    }

    @DeleteMapping(path = "{id}")
    public void deletePayment(@PathVariable Integer id) {
        this.paymentService.deletePayment(id);
    }
}
