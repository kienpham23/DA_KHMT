package com.example.tmdt.service;

import com.example.tmdt.entity.NguoiDung;
import com.example.tmdt.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public List<NguoiDung> findAll() {
        return nguoiDungRepository.findAll();
    }

    public Optional<NguoiDung> findById(Integer id) {
        return nguoiDungRepository.findById(id);
    }

    public NguoiDung save(NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    public void deleteById(Integer id) {
        nguoiDungRepository.deleteById(id);
    }

    public NguoiDung findByEmail(String email) {
        return nguoiDungRepository.findByEmail(email);
    }

    public NguoiDung findByTendangnhap(String tendangnhap) {
        return nguoiDungRepository.findByTendangnhap(tendangnhap);
    }

    public boolean existsByEmail(String email) {
        return nguoiDungRepository.existsByEmail(email);
    }

    public boolean existsByTendangnhap(String tendangnhap) {
        return nguoiDungRepository.existsByTendangnhap(tendangnhap);
    }
}