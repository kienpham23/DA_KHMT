package com.example.tmdt.service;

import com.example.tmdt.entity.ChiTietDonHang;
import com.example.tmdt.repository.ChiTietDonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChiTietDonHangService {
    @Autowired
    private ChiTietDonHangRepository chiTietDonHangRepository;

    public List<ChiTietDonHang> findAll() {
        return chiTietDonHangRepository.findAll();
    }

    public Optional<ChiTietDonHang> findById(Integer id) {
        return chiTietDonHangRepository.findById(id);
    }

    public ChiTietDonHang save(ChiTietDonHang chiTietDonHang) {
        return chiTietDonHangRepository.save(chiTietDonHang);
    }

    public void deleteById(Integer id) {
        chiTietDonHangRepository.deleteById(id);
    }

    public List<ChiTietDonHang> findByDonHangId(Integer donHangId) {
        return chiTietDonHangRepository.findByDonHangId(donHangId);
    }

    public Page<ChiTietDonHang> findByDonHangId(Integer donHangId, Pageable pageable) {
        return chiTietDonHangRepository.findByDonHangId(donHangId, pageable);
    }
}