package com.thifuge.kaala_plus.payments;

import com.thifuge.kaala_plus.shared.entities.Currency;
import com.thifuge.kaala_plus.shared.services.CurrencyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CurrencyService currencyService;

    public void createPayment(Payment payment) {
        Currency currency = payment.getCurrency();
        Currency currencyInDB = this.currencyService.getCurrencyByName(currency.getName());
        if (currencyInDB == null) {
            currencyInDB = this.currencyService.createCurrency(currency);
        }
        payment.setCurrency(currencyInDB);
        log.info("Creating a new payment {}", payment);
        this.paymentRepository.save(payment);
    }

    public List<Payment> findAllPayments() {
        log.info("Finding all payments");
        return this.paymentRepository.findAll();
    }

    public Payment findPayment(Integer id) {
        log.info("Finding a payment with id {}", id);
        Optional<Payment> payment = this.paymentRepository.findById(id);
        return payment.orElseThrow(() -> new EntityNotFoundException(
                "Payment with id " + id + " not found"
        ));
    }

    public Payment updatePayment(Integer id, Payment payment) {
        Payment paymentInDB = this.findPayment(id);

        log.info("Updating a payment with id {}", id);
        paymentInDB.setCurrency(payment.getCurrency());
        paymentInDB.setAmount(payment.getAmount());
        paymentInDB.setPaymentDate(payment.getPaymentDate());
        paymentInDB.setPaymentType(payment.getPaymentType());
        paymentInDB.setNote(payment.getNote());
        paymentInDB.setUpdatedAt(payment.getUpdatedAt());

        paymentInDB = this.paymentRepository.save(paymentInDB);
        return paymentInDB;
    }

    public void deletePayment(Integer id) {
        Payment paymentInDB = this.findPayment(id);
        log.info("Deleting a payment with id {}", id);
        this.paymentRepository.delete(paymentInDB);
    }
}
