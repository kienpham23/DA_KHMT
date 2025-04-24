package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "giay")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Giay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String ten;

    @Column(nullable = false)
    private String loai;

    @Column(nullable = false)
    private BigDecimal gia;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(nullable = false)
    private Integer size;

    // Getter và Setter cho hinhAnh
    public String getHinhAnh() {
        return hinhAnh;
    }
    
    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}