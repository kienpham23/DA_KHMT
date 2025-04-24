package com.example.tmdt.controller;

import com.example.tmdt.entity.VanChuyen;
import com.example.tmdt.service.VanChuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vanchuyen")
@CrossOrigin(origins = "*")
public class VanChuyenController {
    @Autowired
    private VanChuyenService vanChuyenService;

    @GetMapping
    public List<VanChuyen> getAllVanChuyen() {
        return vanChuyenService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VanChuyen> getVanChuyenById(@PathVariable Integer id) {
        return vanChuyenService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public VanChuyen createVanChuyen(@RequestBody VanChuyen vanChuyen) {
        return vanChuyenService.save(vanChuyen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VanChuyen> updateVanChuyen(@PathVariable Integer id, @RequestBody VanChuyen vanChuyen) {
        return vanChuyenService.findById(id)
                .map(existingVanChuyen -> {
                    vanChuyen.setId(id);
                    return ResponseEntity.ok(vanChuyenService.save(vanChuyen));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVanChuyen(@PathVariable Integer id) {
        return vanChuyenService.findById(id)
                .map(vanChuyen -> {
                    vanChuyenService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/donhang/{id}")
    public ResponseEntity<VanChuyen> findByDonHangId(@PathVariable Integer id) {
        VanChuyen vanChuyen = vanChuyenService.findByDonHangId(id);
        return vanChuyen != null ? ResponseEntity.ok(vanChuyen) : ResponseEntity.notFound().build();
    }
}