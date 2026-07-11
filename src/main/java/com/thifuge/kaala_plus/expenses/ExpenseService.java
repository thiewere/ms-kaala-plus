package com.thifuge.kaala_plus.expenses;

import com.thifuge.kaala_plus.orders.Order;
import com.thifuge.kaala_plus.orders.OrderService;
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
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final OrderService orderService;
    private final CurrencyService currencyService;

    public void createExpense(Expense expense) {
        Order order = expense.getOrder();
        Order orderInDB = this.orderService.findOrderByReference(order.getReference());

        // find an existing currency or create a new one
        Currency currency = expense.getCurrency();
        Currency currencyInDB = this.currencyService.getCurrencyByName(currency.getName());
        if (orderInDB == null) {
            currencyInDB = this.currencyService.createCurrency(currency);
        }
        expense.setCurrency(currencyInDB);

        log.info("Creating new expense " + expense);
        expense.setOrder(orderInDB);
        this.expenseRepository.save(expense);
    }

    public List<Expense> findAllExpenses() {
        return this.expenseRepository.findAll();
    }

    public Expense findExpense(Integer id) {
        Optional<Expense> expense = this.expenseRepository.findById(id);
        return expense.orElseThrow(() -> new EntityNotFoundException(
                "Expense with id " + id + " not found"
        ));
    }

    public Expense updateExpense(Integer id, Expense expense) {
        Expense expenseInDB = this.findExpense(id);

        log.info("Updating expense with id " + id + " from " + expenseInDB);
        expenseInDB.setExpenseType(expense.getExpenseType());

        //TODO: resolve the update issues when id is not provided
        //expenseInDB.setOrder(expense.getOrder());

        Currency currency = expense.getCurrency();
        Currency currencyInDB = this.currencyService.getCurrencyByName(currency.getName());
        if (currencyInDB == null) {
            currencyInDB = this.currencyService.createCurrency(currency);
        }
        expenseInDB.setCurrency(currencyInDB);
        expenseInDB.setDescription(expense.getDescription());
        expenseInDB.setExpenseDate(expense.getExpenseDate());
        expenseInDB.setAmount(expense.getAmount());
        expenseInDB.setUpdatedAt(expense.getUpdatedAt());

        expenseInDB = this.expenseRepository.save(expenseInDB);
        return expenseInDB;
    }

    public void deleteExpense(Integer id) {
        Expense expenseInDB = this.findExpense(id);
        log.info("Deleting expense with id " + id);
        this.expenseRepository.delete(expenseInDB);
    }
}
