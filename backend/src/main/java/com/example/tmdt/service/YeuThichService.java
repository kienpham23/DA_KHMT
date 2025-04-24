package com.example.tmdt.service;

import com.example.tmdt.entity.YeuThich;
import com.example.tmdt.repository.YeuThichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class YeuThichService {
    @Autowired
    private YeuThichRepository yeuThichRepository;

    public List<YeuThich> findAll() {
        return yeuThichRepository.findAll();
    }

    public Optional<YeuThich> findById(Integer id) {
        return yeuThichRepository.findById(id);
    }

    public YeuThich save(YeuThich yeuThich) {
        return yeuThichRepository.save(yeuThich);
    }

    public void deleteById(Integer id) {
        yeuThichRepository.deleteById(id);
    }

    public List<YeuThich> findByKhachHangId(Integer khachHangId) {
        return yeuThichRepository.findByKhachHangId(khachHangId);
    }

    public void deleteByKhachHangIdAndGiayId(Integer khachHangId, Integer giayId) {
        yeuThichRepository.deleteByKhachHangIdAndGiayId(khachHangId, giayId);
    }
}