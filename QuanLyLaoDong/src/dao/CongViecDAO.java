package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.DatabaseConnection;
import entity.CongTrinh;
import entity.CongViec;
import entity.NhanVien;

public class CongViecDAO {
	ArrayList<CongViec> dsCV;
	CongViec vc;
	public CongViecDAO() {
		dsCV = new ArrayList<CongViec>();
	}
	
	public ArrayList<CongViec> getDsCongViec() {
		try {
			Connection con= DatabaseConnection.getInstance().getConnection();
			String sql="Select CongViec.*, NhanVien.HoTen, CongTrinh.TenCongTrinh  from CongViec\r\n"
					+ "inner join NhanVien on CongViec.MaNV = NhanVien.MaNV\r\n"
					+ "inner join CongTrinh on CongTrinh.MaCT = CongViec.MaCT";
			Statement statement=con.createStatement();
			ResultSet rs= statement.executeQuery(sql);
			while(rs.next()) {
				int maCV= rs.getInt(1);
				NhanVien nv =new NhanVien(rs.getString(2),rs.getString(5));
				CongTrinh ct = new CongTrinh(rs.getString(3),rs.getString(6));
				int ngayCong = rs.getInt(4);
				CongViec cv = new CongViec(maCV,ct,nv,ngayCong);
				dsCV.add(cv);
			}
			System.out.println("get Danh sach CONG VIEC thanh cong");
//			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return dsCV;
	}
	public ArrayList<CongViec> getCVTheoMA(String mCT,String mNV){
		Connection con= DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		try {
			String sql="Select CongViec.*, NhanVien.HoTen, CongTrinh.TenCongTrinh  from CongViec\\r\\n\"\r\n"
					+ "					+ \"inner join NhanVien on CongViec.MaNV = NhanVien.MaNV\\r\\n\"\r\n"
					+ "					+ \"inner join CongTrinh on CongTrinh.MaCT = CongViec.MaCT where MaCT = ? and MaNV = ? ";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,mCT);
			stmt.setString(2,mNV);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				int maCV= rs.getInt(1);
				NhanVien nv =new NhanVien(rs.getString(2),rs.getString(5));
				CongTrinh ct = new CongTrinh(rs.getString(3),rs.getString(6));
				int ngayCong = rs.getInt(4);
				CongViec cv = new CongViec(maCV,ct,nv,ngayCong);
				dsCV.add(cv);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsCV;
	}
	public boolean delete(String mCV) {
		Connection con=DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("delete from CongViec where maCV=?");
			stmt.setString(1, mCV);
			n=stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(int ngay,CongViec ct) {
		// TODO Auto-generated method stub
			Connection con=DatabaseConnection.getInstance().getConnection();
			PreparedStatement stmt=null;
			int n=0;
			try {
				stmt=con.prepareStatement("update CongViec set SoNgayCongThamGia = ? where maCT=? and maNV = ? ");
				stmt.setInt(1,ngay);
				stmt.setString(2,ct.getCt().getMaCT());
				stmt.setString(3,ct.getNv().getMaNV());
				
				n=stmt.executeUpdate();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
	public boolean create(CongViec p) {
		Connection con=DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into CongViec (MaCT, MaNV, SoNgayCongThamGia) values(?,?,?)");
			int day = p.getNgayCong();
			stmt.setString(1,p.getCt().getMaCT());
			stmt.setString(2,p.getNv().getMaNV());
			stmt.setInt(3,day);
	    	
			n=stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
