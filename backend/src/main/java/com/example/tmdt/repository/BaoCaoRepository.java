package com.example.tmdt.repository;

import com.example.tmdt.entity.BaoCao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BaoCaoRepository extends JpaRepository<BaoCao, Integer> {
    List<BaoCao> findByThangAndNam(Integer thang, Integer nam);
    List<BaoCao> findByNam(Integer nam);
}