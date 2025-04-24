package com.example.tmdt.repository;

import com.example.tmdt.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    KhachHang findByNguoiDungId(Integer id);
    KhachHang findByHoten(String hoten);
    KhachHang findBySodienthoai(String sodienthoai);
}