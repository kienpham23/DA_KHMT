package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "donhang")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_khachhang")
    private KhachHang khachHang;

    @Column(nullable = false)
    private LocalDate ngaydat;

    @Column(nullable = false)
    private BigDecimal tongtien;

    private LocalDateTime ngaytao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrangThai trangthai;

    public enum TrangThai {
        CHỜ_XÁC_NHẬN, ĐÃ_GIAO, ĐANG_GIAO, ĐÃ_HỦY
    }

    @PrePersist
    protected void onCreate() {
        ngaytao = LocalDateTime.now();
    }
}