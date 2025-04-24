package com.example.tmdt.service;

import com.example.tmdt.entity.PhiVanChuyen;
import com.example.tmdt.repository.PhiVanChuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhiVanChuyenService {
    @Autowired
    private PhiVanChuyenRepository phiVanChuyenRepository;

    public List<PhiVanChuyen> findAll() {
        return phiVanChuyenRepository.findAll();
    }

    public Optional<PhiVanChuyen> findById(Integer id) {
        return phiVanChuyenRepository.findById(id);
    }

    public PhiVanChuyen save(PhiVanChuyen phiVanChuyen) {
        return phiVanChuyenRepository.save(phiVanChuyen);
    }

    public void deleteById(Integer id) {
        phiVanChuyenRepository.deleteById(id);
    }

    public PhiVanChuyen findByVanChuyenId(Integer vanChuyenId) {
        return phiVanChuyenRepository.findByVanChuyenId(vanChuyenId);
    }
}