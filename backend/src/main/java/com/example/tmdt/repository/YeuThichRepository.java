package com.example.tmdt.repository;

import com.example.tmdt.entity.YeuThich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface YeuThichRepository extends JpaRepository<YeuThich, Integer> {
    List<YeuThich> findByKhachHangId(Integer khachHangId);
    void deleteByKhachHangIdAndGiayId(Integer khachHangId, Integer giayId);
}