package com.example.tmdt.controller;

import com.example.tmdt.entity.KhachHang;
import com.example.tmdt.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khachhang")
@CrossOrigin(origins = "*")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping
    public List<KhachHang> getAllKhachHang() {
        return khachHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhachHang> getKhachHangById(@PathVariable Integer id) {
        return khachHangService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public KhachHang createKhachHang(@RequestBody KhachHang khachHang) {
        return khachHangService.save(khachHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KhachHang> updateKhachHang(@PathVariable Integer id, @RequestBody KhachHang khachHang) {
        return khachHangService.findById(id)
                .map(existingKhachHang -> {
                    khachHang.setId(id);
                    return ResponseEntity.ok(khachHangService.save(khachHang));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKhachHang(@PathVariable Integer id) {
        return khachHangService.findById(id)
                .map(khachHang -> {
                    khachHangService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nguoidung/{id}")
    public ResponseEntity<KhachHang> findByNguoiDungId(@PathVariable Integer id) {
        KhachHang khachHang = khachHangService.findByNguoiDungId(id);
        return khachHang != null ? ResponseEntity.ok(khachHang) : ResponseEntity.notFound().build();
    }
}