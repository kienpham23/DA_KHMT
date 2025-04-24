package com.example.tmdt.controller;

import com.example.tmdt.entity.BaoCao;
import com.example.tmdt.service.BaoCaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baocao")
@CrossOrigin(origins = "*")
public class BaoCaoController {
    @Autowired
    private BaoCaoService baoCaoService;

    @GetMapping
    public List<BaoCao> getAllBaoCao() {
        return baoCaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaoCao> getBaoCaoById(@PathVariable Integer id) {
        return baoCaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BaoCao createBaoCao(@RequestBody BaoCao baoCao) {
        return baoCaoService.save(baoCao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaoCao> updateBaoCao(@PathVariable Integer id, @RequestBody BaoCao baoCao) {
        return baoCaoService.findById(id)
                .map(existingBaoCao -> {
                    baoCao.setId(id);
                    return ResponseEntity.ok(baoCaoService.save(baoCao));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaoCao(@PathVariable Integer id) {
        return baoCaoService.findById(id)
                .map(baoCao -> {
                    baoCaoService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/thongke/{thang}/{nam}")
    public List<BaoCao> findByThangAndNam(
            @PathVariable Integer thang,
            @PathVariable Integer nam) {
        return baoCaoService.findByThangAndNam(thang, nam);
    }

    @GetMapping("/thongke/{nam}")
    public List<BaoCao> findByNam(@PathVariable Integer nam) {
        return baoCaoService.findByNam(nam);
    }
}