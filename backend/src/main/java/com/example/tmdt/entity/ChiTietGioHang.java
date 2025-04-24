package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "chitietgiohang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietGioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_giay", nullable = false)
    private Giay giay;

    @ManyToOne
    @JoinColumn(name = "id_giohang", nullable = false)
    private GioHang gioHang;

    @Column(nullable = false)
    private Integer soluong;

    @Column(nullable = false)
    private BigDecimal gia;
}
