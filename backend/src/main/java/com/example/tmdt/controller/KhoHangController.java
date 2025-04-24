package com.example.tmdt.controller;

import com.example.tmdt.entity.KhoHang;
import com.example.tmdt.service.KhoHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khohang")
@CrossOrigin(origins = "*")
public class KhoHangController {
    @Autowired
    private KhoHangService khoHangService;

    @GetMapping
    public List<KhoHang> getAllKhoHang() {
        return khoHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhoHang> getKhoHangById(@PathVariable Integer id) {
        return khoHangService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public KhoHang createKhoHang(@RequestBody KhoHang khoHang) {
        return khoHangService.save(khoHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KhoHang> updateKhoHang(@PathVariable Integer id, @RequestBody KhoHang khoHang) {
        return khoHangService.findById(id)
                .map(existingKhoHang -> {
                    khoHang.setId(id);
                    return ResponseEntity.ok(khoHangService.save(khoHang));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKhoHang(@PathVariable Integer id) {
        return khoHangService.findById(id)
                .map(khoHang -> {
                    khoHangService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/giay/{id}")
    public ResponseEntity<KhoHang> findByGiayId(@PathVariable Integer id) {
        KhoHang khoHang = khoHangService.findByGiayId(id);
        return khoHang != null ? ResponseEntity.ok(khoHang) : ResponseEntity.notFound().build();
    }

    @GetMapping("/soluong/{soLuong}")
    public List<KhoHang> findBySoLuongLessThan(@PathVariable Integer soLuong) {
        return khoHangService.findBySoLuongLessThan(soLuong);
    }
}