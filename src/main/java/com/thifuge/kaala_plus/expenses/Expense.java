package com.thifuge.kaala_plus.expenses;

import com.thifuge.kaala_plus.orders.Order;
import com.thifuge.kaala_plus.shared.entities.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id", nullable = false)
    private Integer id;

    @Column(name = "expense_type", length = 30)
    private String expenseType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "description", length = 100)
    private String description;

    @ColumnDefault("now()")
    @Column(name = "expense_date")
    private LocalDate expenseDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @ColumnDefault("now()")
    @Column(name = "updated_at")
    private LocalDate updatedAt;


}