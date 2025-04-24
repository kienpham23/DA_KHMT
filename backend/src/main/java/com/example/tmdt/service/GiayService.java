package com.example.tmdt.service;

import com.example.tmdt.entity.Giay;
import com.example.tmdt.repository.GiayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiayService {
    @Autowired
    private GiayRepository giayRepository;

    public Page<Giay> findAll(Pageable pageable) {
        return giayRepository.findAll(pageable);
    }

    public List<Giay> findAll() {
        return giayRepository.findAll();
    }

    public Optional<Giay> findById(Integer id) {
        return giayRepository.findById(id);
    }

    public Giay save(Giay giay) {
        return giayRepository.save(giay);
    }

    public void deleteById(Integer id) {
        giayRepository.deleteById(id);
    }

    public List<Giay> findByLoai(String loai) {
        return giayRepository.findByLoai(loai);
    }

    public List<Giay> findBySize(Integer size) {
        return giayRepository.findBySize(size);
    }

    public List<Giay> findByTenContaining(String ten) {
        return giayRepository.findByTenContaining(ten);
    }
    public Giay updateHinhAnh(Integer id, String imageUrl) {
        return giayRepository.findById(id)
                .map(giay -> {
                    giay.setHinhAnh(imageUrl);
                    return giayRepository.save(giay);
                })
                .orElse(null);
    }
}