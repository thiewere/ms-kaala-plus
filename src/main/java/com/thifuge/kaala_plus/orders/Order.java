package com.thifuge.kaala_plus.orders;

import com.thifuge.kaala_plus.clients.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "order_state", length = 15)
    private String orderState;

    @ColumnDefault("now()")
    @Column(name = "updated_at")
    private LocalDate updatedAt;


}