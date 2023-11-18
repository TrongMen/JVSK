package dao;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.DatabaseConnection;
import entity.CongTrinh;
import entity.NhanVien;

public class CongTrinhDAO {
	ArrayList<CongTrinh> dsCT;
	CongTrinh ct;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public CongTrinhDAO() {
		dsCT=new ArrayList<CongTrinh>();
	}
	public ArrayList<CongTrinh> docTuBang(){
		try {
			Connection con= DatabaseConnection.getInstance().getConnection();
			String sql="Select * from CongTrinh";
			Statement statement=con.createStatement();
			ResultSet rs= statement.executeQuery(sql);
			while(rs.next()) {
				String maCT= rs.getString(1);
				String tenCT=rs.getString(2);
				String diadiem=rs.getString(3);
				LocalDate ngayCP=rs.getDate(4).toLocalDate();
				LocalDate ngayKC=rs.getDate(5).toLocalDate();
				LocalDate ngayKT=rs.getDate(6).toLocalDate();
				CongTrinh ct= new CongTrinh(maCT,tenCT,ngayCP,ngayKC,ngayKT,diadiem);
				dsCT.add(ct);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsCT;
	}
	public ArrayList<CongTrinh> getCTtheoMaCT(String mCT){
		Connection con= DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		try {
			String sql="Select * from CongTrinh where maCT=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,mCT);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				String maCT=rs.getString(1);
				System.out.println(maCT);
				String tenCT=rs.getString(2);
				String diadiem=rs.getString(3);
				LocalDate ngayCP=rs.getDate(4).toLocalDate();
				LocalDate ngayKC=rs.getDate(5).toLocalDate();
				LocalDate ngayKT=rs.getDate(6).toLocalDate();
				CongTrinh ct= new CongTrinh(maCT,tenCT,ngayCP,ngayKC,ngayKT,diadiem);
				dsCT.add(ct);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsCT;
	}
	
	public boolean create(CongTrinh p) {
		Connection con=DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into CongTrinh values(?,?,?,?,?,?)");
			stmt.setString(1,p.getMaCT());
			stmt.setString(2,p.getTenCT());
			stmt.setString(3,p.getDiaDiem());
			int day = p.getNgayCP().getDayOfMonth();
	    	int month = p.getNgayCP().getMonthValue();
	    	int year = p.getNgayCP().getYear();
			stmt.setString(4,year + "-" + month + "-" + day);
			stmt.setString(5,p.getNgayKC().getDayOfMonth() + "-" + p.getNgayKC().getMonthValue() + "-" + p.getNgayKC().getYear() );
			stmt.setString(6,p.getNgayHTDK().getDayOfMonth() + "-" + p.getNgayHTDK().getMonthValue() + "-" + p.getNgayHTDK().getYear());
			n=stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean update(CongTrinh p) {
		Connection con=DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update CongTrinh set tenCT=?, diaDiem=?, ngayCP=?, ngayKC=?, ngayKT=? where maCT=? ");
			stmt.setString(1,p.getTenCT());
			stmt.setString(2,p.getDiaDiem());
			stmt.setString(3,p.getNgayCP().getDayOfMonth() + "-" + p.getNgayCP().getMonthValue() + "-" + p.getNgayCP().getYear() );
			stmt.setString(4,p.getNgayKC().getDayOfMonth() + "-" + p.getNgayKC().getMonthValue() + "-" + p.getNgayKC().getYear() );
			stmt.setString(5,p.getNgayHTDK().getDayOfMonth() + "-" + p.getNgayHTDK().getMonthValue() + "-" + p.getNgayHTDK().getYear());
			n=stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delete(String mCT) {
		Connection con=DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("delete from CongTrinh where maCT=?");
			stmt.setString(1, mCT);
			n=stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public int getSoNgayCongNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		int soNgay = 0;
		con = DatabaseConnection.getInstance().getConnection(); 
		String query = "SELECT TOP 1 CongTrinh.MaCT, CongTrinh.TenCongTrinh, SUM(CongViec.SoNgayCongThamGia) AS TongNgayCong\r\n"
				+ "FROM CongTrinh\r\n"
				+ "INNER JOIN CongViec ON CongTrinh.MaCT = CongViec.MaCT\r\n"
				+ "WHERE CongTrinh.NgayKhoiCong BETWEEN ? AND ?\r\n"
				+ "GROUP BY CongTrinh.MaCT, CongTrinh.TenCongTrinh\r\n"
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
			soNgay = rs.getInt("TongNgayCong");
			return soNgay;
		}
		return 0;

	}
	
	public List<CongTrinh> getCongTrinhNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		dsCT = new ArrayList<CongTrinh>();
		con = DatabaseConnection.getInstance().getConnection(); 
		try {
			String query = "SELECT TOP 1 CongTrinh.MaCT, CongTrinh.TenCongTrinh, SUM(CongViec.SoNgayCongThamGia) AS TongNgayCong\r\n"
					+ "FROM CongTrinh\r\n"
					+ "INNER JOIN CongViec ON CongTrinh.MaCT = CongViec.MaCT\r\n"
					+ "WHERE CongTrinh.NgayKhoiCong BETWEEN ? AND ? \r\n"
					+ "GROUP BY CongTrinh.MaCT, CongTrinh.TenCongTrinh\r\n"
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
				CongTrinh nv = new CongTrinh(manv,tennv);
				dsCT.add(nv);

			}
			return dsCT;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

	
