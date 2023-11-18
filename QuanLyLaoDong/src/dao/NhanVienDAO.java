package dao;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import connectDB.DatabaseConnection;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVienDAO {
	private PreparedStatement ps = null;
	ArrayList<NhanVien> dsNV;
	private Connection con;
	private ResultSet rs;
	public NhanVienDAO() {
	// TODO Auto-generated constructor stub
		dsNV = new ArrayList<NhanVien>();
	}
	
	public ArrayList<NhanVien> getDsNhanVien() {
		try {
			Connection cnt = DatabaseConnection.getInstance().getConnection(); 
			String sql = "SELECT NhanVien.*, PhongBan.TenPB \r\n"
					+ "FROM NhanVien \r\n"
					+ "INNER JOIN PhongBan ON NhanVien.MaPB = PhongBan.MaPB ";
			Statement st = cnt.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString("MaNV"));
				nv.setHoTen(rs.getString("HoTen"));
				nv.setNgaySinh(rs.getDate("NgaySinh").toLocalDate());
				nv.setGioiTinh(rs.getBoolean("GioiTinh"));
				nv.setDiaChi(rs.getString("DiaChi"));
				nv.setChucVu(rs.getString("ChucVu"));
				nv.setMatKhau(rs.getString("MatKhau"));
				nv.setMaPB(new PhongBan(rs.getString("MaPB"),rs.getString("TenPB")));
				dsNV.add(nv);
			}
			System.out.println("get Danh sach nhan vien thanh cong");
			cnt.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsNV;
	}
	
	public NhanVien getNhanVienTheoMa() {
		NhanVien nv = new NhanVien();
		try {
			Connection cnt = DatabaseConnection.getInstance().getConnection(); 
			String sql = "SELECT NhanVien.*, PhongBan.TenPB\r\n"
					+ "FROM NhanVien \r\n"
					+ "INNER JOIN PhongBan ON NhanVien.MaPB = PhongBan.MaPB \r\n"
					+ "where MaNV = ? ";
			Statement st = cnt.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				
				nv.setMaNV(rs.getString("MaNV"));
				nv.setHoTen(rs.getString("HoTen"));
				nv.setNgaySinh(rs.getDate("NgaySinh").toLocalDate());
				nv.setGioiTinh(rs.getBoolean("GioiTinh"));
				nv.setDiaChi(rs.getString("DiaChi"));
				nv.setChucVu(rs.getString("ChucVu"));
				nv.setMatKhau(rs.getString("MatKhau"));
				nv.setMaPB(new PhongBan(rs.getString("MaPB"),rs.getString("TenPB")));
				dsNV.add(nv);
			}
			System.out.println("get 1 nhan vien thanh cong");
			cnt.close();
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	public int getSoNgayCongNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		int soNgay = 0;
		con = DatabaseConnection.getInstance().getConnection(); 
		String query = "SELECT TOP 1 SUM(CongViec.SoNgayCongThamGia) AS TongNgayCong\r\n"
				+ "FROM NhanVien\r\n"
				+ "INNER JOIN CongViec ON NhanVien.MaNV = CongViec.MaNV\r\n"
				+ "INNER JOIN CongTrinh ON CongViec.MaCT = CongTrinh.MaCT\r\n"
				+ "WHERE CongTrinh.NgayKhoiCong BETWEEN ? AND ? \r\n"
				+ "GROUP BY NhanVien.MaNV, NhanVien.HoTen\r\n"
				+ "ORDER BY TongNgayCong DESC; ";

		ps = con.prepareStatement(query);
		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);

		rs = ps.executeQuery();
		while (rs.next()) {
			soNgay = rs.getInt("TongNgayCong");
			return soNgay;
		}
		return 0;

	}
	
	public List<NhanVien> getNgayCongNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		dsNV = new ArrayList<NhanVien>();
		con = DatabaseConnection.getInstance().getConnection(); 
		try {
			String query = "SELECT TOP 1 NhanVien.MaNV, NhanVien.HoTen AS TongNgayCong\r\n"
					+ "FROM NhanVien\r\n"
					+ "INNER JOIN CongViec ON NhanVien.MaNV = CongViec.MaNV\r\n"
					+ "INNER JOIN CongTrinh ON CongViec.MaCT = CongTrinh.MaCT\r\n"
					+ "WHERE CongTrinh.NgayKhoiCong BETWEEN ? AND ? \r\n"
					+ "GROUP BY NhanVien.MaNV, NhanVien.HoTen\r\n"
					+ "ORDER BY TongNgayCong DESC;";

			ps = con.prepareStatement(query);
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);

			rs = ps.executeQuery();

			while (rs.next()) {
				String manv = rs.getString(1);
				String tennv = rs.getString(2);
				NhanVien nv = new NhanVien(manv,tennv);
				dsNV.add(nv);

			}
			return dsNV;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
