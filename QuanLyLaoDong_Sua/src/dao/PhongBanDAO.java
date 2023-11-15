package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.DatabaseConnection;
import entity.PhongBan;

public class PhongBanDAO {
	ArrayList<PhongBan> dsPB;
	PhongBan pb;
	public PhongBanDAO() {
		dsPB=new ArrayList<PhongBan>();
	}
	public ArrayList<PhongBan> docTuBang(){
		try {
			Connection con= DatabaseConnection.getInstance().getConnection();
			String sql="Select * from PhongBan";
			Statement statement=con.createStatement();
			ResultSet rs= statement.executeQuery(sql);
			while(rs.next()) {
				String maPB= rs.getString(1);
				String tenPB=rs.getString(2);
				PhongBan pb= new PhongBan(maPB,tenPB);
				dsPB.add(pb);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsPB;
	}
	public ArrayList<PhongBan> getpbtheomaPB(String mPB){
		Connection con= DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		try {
			String sql="Select * from PhongBan where maPB=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,mPB);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				String maPB= rs.getString(1);
				String tenPB=rs.getString(2);
				PhongBan pb= new PhongBan(maPB,tenPB);
				dsPB.add(pb);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsPB;
	}
	
	public boolean create(PhongBan p) {
		Connection con= DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into PhongBan values( ?,? )");
			stmt.setString(1,p.getMaPB());
			stmt.setString(2,p.getTenPB());
			n=stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean update(PhongBan p) {
		Connection con= DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update PhongBan set tenPB=?  where maPB=? ");
			stmt.setString(1,p.getTenPB());
			n=stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean delete(String mPB) {
		Connection con= DatabaseConnection.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("delete from PhongBan where maPB=?");
			stmt.setString(1, mPB);
			n=stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
}
