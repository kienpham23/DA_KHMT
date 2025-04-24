package com.example.tmdt.controller;

import com.example.tmdt.dto.AuthResponse;
import com.example.tmdt.dto.DangKyRequest;
import com.example.tmdt.dto.DangNhapRequest;
import com.example.tmdt.entity.KhachHang;
import com.example.tmdt.entity.NguoiDung;
import com.example.tmdt.security.JwtService;
import com.example.tmdt.service.KhachHangService;
import com.example.tmdt.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private NguoiDungService nguoiDungService;
    
    @Autowired
    private KhachHangService khachHangService;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/dangky")
    public ResponseEntity<?> dangKy(@RequestBody DangKyRequest request) {
        // Kiểm tra username và email đã tồn tại chưa
        if (nguoiDungService.existsByTendangnhap(request.getTendangnhap())) {
            return ResponseEntity.badRequest().body("Tên đăng nhập đã tồn tại");
        }
        if (nguoiDungService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email đã tồn tại");
        }

        // Tạo người dùng mới
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setTendangnhap(request.getTendangnhap());
        nguoiDung.setEmail(request.getEmail());
        nguoiDung.setMatkhau(passwordEncoder.encode(request.getMatkhau()));
        nguoiDung.setRole(NguoiDung.Role.USER);
        nguoiDung = nguoiDungService.save(nguoiDung);

        // Tạo khách hàng mới
        KhachHang khachHang = new KhachHang();
        khachHang.setNguoiDung(nguoiDung);
        khachHang.setHoten(request.getHoten());
        khachHang.setSodienthoai(request.getSodienthoai());
        khachHang.setDiachi(request.getDiachi());
        khachHang.setGioitinh(KhachHang.GioiTinh.valueOf(request.getGioitinh().toLowerCase()));
        khachHang.setNgaysinh(request.getNgaysinh());  // Thêm dòng này
        khachHangService.save(khachHang);

        // Tạm thời trả về thông báo thành công
        return ResponseEntity.ok("Đăng ký thành công");
    }

    @PostMapping("/dangnhap")
    public ResponseEntity<?> dangNhap(@RequestBody DangNhapRequest request) {
        System.out.println("Request received: " + request.getTendangnhap());
        NguoiDung nguoiDung = nguoiDungService.findByTendangnhap(request.getTendangnhap());
        
        if (nguoiDung == null) {
            return ResponseEntity.badRequest().body("Tên đăng nhập không tồn tại");
        }

        // Sử dụng PasswordEncoder để kiểm tra mật khẩu
        if (!passwordEncoder.matches(request.getMatkhau(), nguoiDung.getMatkhau())) {
            return ResponseEntity.badRequest().body("Mật khẩu không đúng");
        }

        String token = jwtService.generateToken(request.getTendangnhap(), nguoiDung.getRole().name());
        return ResponseEntity.ok(new AuthResponse(token, nguoiDung.getRole().name()));
    }
}