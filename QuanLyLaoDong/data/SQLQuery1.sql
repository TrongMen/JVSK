drop database QuanLyLaoDong
create database QuanLyLaoDong
go
use QuanLyLaoDong
CREATE TABLE PhongBan (
    MaPB VARCHAR(5) PRIMARY KEY,
    TenPB NVARCHAR(50) NOT NULL
);

CREATE TABLE NhanVien (
    MaNV VARCHAR(5) PRIMARY KEY,
    HoTen NVARCHAR(50) NOT NULL,
    NgaySinh DATE  NOT NULL,
    GioiTinh BIT,
    DiaChi NVARCHAR(100),
    MaPB VARCHAR(5),
	ChucVu NVARCHAR(25),
	MatKhau VARCHAR(5),
    FOREIGN KEY (MaPB) REFERENCES PhongBan(MaPB)
);

CREATE TABLE CongTrinh (
    MaCT VARCHAR(5) PRIMARY KEY,
    TenCongTrinh NVARCHAR(50) NOT NULL,
    DiaDiem NVARCHAR(100),
    NgayCapPhep DATE,
    NgayKhoiCong DATE,
    NgayHoanThanhDuKien DATE
);

CREATE TABLE CongViec (
    MaCV INT IDENTITY(1,1) PRIMARY KEY,
    MaNV VARCHAR(5),
    MaCT VARCHAR(5),
    SoNgayCongThamGia INT NOT NULL,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaCT) REFERENCES CongTrinh(MaCT)
);

-- Chèn dữ liệu cho bảng PhongBan
INSERT INTO PhongBan (MaPB, TenPB)
VALUES 
    ('KT001', N'Phòng Kế Toán'),
    ('KD001', N'Phòng Kinh Doanh'),
    ('KT002', N'Phòng Kỹ Thuật'),
    ('TC001', N'Phòng Tổ Chức'),
    ('DA001', N'Phòng Dự Án'),
    ('CM001', N'Phòng Chuyên Môn'),
    ('PV001', N'Phòng Phục Vụ');

-- Thêm dữ liệu cho 10 nhân viên
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, DiaChi, MaPB, ChucVu)
VALUES 
    ('NV001', N'Diêm Công Bình', '2002-03-22', 0, N'Gò Vấp - TPHCM', 'KD001', N'Nhân viên'),
    ('NV002', N'Trần Thị Anh', '1995-06-15', 1, N'Bình Thạnh - TPHCM', 'KT002', N'Nhân viên'),
    ('NV003', N'Nguyễn Văn Nam', '1990-02-10', 0, N'Tân Phú - TPHCM', 'KT001', N'Nhân viên'),
    ('NV004', N'Lê Thị Hồng', '1998-08-05', 1, N'Quận 7 - TPHCM', 'KD001', N'Nhân viên'),
    ('NV005', N'Phạm Văn Hoàng', '1992-11-12', 0, N'Quận 1 - TPHCM', 'KD001', N'Nhân viên'),
    ('NV006', N'Đỗ Thị Ngọc', '1993-04-20', 1, N'Quận 5 - TPHCM', 'KT002', N'Nhân viên'),
    ('NV007', N'Lê Minh Tuấn', '1997-07-25', 0, N'Tân Bình - TPHCM', 'KT001', N'Nhân viên'),
    ('NV008', N'Nguyễn Thị Ngọc', '1989-09-10', 1, N'Quận 2 - TPHCM', 'KD001', N'Nhân viên'),
    ('NV009', N'Hoàng Văn Đức', '1994-03-15', 0, N'Gò Vấp - TPHCM', 'KD001', N'Nhân viên'),
    ('NV010', N'Trần Minh Tâm', '1991-10-20', 1, N'Tân Phú - TPHCM', 'KT002', N'Nhân viên');

-- Thêm dữ liệu cho 5 trưởng phòng
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, DiaChi, MaPB, ChucVu)
VALUES 
    ('TP001', N'Lê Thị Mai', '1980-01-05', 1, N'Quận 1 - TPHCM', 'KD001', N'Trưởng phòng'),
    ('TP002', N'Nguyễn Văn Lực', '1975-05-12', 0, N'Quận 7 - TPHCM', 'TC001', N'Trưởng phòng'),
    ('TP003', N'Phạm Thị Anh', '1982-08-18', 1, N'Tân Bình - TPHCM', 'KT001', N'Trưởng phòng'),
    ('TP004', N'Đỗ Văn Hiệp', '1978-11-25', 0, N'Quận 5 - TPHCM', 'KT002', N'Trưởng phòng'),
    ('TP005', N'Nguyễn Thị Hoa', '1985-03-30', 1, N'Quận 2 - TPHCM', 'PV001', N'Trưởng phòng');

-- Thêm dữ liệu cho 5 quản lý dự án
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, DiaChi, MaPB, ChucVu)
VALUES 
    ('DA001', N'Lê Văn Dũng', '1983-06-10', 0, N'Tân Phú - TPHCM', 'DA001', N'Quản lý dự án'),
    ('DA002', N'Phạm Thị Thu', '1979-09-15', 1, N'Quận 7 - TPHCM', 'DA001', N'Quản lý dự án'),
    ('DA003', N'Nguyễn Văn Hoàn', '1986-12-20', 0, N'Quận 2 - TPHCM', 'DA001', N'Quản lý dự án'),
    ('DA004', N'Lê Thị Hương', '1981-03-25', 1, N'Quận 5 - TPHCM', 'DA001', N'Quản lý dự án'),
    ('DA005', N'Hoàng Văn Thắng', '1976-07-30', 0, N'Tân Bình - TPHCM', 'DA001', N'Quản lý dự án');


-- Thêm dữ liệu cho 10 công trình
INSERT INTO CongTrinh (MaCT, TenCongTrinh, DiaDiem, NgayCapPhep, NgayKhoiCong, NgayHoanThanhDuKien)
VALUES 
    ('CT001', N'Công trình A', N'Quận 1 - TPHCM', '2022-01-01', '2022-02-01', '2022-05-01'),
    ('CT002', N'Công trình B', N'Quận 3 - TPHCM', '2022-02-15', '2022-03-01', '2022-06-01'),
    ('CT003', N'Công trình C', N'Tân Bình - TPHCM', '2022-03-20', '2022-04-01', '2022-07-01'),
    ('CT004', N'Công trình D', N'Tân Phú - TPHCM', '2022-04-10', '2022-05-01', '2022-08-01'),
    ('CT005', N'Công trình E', N'Quận 10 - TPHCM', '2022-05-05', '2022-06-01', '2022-09-01'),
    ('CT006', N'Công trình F', N'Quận 5 - TPHCM', '2022-06-12', '2022-07-01', '2022-10-01'),
    ('CT007', N'Công trình G', N'Quận 7 - TPHCM', '2022-07-25', '2022-08-01', '2022-11-01'),
    ('CT008', N'Công trình H', N'Quận 2 - TPHCM', '2022-08-10', '2022-09-01', '2022-12-01'),
    ('CT009', N'Công trình I', N'Bình Thạnh - TPHCM', '2022-09-15', '2022-10-01', '2023-01-01'),
    ('CT010', N'Công trình J', N'Gò Vấp - TPHCM', '2022-10-20', '2022-11-01', '2023-02-01');

-- Thêm dữ liệu cho khoảng 20 công việc tương ứng
INSERT INTO CongViec (MaCT, MaNV, SoNgayCongThamGia)
VALUES 
    ('CT001', 'NV001', 5),
    ('CT002', 'NV001', 3),
    ('CT002', 'NV002', 4),
    ('CT003', 'NV002', 6),
    ('CT004', 'NV003', 5),
    ('CT005', 'NV003', 7),
    ('CT006', 'NV004', 6),
    ('CT007', 'NV004', 8),
    ('CT008', 'NV005', 4),
    ('CT009', 'NV005', 6);

	select * from NhanVien