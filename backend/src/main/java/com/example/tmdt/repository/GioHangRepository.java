package com.example.tmdt.repository;

import com.example.tmdt.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {
    List<GioHang> findByKhachHangId(Integer khachHangId);
    GioHang findByKhachHangIdAndTrangthai(Integer khachHangId, GioHang.TrangThai trangthai);
}