package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import dao.CongTrinhDAO;
import dao.NhanVienDAO;
import entity.CongTrinh;
import entity.CongViec;
import entity.NhanVien;
import entity.PhongBan;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pnl_BaoCaoCongTrinh extends JPanel implements ActionListener{
	NhanVienDAO dsNV = new NhanVienDAO();
	private Box pnlNor;
	private JPanel pnlHeader;
	private JLabel lblHeader;
	private JPanel pnlCen;
	private JTextField txtNvNhat;
	private JTextField txtTongNc;
	private JTextField textField;
	private JTextField textField_1;
	private JDateChooser dateChooserFrom;
	private ArrayList<NhanVien> dsSP;
	private JButton btnLoc;
	private JDateChooser dateChooserTo;
	private CongTrinhDAO dsCT = new CongTrinhDAO();

	public Pnl_BaoCaoCongTrinh() {
		// TODO Auto-generated constructor stub
		//set thuôc tính cho Panel
		setBackground(Color.WHITE);
		setMinimumSize(new java.awt.Dimension(0, 0));
		setName(""); // NOI18N
		setPreferredSize(new java.awt.Dimension(1920, 1080));
		setLayout(new java.awt.BorderLayout());

		add(pnlNor = Box.createVerticalBox(), BorderLayout.NORTH);
		pnlNor.setForeground(new Color(0, 153, 0));
		pnlNor.setBackground(new Color(51, 153, 51));
		Component verticalStrut = Box.createVerticalStrut(60);
		pnlNor.add(verticalStrut);
		
		//Tạo header cho panel
		pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(0, 153, 51));
		pnlHeader.setPreferredSize(new Dimension(getWidth(), 100));
		pnlNor.add(pnlHeader);
		pnlHeader.setLayout(new BorderLayout(0, 0));
		lblHeader = new JLabel("BẢNG BÁO CÁO CÔNG TRÌNH");
		lblHeader.setLabelFor(pnlHeader);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setForeground(new Color(255, 255, 255));
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHeader.setOpaque(false);
		pnlHeader.add(lblHeader);
		
		//center
		add(pnlCen = new JPanel(), BorderLayout.CENTER);
		pnlCen.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhân Viên Có Ngày Công Nhiều Nhất");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(59, 148, 346, 22);
		pnlCen.add(lblNewLabel);
		
		JLabel lblTongNgayCong = new JLabel("Số ngày công:");
		lblTongNgayCong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongNgayCong.setBounds(59, 231, 131, 22);
		pnlCen.add(lblTongNgayCong);
		
		JLabel lblTenNVNhat = new JLabel("Tên:");
		lblTenNVNhat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenNVNhat.setBounds(59, 189, 56, 22);
		pnlCen.add(lblTenNVNhat);
		
		txtNvNhat = new JTextField();
		txtNvNhat.setEditable(false);
		txtNvNhat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNvNhat.setBounds(125, 181, 325, 32);
		pnlCen.add(txtNvNhat);
		txtNvNhat.setColumns(10);
		
		txtTongNc = new JTextField();
		txtTongNc.setEditable(false);
		txtTongNc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTongNc.setColumns(10);
		txtTongNc.setBounds(207, 226, 243, 32);
		pnlCen.add(txtTongNc);
		
		JLabel lblCngTrnhC = new JLabel("Công trình Có Ngày Công Nhiều Nhất");
		lblCngTrnhC.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCngTrnhC.setBounds(59, 302, 346, 22);
		pnlCen.add(lblCngTrnhC);
		
		JLabel lblTenNVNhat_1 = new JLabel("Tên:");
		lblTenNVNhat_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenNVNhat_1.setBounds(59, 353, 56, 22);
		pnlCen.add(lblTenNVNhat_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(125, 345, 325, 32);
		pnlCen.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(207, 390, 243, 32);
		pnlCen.add(textField_1);
		
		JLabel lblTongNgayCong_1 = new JLabel("Số ngày công:");
		lblTongNgayCong_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongNgayCong_1.setBounds(59, 395, 131, 22);
		pnlCen.add(lblTongNgayCong_1);
		
		dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBounds(59, 84, 164, 33);
		pnlCen.add(dateChooserFrom);
		
		dateChooserTo = new JDateChooser();
		dateChooserTo.setBounds(286, 84, 164, 33);
		pnlCen.add(dateChooserTo);
		
		JLabel lblTu = new JLabel("Từ");
		lblTu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTu.setBounds(59, 47, 56, 22);
		pnlCen.add(lblTu);
		
		JLabel lbln = new JLabel("Đến");
		lbln.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbln.setBounds(286, 47, 56, 22);
		pnlCen.add(lbln);
		
		btnLoc = new JButton("Lọc");
		btnLoc.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoc.setBounds(473, 84, 111, 33);
		btnLoc.addActionListener(this);
		pnlCen.add(btnLoc);
		
	}
	
	public LocalDate getNgayFromJDateChooser(JDateChooser ngay) {
		if (ngay.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày");
			return null;
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String dayKT = sdf2.format(ngay.getDate());
		String dateKT[] = dayKT.split("-");
		int namKT = Integer.parseInt(dateKT[0]);
		int thangKT = Integer.parseInt(dateKT[1]);
		int ngayKT = Integer.parseInt(dateKT[2]);
		LocalDate lcDateKT = LocalDate.of(namKT, thangKT, ngayKT);
		return lcDateKT;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnLoc)) {
			List<NhanVien>  list= dsNV.getNgayCongNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFrom), getNgayFromJDateChooser(dateChooserTo));
			for(NhanVien n : list) {
				txtNvNhat.setText(n.getHoTen());
				try {
					txtTongNc.setText((dsNV.getSoNgayCongNhieuNhat(getNgayFromJDateChooser(dateChooserFrom), getNgayFromJDateChooser(dateChooserTo)))+"");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			List<CongTrinh>  l= dsCT.getCongTrinhNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFrom), getNgayFromJDateChooser(dateChooserTo));
			for(CongTrinh n : l) {
				textField.setText(n.getTenCT());
				try {
					textField_1.setText((dsCT.getSoNgayCongNhieuNhat(getNgayFromJDateChooser(dateChooserFrom), getNgayFromJDateChooser(dateChooserTo)))+"");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
