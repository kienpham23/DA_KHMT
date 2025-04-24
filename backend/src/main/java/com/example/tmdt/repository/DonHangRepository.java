package com.example.tmdt.repository;

import com.example.tmdt.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
    List<DonHang> findByKhachHangId(Integer khachHangId);
    List<DonHang> findByTrangthai(DonHang.TrangThai trangthai);
}