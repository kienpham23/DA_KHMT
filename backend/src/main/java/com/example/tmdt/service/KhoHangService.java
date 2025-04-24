package com.example.tmdt.service;

import com.example.tmdt.entity.KhoHang;
import com.example.tmdt.repository.KhoHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KhoHangService {
    @Autowired
    private KhoHangRepository khoHangRepository;

    public List<KhoHang> findAll() {
        return khoHangRepository.findAll();
    }

    public Optional<KhoHang> findById(Integer id) {
        return khoHangRepository.findById(id);
    }

    public KhoHang save(KhoHang khoHang) {
        return khoHangRepository.save(khoHang);
    }

    public void deleteById(Integer id) {
        khoHangRepository.deleteById(id);
    }

    public KhoHang findByGiayId(Integer giayId) {
        return khoHangRepository.findByGiayId(giayId);
    }

    public List<KhoHang> findBySoLuongLessThan(Integer soLuong) {
        return khoHangRepository.findBySoLuongLessThan(soLuong);
    }
}