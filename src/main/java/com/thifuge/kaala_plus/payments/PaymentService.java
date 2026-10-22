package com.thifuge.kaala_plus.payments;

import com.thifuge.kaala_plus.orders.Order;
import com.thifuge.kaala_plus.orders.OrderService;
import com.thifuge.kaala_plus.orders.OrderState;
import com.thifuge.kaala_plus.payments.dto.request.CreatePaymentRequest;
import com.thifuge.kaala_plus.shared.entities.Currency;
import com.thifuge.kaala_plus.shared.services.CurrencyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CurrencyService currencyService;
    private final OrderService orderService;

    @Transactional
    public void createPayment(CreatePaymentRequest request) {

        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }

        Order order = this.orderService.findOrderByReference(request.orderReference());

        Currency currency = this.currencyService.getCurrency(request.currencyId());

        Optional<Payment> firstPayment = this.paymentRepository.findFirstByOrderIdOrderByIdAsc(order.getId());

        if (firstPayment.isPresent()) {
            Currency firstCurrency = firstPayment.get().getCurrency();

            if (!firstCurrency.getId().equals(currency.getId())) {
                throw new IllegalArgumentException("All payments for the same order must use the same currency");
            }
        }

        BigDecimal convertedAmount;
        BigDecimal exchangeRate;

        if (order.getCurrency().getId().equals(currency.getId())) {
            exchangeRate = BigDecimal.ONE;
            convertedAmount = request.amount();
        } else {
            exchangeRate = request.exchangeRate();
            if (exchangeRate == null || exchangeRate.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Exchange rate must be greater than zero");
            }

            convertedAmount = request.amount().multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
        }

        // Total of earlier payments
        BigDecimal totalPaid = this.paymentRepository.sumConvertedAmountByOrderId(order.getId());

        // Remaining sold
        BigDecimal remainingAmount = order.getTotalprice().subtract(totalPaid);

        // verify the new payment
        if (convertedAmount.compareTo(remainingAmount) > 0) {
            throw new IllegalArgumentException("Payment amount exceeds the remaining balance");
        }

        PaymentType paymentType = convertedAmount.compareTo(remainingAmount) == 0
                ? PaymentType.FINAL
                : PaymentType.PARTIAL;

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setCurrency(currency);
        payment.setAmount(convertedAmount);
        payment.setExchangeRate(exchangeRate);
        payment.setPaymentType(paymentType);
        payment.setExchangeRate(exchangeRate);
        payment.setConvertedAmount(convertedAmount);
        payment.setPaymentDate(
                request.paymentDate() != null
                    ? request.paymentDate() : LocalDate.now()
        );
        payment.setNote(request.note());
        this.paymentRepository.save(payment);

        if (paymentType == PaymentType.FINAL) {
            order.setOrderState(OrderState.PAID);
        }else  {
            order.setOrderState(OrderState.IN_PROGRESS);
        }

    }

    public void createPayment(Payment payment) {
        // find the target order
        Order order = payment.getOrder();
        Order orderInDB = this.orderService.findOrderByReference(order.getReference());
        payment.setOrder(orderInDB);

        // find the target currency or create a new one
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
        Currency currency = payment.getCurrency();
        Currency currencyInDB = this.currencyService.getCurrencyByName(currency.getName());
        if (currencyInDB == null) {
            currencyInDB = this.currencyService.createCurrency(currency);
        }
        paymentInDB.setCurrency(currencyInDB);
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
