package com.example.tmdt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "khachhang")
 @NoArgsConstructor @AllArgsConstructor
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_nguoidung")
    private NguoiDung nguoiDung;

    @Column(nullable = false)
    private String hoten;

    @Column(nullable = false)
    private LocalDate ngaysinh;

    @Column(nullable = false)
    private String sodienthoai;

    @Column(nullable = false)
    private String diachi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GioiTinh gioitinh;

    public enum GioiTinh {
        nam, nu
    }

    public Integer getId() {
        return id;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public String getHoten() {
        return hoten;
    }

    public LocalDate getNgaysinh() {
        return ngaysinh;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public GioiTinh getGioitinh() {
        return gioitinh;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setNgaysinh(LocalDate ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setGioitinh(GioiTinh gioitinh) {
        this.gioitinh = gioitinh;
    }
}