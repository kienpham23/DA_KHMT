package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "giohang")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_khachhang")
    private KhachHang khachHang;

    private LocalDateTime ngaytao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrangThai trangthai;

    public enum TrangThai {
        ĐANG_MUA, ĐÃ_THANH_TOÁN
    }

    @PrePersist
    protected void onCreate() {
        ngaytao = LocalDateTime.now();
    }
}