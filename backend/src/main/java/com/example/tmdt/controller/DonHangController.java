package com.example.tmdt.controller;

import com.example.tmdt.entity.DonHang;
import com.example.tmdt.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donhang")
@CrossOrigin(origins = "*")
public class DonHangController {
    @Autowired
    private DonHangService donHangService;

    @GetMapping
    public List<DonHang> getAllDonHang() {
        return donHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonHang> getDonHangById(@PathVariable Integer id) {
        return donHangService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DonHang createDonHang(@RequestBody DonHang donHang) {
        return donHangService.save(donHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonHang> updateDonHang(@PathVariable Integer id, @RequestBody DonHang donHang) {
        return donHangService.findById(id)
                .map(existingDonHang -> {
                    donHang.setId(id);
                    return ResponseEntity.ok(donHangService.save(donHang));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonHang(@PathVariable Integer id) {
        return donHangService.findById(id)
                .map(donHang -> {
                    donHangService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/khachhang/{id}")
    public List<DonHang> findByKhachHangId(@PathVariable Integer id) {
        return donHangService.findByKhachHangId(id);
    }

    @GetMapping("/trangthai/{trangthai}")
    public List<DonHang> findByTrangthai(@PathVariable DonHang.TrangThai trangthai) {
        return donHangService.findByTrangthai(trangthai);
    }
}