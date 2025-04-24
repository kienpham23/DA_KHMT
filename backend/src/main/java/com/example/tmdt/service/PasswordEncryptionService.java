package com.example.tmdt.service;
import com.example.tmdt.entity.NguoiDung;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordEncryptionService {

    @Autowired
    private NguoiDungService nguoiDungService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void encryptPasswords() {
        List<NguoiDung> nguoiDungList = nguoiDungService.findAll();
        for (NguoiDung nguoiDung : nguoiDungList) {
            if ("123456".equals(nguoiDung.getMatkhau())) { // Kiểm tra mật khẩu hiện tại
                String encodedPassword = passwordEncoder.encode(nguoiDung.getMatkhau());
                nguoiDung.setMatkhau(encodedPassword);
                nguoiDungService.save(nguoiDung);
            }
        }
    }
}