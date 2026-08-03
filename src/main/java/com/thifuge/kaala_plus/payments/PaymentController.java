package com.thifuge.kaala_plus.payments;

import com.thifuge.kaala_plus.shared.constants.ApiEndpoints;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.PAYMENTS)
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public void  createPayment(@RequestBody Payment payment) {
        this.paymentService.createPayment(payment);
    }

    @GetMapping
    public List<Payment> findAllPayments() {
        return this.paymentService.findAllPayments();
    }

    @GetMapping(path = "{id}")
    public Payment findPayment(@PathVariable Integer id) {
        return this.paymentService.findPayment(id);
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
