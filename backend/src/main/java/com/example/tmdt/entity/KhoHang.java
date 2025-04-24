package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "khohang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhoHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_giay")
    private Giay giay;

    @Column(nullable = false)
    private Integer soLuong;
}