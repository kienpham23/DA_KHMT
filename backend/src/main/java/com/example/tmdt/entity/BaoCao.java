package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "baocao")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BaoCao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String thongke;

    @Column(nullable = false)
    private BigDecimal giatri;

    @Column(nullable = false)
    private Integer thang;

    @Column(nullable = false)
    private Integer nam;
}