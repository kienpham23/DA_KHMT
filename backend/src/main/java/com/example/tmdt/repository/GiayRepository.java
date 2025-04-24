package com.example.tmdt.repository;

import com.example.tmdt.entity.Giay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GiayRepository extends JpaRepository<Giay, Integer> {
    List<Giay> findByLoai(String loai);
    List<Giay> findBySize(Integer size);
    List<Giay> findByTenContaining(String ten);
}