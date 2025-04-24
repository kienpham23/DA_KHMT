package com.example.tmdt.repository;

import com.example.tmdt.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    NguoiDung findByEmail(String email);
    NguoiDung findByTendangnhap(String tendangnhap);
    boolean existsByEmail(String email);
    boolean existsByTendangnhap(String tendangnhap);
}