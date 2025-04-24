package com.example.tmdt.service;

import com.example.tmdt.entity.KhachHang;
import com.example.tmdt.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    public Optional<KhachHang> findById(Integer id) {
        return khachHangRepository.findById(id);
    }

    public KhachHang save(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    public void deleteById(Integer id) {
        khachHangRepository.deleteById(id);
    }

    public KhachHang findByNguoiDungId(Integer id) {
        return khachHangRepository.findByNguoiDungId(id);
    }

    public KhachHang findByHoten(String hoten) {
        return khachHangRepository.findByHoten(hoten);
    }

    public KhachHang findBySodienthoai(String sodienthoai) {
        return khachHangRepository.findBySodienthoai(sodienthoai);
    }
}