package com.example.tmdt.controller;

import com.example.tmdt.entity.YeuThich;
import com.example.tmdt.service.YeuThichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yeuthich")
@CrossOrigin(origins = "*")
public class YeuThichController {
    @Autowired
    private YeuThichService yeuThichService;

    @GetMapping
    public List<YeuThich> getAllYeuThich() {
        return yeuThichService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<YeuThich> getYeuThichById(@PathVariable Integer id) {
        return yeuThichService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public YeuThich createYeuThich(@RequestBody YeuThich yeuThich) {
        return yeuThichService.save(yeuThich);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYeuThich(@PathVariable Integer id) {
        return yeuThichService.findById(id)
                .map(yeuThich -> {
                    yeuThichService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/khachhang/{id}")
    public List<YeuThich> findByKhachHangId(@PathVariable Integer id) {
        return yeuThichService.findByKhachHangId(id);
    }

    @DeleteMapping("/khachhang/{khachHangId}/giay/{giayId}")
    public ResponseEntity<Void> deleteByKhachHangIdAndGiayId(
            @PathVariable Integer khachHangId,
            @PathVariable Integer giayId) {
        yeuThichService.deleteByKhachHangIdAndGiayId(khachHangId, giayId);
        return ResponseEntity.ok().build();
    }
}