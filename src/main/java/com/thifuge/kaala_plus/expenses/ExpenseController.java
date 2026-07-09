package com.thifuge.kaala_plus.expenses;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public void createExpense(@RequestBody Expense expense) {
        this.expenseService.createExpense(expense);
    }

    @GetMapping
    public List<Expense> findAllExpenses() {
        return this.expenseService.findAllExpenses();
    }

    @GetMapping(path = "{id}")
    public Expense findExpense(@PathVariable Integer id) {
        return this.expenseService.findExpense(id);
    }

    @PutMapping(path = "{id}")
    public Expense updateExpense(@PathVariable Integer id, @RequestBody Expense expense) {
        return this.expenseService.updateExpense(id, expense);
    }

    @DeleteMapping(path = "{id}")
    public void deleteExpense(@PathVariable Integer id) {
        this.expenseService.deleteExpense(id);
    }

}
