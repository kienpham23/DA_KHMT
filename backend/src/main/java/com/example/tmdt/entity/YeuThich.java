package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "yeuthich")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class YeuThich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_khachhang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "id_giay")
    private Giay giay;
}