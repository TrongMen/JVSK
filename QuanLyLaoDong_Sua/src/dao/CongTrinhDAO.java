package dao;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.DatabaseConnection;
import entity.CongTrinh;


public class CongTrinhDAO {
	ArrayList<CongTrinh> dsCT;
	CongTrinh ct;
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
stmt=con.prepareStatement("update CongTrinh set TenCongTrinh=?, NgayCapPhep=?, NgayKhoiCong=?, ngayHoanThanhDuKien=?  , DiaDiem=? where maCT=? ");
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
}