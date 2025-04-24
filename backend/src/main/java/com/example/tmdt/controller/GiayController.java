package com.example.tmdt.controller;

import com.example.tmdt.entity.Giay;
import com.example.tmdt.service.GiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Thêm imports
import org.springframework.core.env.Environment;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;

@RestController
@RequestMapping("/api/giay")
@CrossOrigin(origins = "*")
public class GiayController {
    @Autowired
    private GiayService giayService;
    
    @Autowired
    private Environment env;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllGiay(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable paging = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<Giay> pageGiay = giayService.findAll(paging);
        
        Map<String, Object> response = new HashMap<>();
        response.put("giay", pageGiay.getContent());
        response.put("currentPage", pageGiay.getNumber());
        response.put("totalItems", pageGiay.getTotalElements());
        response.put("totalPages", pageGiay.getTotalPages());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Giay> getGiayById(@PathVariable Integer id) {
        return giayService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Giay createGiay(@RequestBody Giay giay) {
        return giayService.save(giay);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Giay> updateGiay(@PathVariable Integer id, @RequestBody Giay giay) {
        return giayService.findById(id)
                .map(existingGiay -> {
                    giay.setId(id);
                    return ResponseEntity.ok(giayService.save(giay));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiay(@PathVariable Integer id) {
        return giayService.findById(id)
                .map(giay -> {
                    giayService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/loai/{loai}")
    public List<Giay> findByLoai(@PathVariable String loai) {
        return giayService.findByLoai(loai);
    }

    @GetMapping("/size/{size}")
    public List<Giay> findBySize(@PathVariable Integer size) {
        return giayService.findBySize(size);
    }

    @GetMapping("/search/{ten}")
    public List<Giay> findByTenContaining(@PathVariable String ten) {
        return giayService.findByTenContaining(ten);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Giay> uploadHinhAnh(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile file) {
        try {
            // In thông tin debug về đường dẫn
            System.out.println("Project Directory: " + System.getProperty("user.dir"));
            
            // Kiểm tra file

            if (file == null || file.isEmpty()) {
                System.out.println("File is empty");
                return ResponseEntity.badRequest().body(null);
            }
    
            // In thông tin debug
            System.out.println("Receiving file: " + file.getOriginalFilename());
            System.out.println("File size: " + file.getSize());
            System.out.println("Content type: " + file.getContentType());
    
            // Kiểm tra định dạng file
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                System.out.println("Invalid file type: " + contentType);
                return ResponseEntity.badRequest().body(null);
            }
    
            // Tạo tên file unique
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            
            // Tạo đường dẫn tuyệt đối cho thư mục uploads
            String projectDir = System.getProperty("user.dir");
            File uploadDir = new File(projectDir, "uploads");
            System.out.println("Upload Directory: " + uploadDir.getAbsolutePath());
            
            if (!uploadDir.exists()) {
                boolean created = uploadDir.mkdirs();
                if (!created) {
                    System.out.println("Could not create upload directory");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
            }
            
            // Lưu file
            Path filePath = uploadDir.toPath().resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Lưu đường dẫn vào database
            String fileUrl = "/uploads/" + fileName;
            System.out.println("File saved at: " + fileUrl);
            
            // Kiểm tra xem giày có tồn tại không
            Giay existingGiay = giayService.findById(id).orElse(null);
            if (existingGiay == null) {
                System.out.println("Giay not found with id: " + id);
                return ResponseEntity.notFound().build();
            }
            
            Giay updatedGiay = giayService.updateHinhAnh(id, fileUrl);
            return ResponseEntity.ok(updatedGiay);
    
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error uploading file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}