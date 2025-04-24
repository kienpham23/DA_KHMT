package com.example.tmdt.repository;

import com.example.tmdt.entity.KhoHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KhoHangRepository extends JpaRepository<KhoHang, Integer> {
    KhoHang findByGiayId(Integer giayId);
    List<KhoHang> findBySoLuongLessThan(Integer soLuong);
}