package com.example.tmdt.service;

import com.example.tmdt.entity.BaoCao;
import com.example.tmdt.repository.BaoCaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BaoCaoService {
    @Autowired
    private BaoCaoRepository baoCaoRepository;

    public List<BaoCao> findAll() {
        return baoCaoRepository.findAll();
    }

    public Optional<BaoCao> findById(Integer id) {
        return baoCaoRepository.findById(id);
    }

    public BaoCao save(BaoCao baoCao) {
        return baoCaoRepository.save(baoCao);
    }

    public void deleteById(Integer id) {
        baoCaoRepository.deleteById(id);
    }

    public List<BaoCao> findByThangAndNam(Integer thang, Integer nam) {
        return baoCaoRepository.findByThangAndNam(thang, nam);
    }

    public List<BaoCao> findByNam(Integer nam) {
        return baoCaoRepository.findByNam(nam);
    }
}