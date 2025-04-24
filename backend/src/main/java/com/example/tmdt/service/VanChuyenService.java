package com.example.tmdt.service;

import com.example.tmdt.entity.VanChuyen;
import com.example.tmdt.repository.VanChuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VanChuyenService {
    @Autowired
    private VanChuyenRepository vanChuyenRepository;

    public List<VanChuyen> findAll() {
        return vanChuyenRepository.findAll();
    }

    public Optional<VanChuyen> findById(Integer id) {
        return vanChuyenRepository.findById(id);
    }

    public VanChuyen save(VanChuyen vanChuyen) {
        return vanChuyenRepository.save(vanChuyen);
    }

    public void deleteById(Integer id) {
        vanChuyenRepository.deleteById(id);
    }

    public VanChuyen findByDonHangId(Integer donHangId) {
        return vanChuyenRepository.findByDonHangId(donHangId);
    }
}