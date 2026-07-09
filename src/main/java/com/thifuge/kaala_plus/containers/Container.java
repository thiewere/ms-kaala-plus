package com.thifuge.kaala_plus.containers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "containers")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "container_id", nullable = false)
    private Integer id;

    @Column(name = "reference", length = 40)
    private String reference;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @ColumnDefault("now()")
    @Column(name = "updated_at")
    private LocalDate updatedAt;


}