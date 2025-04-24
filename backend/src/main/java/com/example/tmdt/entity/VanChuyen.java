package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vanchuyen")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VanChuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_donhang")
    private DonHang donHang;

    @Column(nullable = false)
    private String phuongthuc;

    @Column(nullable = false)
    private String diachigiaohang;

    private LocalDate ngaygiaohang;

    private BigDecimal phivanchuyen;

    private String trangthai;
}