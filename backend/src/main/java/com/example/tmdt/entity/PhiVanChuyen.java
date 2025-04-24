package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "phivanchuyen")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PhiVanChuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_vanchuyen")
    private VanChuyen vanChuyen;

    @Column(nullable = false)
    private BigDecimal phi;
}