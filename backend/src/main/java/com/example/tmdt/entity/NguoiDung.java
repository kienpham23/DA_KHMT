package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nguoidung")
@NoArgsConstructor @AllArgsConstructor
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String tendangnhap;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String matkhau;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    public Integer getId() {
        return id;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public String getEmail() {
        return email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}