package com.thifuge.kaala_plus.orders;

import com.thifuge.kaala_plus.clients.Client;
import com.thifuge.kaala_plus.containers.Container;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @Column(name = "reference", nullable = false, length = 40)
    private String reference;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "client_id")
    private Client client;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "completed_at")
    private LocalDate completedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state", length = 30)
    private OrderState orderState;

    @ColumnDefault("now()")
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "container_id")
    private Container container;
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ColumnDefault("0")
    @Column(name = "totalprice", nullable = false)
    private BigDecimal totalprice = BigDecimal.ZERO;


}