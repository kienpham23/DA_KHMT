package com.example.tmdt.repository;

import com.example.tmdt.entity.ChiTietDonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Integer> {
    List<ChiTietDonHang> findByDonHangId(Integer donHangId);
    Page<ChiTietDonHang> findByDonHangId(Integer donHangId, Pageable pageable);

}