package com.example.tmdt.controller;

import com.example.tmdt.entity.ChiTietGioHang;
import com.example.tmdt.service.ChiTietGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chitietgiohang")
@CrossOrigin(origins = "*")
public class ChiTietGioHangController {
    @Autowired
    private ChiTietGioHangService chiTietGioHangService;

    @GetMapping
    public List<ChiTietGioHang> getAllChiTietGioHang() {
        return chiTietGioHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChiTietGioHang> getChiTietGioHangById(@PathVariable Integer id) {
        return chiTietGioHangService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ChiTietGioHang createChiTietGioHang(@RequestBody ChiTietGioHang chiTietGioHang) {
        return chiTietGioHangService.save(chiTietGioHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChiTietGioHang> updateChiTietGioHang(@PathVariable Integer id, @RequestBody ChiTietGioHang chiTietGioHang) {
        return chiTietGioHangService.findById(id)
                .map(existingChiTietGioHang -> {
                    chiTietGioHang.setId(id);
                    return ResponseEntity.ok(chiTietGioHangService.save(chiTietGioHang));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChiTietGioHang(@PathVariable Integer id) {
        return chiTietGioHangService.findById(id)
                .map(chiTietGioHang -> {
                    chiTietGioHangService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/giohang/{id}")
    public List<ChiTietGioHang> findByGioHangId(@PathVariable Integer id) {
        return chiTietGioHangService.findByGioHangId(id);
    }

    @DeleteMapping("/giohang/{id}")
    public ResponseEntity<Void> deleteByGioHangId(@PathVariable Integer id) {
        chiTietGioHangService.deleteByGioHangId(id);
        return ResponseEntity.ok().build();
    }
}