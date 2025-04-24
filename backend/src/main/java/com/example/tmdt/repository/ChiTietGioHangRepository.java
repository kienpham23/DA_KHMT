package com.example.tmdt.repository;

import com.example.tmdt.entity.ChiTietGioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Integer> {
    List<ChiTietGioHang> findByGioHangId(Integer gioHangId);
    void deleteByGioHangId(Integer gioHangId);
}