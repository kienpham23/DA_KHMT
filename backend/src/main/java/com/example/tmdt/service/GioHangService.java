package com.example.tmdt.service;

import com.example.tmdt.entity.GioHang;
import com.example.tmdt.repository.GioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GioHangService {
    @Autowired
    private GioHangRepository gioHangRepository;

    public List<GioHang> findAll() {
        return gioHangRepository.findAll();
    }

    public Optional<GioHang> findById(Integer id) {
        return gioHangRepository.findById(id);
    }

    public GioHang save(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }

    public void deleteById(Integer id) {
        gioHangRepository.deleteById(id);
    }

    public List<GioHang> findByKhachHangId(Integer khachHangId) {
        return gioHangRepository.findByKhachHangId(khachHangId);
    }

    public GioHang findByKhachHangIdAndTrangthai(Integer khachHangId, GioHang.TrangThai trangthai) {
        return gioHangRepository.findByKhachHangIdAndTrangthai(khachHangId, trangthai);
    }
}