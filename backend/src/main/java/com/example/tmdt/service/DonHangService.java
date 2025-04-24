package com.example.tmdt.service;

import com.example.tmdt.entity.DonHang;
import com.example.tmdt.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DonHangService {
    @Autowired
    private DonHangRepository donHangRepository;

    public List<DonHang> findAll() {
        return donHangRepository.findAll();
    }

    public Optional<DonHang> findById(Integer id) {
        return donHangRepository.findById(id);
    }

    public DonHang save(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    public void deleteById(Integer id) {
        donHangRepository.deleteById(id);
    }

    public List<DonHang> findByKhachHangId(Integer khachHangId) {
        return donHangRepository.findByKhachHangId(khachHangId);
    }

    public List<DonHang> findByTrangthai(DonHang.TrangThai trangthai) {
        return donHangRepository.findByTrangthai(trangthai);
    }
}