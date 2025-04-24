package com.example.tmdt.service;

import com.example.tmdt.entity.ChiTietGioHang;
import com.example.tmdt.repository.ChiTietGioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChiTietGioHangService {
    @Autowired
    private ChiTietGioHangRepository chiTietGioHangRepository;

    public List<ChiTietGioHang> findAll() {
        return chiTietGioHangRepository.findAll();
    }

    public Optional<ChiTietGioHang> findById(Integer id) {
        return chiTietGioHangRepository.findById(id);
    }

    public ChiTietGioHang save(ChiTietGioHang chiTietGioHang) {
        return chiTietGioHangRepository.save(chiTietGioHang);
    }

    public void deleteById(Integer id) {
        chiTietGioHangRepository.deleteById(id);
    }

    public List<ChiTietGioHang> findByGioHangId(Integer gioHangId) {
        return chiTietGioHangRepository.findByGioHangId(gioHangId);
    }

    public void deleteByGioHangId(Integer gioHangId) {
        chiTietGioHangRepository.deleteByGioHangId(gioHangId);
    }
}