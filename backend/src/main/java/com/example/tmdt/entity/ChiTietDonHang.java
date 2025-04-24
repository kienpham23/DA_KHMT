package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "chitietdonhang")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_donhang", nullable = false)
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "id_giay", nullable = false)
    private Giay giay;

    @Column(nullable = false)
    private Integer soluong;

    @Column(nullable = false)
    private BigDecimal gia;
}
