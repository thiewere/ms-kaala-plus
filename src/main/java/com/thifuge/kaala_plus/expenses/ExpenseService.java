package com.thifuge.kaala_plus.expenses;

import com.thifuge.kaala_plus.orders.Order;
import com.thifuge.kaala_plus.orders.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final OrderService orderService;

    public void createExpense(Expense expense) {
        Order order = expense.getOrder();
        Order orderInDB = this.orderService.findOrderByReference(order.getReference());
        expense.setOrder(orderInDB);
        this.expenseRepository.save(expense);
    }
}
