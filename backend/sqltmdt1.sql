CREATE DATABASE IF NOT EXISTS tmdt_giay;
USE tmdt_giay;

show tables;

CREATE TABLE nguoidung (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tendangnhap VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    matkhau VARCHAR(100) NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL
);
CREATE TABLE khachhang (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_nguoidung INT,
    hoten VARCHAR(50) NOT NULL,
    ngaysinh DATE NOT NULL,
    sodienthoai VARCHAR(15) NOT NULL,
    diachi VARCHAR(100) NOT NULL,
    gioitinh ENUM('nam', 'nu') NOT NULL,
    FOREIGN KEY (id_nguoidung) REFERENCES nguoidung(id) ON DELETE CASCADE
);
CREATE TABLE giay (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ten VARCHAR(50) NOT NULL,
    loai VARCHAR(50) NOT NULL,
    gia DECIMAL(10, 2) NOT NULL,
    so_luong INT NOT NULL,
    size INT NOT NULL
);

CREATE TABLE donhang (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_khachhang INT,
    ngaydat DATE NOT NULL,
    tongtien DECIMAL(10, 2) NOT NULL,
     ngaytao DATETIME DEFAULT CURRENT_TIMESTAMP,
    trangthai ENUM('CHỜ XÁC NHẬN', 'ĐÃ GIAO', 'ĐANG GIAO', 'ĐÃ HỦY') NOT NULL,
    FOREIGN KEY (id_khachhang) REFERENCES khachhang(id) ON DELETE CASCADE
);
CREATE TABLE chitietdonhang (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_donhang INT,
    id_giay INT,
    soluong INT NOT NULL,
    gia DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_donhang) REFERENCES donhang(id) ON DELETE CASCADE,
    FOREIGN KEY (id_giay) REFERENCES giay(id) ON DELETE CASCADE
);
CREATE TABLE giohang (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_khachhang INT,
    ngaytao DATETIME DEFAULT CURRENT_TIMESTAMP,
    trangthai ENUM('Đang mua', 'Đã thanh toán') NOT NULL,
    FOREIGN KEY (id_khachhang) REFERENCES khachhang(id) ON DELETE CASCADE
);
CREATE TABLE chitietgiohang (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_giay INT,
    id_giohang INT,
    soluong INT NOT NULL,
    gia DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_giay) REFERENCES giay(id),
    FOREIGN KEY (id_giohang) REFERENCES giohang(id) ON DELETE CASCADE
);

CREATE TABLE vanchuyen (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_donhang INT,
    phuongthuc VARCHAR(50) NOT NULL,
    diachigiaohang VARCHAR(100) NOT NULL,
    ngaygiaohang DATE,
    phivanchuyen DECIMAL(10, 2),
    trangthai VARCHAR(50),
    FOREIGN KEY (id_donhang) REFERENCES donhang(id) ON DELETE CASCADE
);
UPDATE vanchuyen SET phivanchuyen = 50 WHERE id = 1;
UPDATE vanchuyen SET phivanchuyen = 70 WHERE id = 2;

UPDATE vanchuyen SET trangthai = 'ĐÃ GIAO' WHERE id = 1;
UPDATE vanchuyen SET trangthai = 'ĐANG GIAO' WHERE id = 2;

UPDATE vanchuyen
SET ngaygiaohang = '2025-04-25'
WHERE id_donhang = 1;

UPDATE vanchuyen
SET ngaygiaohang = '2025-04-26'
WHERE id_donhang = 2;

CREATE TABLE phivanchuyen (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_vanchuyen INT,
    phi DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_vanchuyen) REFERENCES vanchuyen(id) ON DELETE CASCADE
);
CREATE TABLE yeuthich (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_khachhang INT,
    id_giay INT,
    FOREIGN KEY (id_khachhang) REFERENCES khachhang(id) ON DELETE CASCADE,
    FOREIGN KEY (id_giay) REFERENCES giay(id) ON DELETE CASCADE
);
CREATE TABLE baocao (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    thongke VARCHAR(100) NOT NULL,
    giatri DECIMAL(10, 2) NOT NULL,
    thang INT NOT NULL,
    nam INT NOT NULL
);
-- nhật tổng tiền trong đơn hàng
DELIMITER $$

CREATE TRIGGER update_tongtien_donhang
AFTER INSERT ON chitietdonhang
FOR EACH ROW
BEGIN
    DECLARE total DECIMAL(10,2);

    -- Tính tổng tiền của đơn hàng
    SELECT SUM(soluong * gia) INTO total
    FROM chitietdonhang
    WHERE id_donhang = NEW.id_donhang;

    -- Cập nhật tổng tiền vào bảng donhang
    UPDATE donhang
    SET tongtien = total
    WHERE id = NEW.id_donhang;
END $$

DELIMITER ;
-- Xóa chi tiết đơn hàng khi đơn hàng bị xóa
DELIMITER $$

CREATE TRIGGER delete_chitietdonhang_after_donhang
AFTER DELETE ON donhang
FOR EACH ROW
BEGIN
    DELETE FROM chitietdonhang WHERE id_donhang = OLD.id;
END $$

DELIMITER ;

SHOW TRIGGERS;

INSERT INTO nguoidung (tendangnhap, email, matkhau, role) 
VALUES 
('kientrung', 'kien@example.com', '123456', 'USER'),
('admin', 'admin@example.com', 'admin123', 'ADMIN');

INSERT INTO khachhang (id_nguoidung, hoten, gioitinh, sodienthoai, diachi, ngaysinh) 
VALUES 
(1, 'Phạm Trung Kiên', 'nam', '0123456789', 'Hà Nội', '2003-05-09'),
(2, 'Nguyễn Thị Lan', 'nữ', '0987654321', 'TP.HCM', '1995-07-15');

INSERT INTO giay (ten, loai, gia, so_luong, size)
VALUES 
('Giày Nike Air Max', 'Thể thao', 2000000, 50, 42),
('Giày Adidas Ultraboost', 'Thể thao', 2500000, 30, 38);

INSERT INTO donhang (id_khachhang, ngaytao, tongtien) 
VALUES 
(1, '2025-04-23', 4000000),
(2, '2025-04-22', 2500000);

INSERT INTO donhang (id_khachhang, ngaydat, tongtien, trangthai) 
VALUES  
(1, '2025-04-23', 2000000, 'CHỜ XÁC NHẬN'), 
(2, '2025-04-22', 3500000, 'ĐANG GIAO'), 
(1, '2025-04-21', 1500000, 'ĐÃ GIAO'), 
(2, '2025-04-20', 500000, 'ĐÃ HỦY');

INSERT INTO chitietdonhang (id_donhang, id_giay, soluong, gia) 
VALUES  
(5, 1, 1, 2000000), 
(6, 2, 1, 2500000), 
(6, 1, 1, 1000000), 
(7, 1, 1, 1500000), 
(8, 2, 1, 500000);
INSERT INTO vanchuyen (id_donhang, diachigiaohang, phuongthuc) 
VALUES 
(5, '123 Đường A, Quận 1, TP.HCM', 'Giao hàng tiêu chuẩn'),
(6, '456 Đường B, Quận 3, TP.HCM', 'Giao nhanh'),
(7, '789 Đường C, Quận 5, TP.HCM', 'Giao hàng tiêu chuẩn'),
(8, '101 Đường D, Quận 10, TP.HCM', 'Giao nhanh');

INSERT INTO yeuthich (id_khachhang, id_giay) 
VALUES 
(1, 1),
(1, 2),
(2, 1);
INSERT INTO baocao (thongke, giatri, thang, nam)
VALUES 
('Doanh thu', 5000000.00, 4, 2025),
('Số lượng bán', 100, 4, 2025),
('Số lượng trả lại', 5, 4, 2025);
SELECT * FROM nguoidung;
SELECT * FROM khachhang;
SELECT * FROM donhang;
SELECT * FROM giay;
SELECT * FROM chitietdonhang;

SELECT * FROM phivanchuyen ;
SELECT * FROM vanchuyen ;
SELECT * FROM yeuthich ;
SELECT * FROM baocao ;
-- Thêm giỏ hàng
INSERT INTO giohang (id_khachhang, trangthai) 
VALUES (1, 'Đang mua'), (2, 'Đang mua');

-- Thêm sản phẩm vào giỏ hàng
INSERT INTO chitietgiohang (id_giay, id_giohang, soluong, gia) 
VALUES (1, 1, 2, 2000000), (2, 1, 1, 2500000), (1, 2, 3, 1000000);

--  Báo cáo doanh thu (tổng tiền) theo tháng hoặc năm:
INSERT INTO baocao (thongke, giatri, thang, nam)
SELECT 
    CONCAT('Doanh thu tháng ', DATE_FORMAT(MIN(ngaytao), '%m-%Y')) AS thongke,  -- Dùng MIN hoặc MAX để lấy một giá trị đại diện cho tháng/năm
    SUM(tongtien) AS giatri,
    MONTH(MIN(ngaytao)) AS thang,
    YEAR(MIN(ngaytao)) AS nam
FROM donhang
WHERE trangthai = 'ĐÃ GIAO'
GROUP BY MONTH(ngaytao), YEAR(ngaytao);

-- Báo cáo số lượng sản phẩm bán ra:
INSERT INTO baocao (thongke, giatri, thang, nam)
SELECT 
    g.ten AS thongke, 
    SUM(c.soluong) * g.gia AS giatri, 
    MONTH(d.ngaytao) AS thang, 
    YEAR(d.ngaytao) AS nam
FROM 
    chitietdonhang c 
JOIN 
    donhang d ON c.id_donhang = d.id 
JOIN 
    giay g ON c.id_giay = g.id 
WHERE 
    d.trangthai = 'ĐÃ GIAO' 
GROUP BY 
    g.ten, g.gia, MONTH(d.ngaytao), YEAR(d.ngaytao);


-- Báo cáo số lượng đơn hàng theo trạng thái:
INSERT INTO baocao (thongke, giatri, thang, nam)
SELECT d.trangthai AS thongke,
       COUNT(*) AS giatri,
       MONTH(d.ngaytao) AS thang,
       YEAR(d.ngaytao) AS nam
FROM donhang d
GROUP BY d.trangthai, MONTH(d.ngaytao), YEAR(d.ngaytao);

-- Báo cáo chi phí vận chuyển:
INSERT INTO baocao (thongke, giatri, thang, nam)
SELECT 
    CONCAT('Chi phí vận chuyển tháng ', MONTH(vc.ngaygiaohang)) AS thongke,
    SUM(vc.phivanchuyen) AS giatri,
    MONTH(vc.ngaygiaohang) AS thang,
    YEAR(vc.ngaygiaohang) AS nam
FROM vanchuyen vc
WHERE vc.trangthai = 'ĐÃ GIAO' AND vc.ngaygiaohang IS NOT NULL
GROUP BY MONTH(vc.ngaygiaohang), YEAR(vc.ngaygiaohang), vc.ngaygiaohang;









