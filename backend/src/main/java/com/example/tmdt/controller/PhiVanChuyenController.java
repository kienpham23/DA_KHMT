package com.example.tmdt.controller;

import com.example.tmdt.entity.PhiVanChuyen;
import com.example.tmdt.service.PhiVanChuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phivanchuyen")
@CrossOrigin(origins = "*")
public class PhiVanChuyenController {
    @Autowired
    private PhiVanChuyenService phiVanChuyenService;

    @GetMapping
    public List<PhiVanChuyen> getAllPhiVanChuyen() {
        return phiVanChuyenService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhiVanChuyen> getPhiVanChuyenById(@PathVariable Integer id) {
        return phiVanChuyenService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PhiVanChuyen createPhiVanChuyen(@RequestBody PhiVanChuyen phiVanChuyen) {
        return phiVanChuyenService.save(phiVanChuyen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhiVanChuyen> updatePhiVanChuyen(@PathVariable Integer id, @RequestBody PhiVanChuyen phiVanChuyen) {
        return phiVanChuyenService.findById(id)
                .map(existingPhiVanChuyen -> {
                    phiVanChuyen.setId(id);
                    return ResponseEntity.ok(phiVanChuyenService.save(phiVanChuyen));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhiVanChuyen(@PathVariable Integer id) {
        return phiVanChuyenService.findById(id)
                .map(phiVanChuyen -> {
                    phiVanChuyenService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vanchuyen/{id}")
    public ResponseEntity<PhiVanChuyen> findByVanChuyenId(@PathVariable Integer id) {
        PhiVanChuyen phiVanChuyen = phiVanChuyenService.findByVanChuyenId(id);
        return phiVanChuyen != null ? ResponseEntity.ok(phiVanChuyen) : ResponseEntity.notFound().build();
    }
}