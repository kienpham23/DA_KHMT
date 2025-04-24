package com.example.tmdt.controller;

import com.example.tmdt.entity.ChiTietDonHang;
import com.example.tmdt.service.ChiTietDonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chitietdonhang")
@CrossOrigin(origins = "*")
public class ChiTietDonHangController {
    @Autowired
    private ChiTietDonHangService chiTietDonHangService;

    @GetMapping
    public List<ChiTietDonHang> getAllChiTietDonHang() {
        return chiTietDonHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChiTietDonHang> getChiTietDonHangById(@PathVariable Integer id) {
        return chiTietDonHangService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ChiTietDonHang createChiTietDonHang(@RequestBody ChiTietDonHang chiTietDonHang) {
        return chiTietDonHangService.save(chiTietDonHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChiTietDonHang> updateChiTietDonHang(@PathVariable Integer id, @RequestBody ChiTietDonHang chiTietDonHang) {
        return chiTietDonHangService.findById(id)
                .map(existingChiTietDonHang -> {
                    chiTietDonHang.setId(id);
                    return ResponseEntity.ok(chiTietDonHangService.save(chiTietDonHang));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChiTietDonHang(@PathVariable Integer id) {
        return chiTietDonHangService.findById(id)
                .map(chiTietDonHang -> {
                    chiTietDonHangService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/donhang/{id}")
    public List<ChiTietDonHang> findByDonHangId(@PathVariable Integer id) {
        return chiTietDonHangService.findByDonHangId(id);
    }
}