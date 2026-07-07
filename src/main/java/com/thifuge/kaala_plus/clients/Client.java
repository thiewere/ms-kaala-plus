package com.thifuge.kaala_plus.clients;

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
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Integer id;

    @Column(name = "firstname", length = 30)
    private String firstname;

    @Column(name = "lastname", length = 35)
    private String lastname;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "country", length = 25)
    private String country;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @ColumnDefault("now()")
    @Column(name = "updated_at")
    private LocalDate updatedAt;


}