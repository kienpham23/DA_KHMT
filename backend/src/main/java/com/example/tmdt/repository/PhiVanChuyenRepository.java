package com.example.tmdt.repository;

import com.example.tmdt.entity.PhiVanChuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhiVanChuyenRepository extends JpaRepository<PhiVanChuyen, Integer> {
    PhiVanChuyen findByVanChuyenId(Integer vanChuyenId);
}