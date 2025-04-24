package com.example.tmdt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

@Data
public class DangKyRequest {
    private String tendangnhap;
    private String email;
    private String matkhau;
    private String hoten;
    private String sodienthoai;
    private String diachi;
    private String gioitinh;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngaysinh;
}