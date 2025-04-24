package com.example.tmdt.controller;

import com.example.tmdt.entity.GioHang;
import com.example.tmdt.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/giohang")
@CrossOrigin(origins = "*")
public class GioHangController {
    @Autowired
    private GioHangService gioHangService;

    @GetMapping
    public List<GioHang> getAllGioHang() {
        return gioHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GioHang> getGioHangById(@PathVariable Integer id) {
        return gioHangService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public GioHang createGioHang(@RequestBody GioHang gioHang) {
        return gioHangService.save(gioHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GioHang> updateGioHang(@PathVariable Integer id, @RequestBody GioHang gioHang) {
        return gioHangService.findById(id)
                .map(existingGioHang -> {
                    gioHang.setId(id);
                    return ResponseEntity.ok(gioHangService.save(gioHang));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGioHang(@PathVariable Integer id) {
        return gioHangService.findById(id)
                .map(gioHang -> {
                    gioHangService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/khachhang/{id}")
    public List<GioHang> findByKhachHangId(@PathVariable Integer id) {
        return gioHangService.findByKhachHangId(id);
    }

    @GetMapping("/khachhang/{id}/trangthai/{trangthai}")
    public ResponseEntity<GioHang> findByKhachHangIdAndTrangthai(
            @PathVariable Integer id,
            @PathVariable GioHang.TrangThai trangthai) {
        GioHang gioHang = gioHangService.findByKhachHangIdAndTrangthai(id, trangthai);
        return gioHang != null ? ResponseEntity.ok(gioHang) : ResponseEntity.notFound().build();
    }
}