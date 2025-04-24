package com.example.tmdt.repository;

import com.example.tmdt.entity.VanChuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VanChuyenRepository extends JpaRepository<VanChuyen, Integer> {
    VanChuyen findByDonHangId(Integer donHangId);
}