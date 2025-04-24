package com.example.tmdt.controller;

import com.example.tmdt.entity.NguoiDung;
import com.example.tmdt.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nguoidung")
@CrossOrigin(origins = "*")
public class NguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NguoiDung> getNguoiDungById(@PathVariable Integer id) {
        return nguoiDungService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public NguoiDung createNguoiDung(@RequestBody NguoiDung nguoiDung) {
        return nguoiDungService.save(nguoiDung);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NguoiDung> updateNguoiDung(@PathVariable Integer id, @RequestBody NguoiDung nguoiDung) {
        return nguoiDungService.findById(id)
                .map(existingNguoiDung -> {
                    nguoiDung.setId(id);
                    return ResponseEntity.ok(nguoiDungService.save(nguoiDung));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNguoiDung(@PathVariable Integer id) {
        return nguoiDungService.findById(id)
                .map(nguoiDung -> {
                    nguoiDungService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<NguoiDung> findByEmail(@PathVariable String email) {
        NguoiDung nguoiDung = nguoiDungService.findByEmail(email);
        return nguoiDung != null ? ResponseEntity.ok(nguoiDung) : ResponseEntity.notFound().build();
    }

    @GetMapping("/tendangnhap/{tendangnhap}")
    public ResponseEntity<NguoiDung> findByTendangnhap(@PathVariable String tendangnhap) {
        NguoiDung nguoiDung = nguoiDungService.findByTendangnhap(tendangnhap);
        return nguoiDung != null ? ResponseEntity.ok(nguoiDung) : ResponseEntity.notFound().build();
    }
}