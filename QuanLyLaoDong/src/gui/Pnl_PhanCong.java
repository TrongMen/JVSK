package gui;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connectDB.DatabaseConnection;
import dao.CongTrinhDAO;
import dao.CongViecDAO;
import entity.CongTrinh;
import entity.CongViec;
import entity.NhanVien;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;

public class Pnl_PhanCong extends JPanel implements ActionListener, MouseListener {
	private JLabel lblHeader;
	private Box pnlNor, b, b1, b2, b3, b4, b5, b6;
	private JPanel pnlHeader;
	private JPanel pnlCen;
	private JPanel pnlA, pnlC;
	private JLabel lblMaCT;
	private JTextField txtMaCT;
	private JLabel lblMaNV;
	private JTextField txtMaNV;
	private JLabel lblNgay;
	private JTextField txtNgay;
	private JPanel pnlTop1;
	private JPanel pnlB;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JTable table;
	private DefaultTableModel tblModel;
	private JTable tblCV;
	private JPanel pnlMid;
	private JButton btnTim;
	private Component h = Box.createHorizontalStrut(60);
	private Component horizontal = Box.createHorizontalStrut(60);
	private Component horizontal1 = Box.createHorizontalStrut(60);
	private Component horizontal2 = Box.createHorizontalStrut(60);
	private JLabel lblTenCT;
	private JLabel lblTenNV;
	private JTextField txtTenCT;
	private JTextField txtTenNV;
	private JButton btnLuu;
	CongViecDAO dsCV = new CongViecDAO();
	private JLabel lblMacv;
	private JTextField txtMacv;

	public Pnl_PhanCong() {
		// set thuôc tính cho Panel
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

		// Tạo header cho panel
		pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(0, 153, 51));
		pnlHeader.setPreferredSize(new Dimension(getWidth(), 100));
		pnlNor.add(pnlHeader);
		pnlHeader.setLayout(new BorderLayout(0, 0));
		lblHeader = new JLabel("BẢNG PHÂN CÔNG");
		lblHeader.setLabelFor(pnlHeader);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setForeground(new Color(255, 255, 255));
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHeader.setOpaque(false);
		pnlHeader.add(lblHeader);

		// Tạo fields và các button
		add(pnlCen = new JPanel(new BorderLayout()), BorderLayout.CENTER);

		pnlCen.add(b = Box.createVerticalBox(), BorderLayout.NORTH);
		
		b.add(pnlA = new JPanel());
		pnlA.add(b6 = Box.createHorizontalBox());
		b6.add(lblMacv = new JLabel("Mã Công Việc:"));
		lblMacv.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b6.add(txtMacv = new JTextField(20));
		b6.add(Box.createHorizontalStrut(20));
		pnlA.add(b1 = Box.createHorizontalBox());
		b1.add(lblMaCT = new JLabel("Mã công trình:"));
		lblMaCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b1.add(txtMaCT = new JTextField(20));
		b1.add(Box.createHorizontalStrut(20));
		pnlA.add(b2 = Box.createHorizontalBox());
		b2.add(lblMaNV = new JLabel("Mã nhân viên:"));
		lblMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b2.add(txtMaNV = new JTextField(20));
		b2.add(Box.createHorizontalStrut(20));
		pnlA.add(b3 = Box.createHorizontalBox());
		b3.add(lblNgay = new JLabel("Số ngày công:"));
		lblNgay.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b3.add(txtNgay = new JTextField(20));

		b.add(pnlC = new JPanel());
		pnlC.add(b4 = Box.createHorizontalBox());
		b4.add(lblTenCT = new JLabel("Tên công trình:"));
		lblTenCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b4.add(txtTenCT = new JTextField(40));
		txtTenCT.setEditable(false);
		b4.add(Box.createHorizontalStrut(20));
		pnlC.add(b5 = Box.createHorizontalBox());
		b5.add(lblTenNV = new JLabel("Tên Nhân Viên:"));
		lblTenNV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b5.add(txtTenNV = new JTextField(40));
		txtTenNV.setEditable(false);

		b.add(pnlB = new JPanel());
		pnlB.add(btnLuu = new JButton("Lưu"));
		btnLuu.addActionListener(this);
		btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnlB.add(h);
		pnlB.add(btnSua = new JButton("Sửa"));
		btnSua.addActionListener(this);
		btnSua.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnlB.add(horizontal);
		pnlB.add(btnThem = new JButton("Thêm"));
		btnThem.addActionListener(this);
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnlB.add(horizontal1);
		pnlB.add(btnXoa = new JButton("Xóa"));
		btnXoa.addActionListener(this);
		btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnlB.add(horizontal2);
		pnlB.add(btnTim = new JButton("Tìm"));
		btnTim.addActionListener(this);
		btnTim.setFont(new Font("Segoe UI", Font.BOLD, 12));

		// Tạo Table
		pnlCen.add(pnlMid = new JPanel(), BorderLayout.CENTER);
		String[] header = "STT;Mã Công Trình;Tên Công Trình ;Mã Nhân viên;Tên Nhân Viên;Ngày công".split(";");
		tblModel = new DefaultTableModel(header, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(tblCV = new JTable(tblModel));
		tblCV.setRowHeight(25);
		scroll.setPreferredSize(new Dimension(1080, 800));
		tblCV.addMouseListener(this);
		pnlMid.add(scroll);

		// Connect với database
		DatabaseConnection.getInstance().getConnection();
		updateTableData();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void updateTableData() {
		CongViecDAO ds = new CongViecDAO();
		ArrayList<CongViec> list = ds.getDsCongViec();
		for (CongViec c : list) {
			int i = 1;
			String[] rowData = { c.getMaCV() + "", c.getCt().getMaCT(), c.getCt().getTenCT(), c.getNv().getMaNV(),
					c.getNv().getHoTen(), c.getNgayCong() + "" };
			tblModel.addRow(rowData);
		}
		tblCV.setModel(tblModel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tblCV.getSelectedRow();
		txtMacv.setText(tblCV.getValueAt(row, 0).toString());
		txtMaCT.setText(tblCV.getValueAt(row, 1).toString());
		txtTenCT.setText(tblCV.getValueAt(row, 2).toString());
		txtMaNV.setText(tblCV.getValueAt(row, 3).toString());
		txtTenNV.setText(tblCV.getValueAt(row, 4).toString());
		txtNgay.setText(tblCV.getValueAt(row, 5).toString());
	}
	
	private CongViec  reverSPFromTextFile() {
		NhanVien maNV= new NhanVien(txtMaNV.getText().toString()) ;
		CongTrinh maCT= new CongTrinh( txtMaCT.getText().toString());
		int macv = Integer.parseInt(txtMacv.getText());
		int ngay = Integer.parseInt(txtNgay.getText());
		return new CongViec(macv,maCT,maNV,ngay);
	}
	private void xoaRongTextfields() {
		txtMaCT.setText("");
		txtMaNV.setText("");
		txtTenCT.setText("");
		txtTenNV.setText("");
		txtNgay.setText("");
		txtMaCT.requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(btnThem)) {
			NhanVien maNV= new NhanVien(txtMaNV.getText().toString()) ;
			CongTrinh maCT= new CongTrinh( txtMaCT.getText().toString());
			int macv = Integer.parseInt(txtMacv.getText());
			int ngay = Integer.parseInt(txtNgay.getText());
			CongViec cv = new CongViec(macv,maCT,maNV,ngay);
			dsCV.create(cv);
			tblModel.getDataVector().removeAllElements();
			updateTableData();
		}
		else if(o.equals(btnXoa)) {
			JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa");
			int row=tblCV.getSelectedRow();
			if(row>=0) {
				String maCV=(String) tblCV.getValueAt(row,0);
				if(dsCV.delete(maCV)) {
					tblModel.removeRow(row);
					xoaRongTextfields();
				}
				tblModel.getDataVector().removeAllElements();
				updateTableData();
			}
		}
		else if(o.equals(btnLuu)) {
			int row=tblCV.getSelectedRow();
			int ngay = Integer.parseInt(txtNgay.getText());
			if(row>=0) {
				CongViec ct=reverSPFromTextFile();
				if(dsCV.update(ngay,ct)) {
					tblModel.getDataVector().removeAllElements();
					updateTableData();
				}
			}
		}
		else if(o.equals(btnSua)) {
			txtMaCT.setEditable(false);
			txtMaNV.setEditable(false);
		}
		else if(o.equals(btnTim)) {
			CongViecDAO ds= new CongViecDAO();
			List<CongViec> list= ds.getCVTheoMA(txtMaCT.getText(),txtMaNV.getText());
			
			if(txtMaNV.getText().equals("")&&txtMaCT.getText().equals(""))
					JOptionPane.showMessageDialog(this,"Vui lòng nhập dữ liệu cần tìm!");
			else if(list.size()==0)
						JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			else {
					tblModel.getDataVector().removeAllElements();
					for (CongViec c : list) {
						int i = 1;
						String[] rowData = { c.getMaCV() + "", c.getCt().getMaCT(), c.getCt().getTenCT(), c.getNv().getMaNV(),
								c.getNv().getHoTen(), c.getNgayCong() + "" };
						tblModel.addRow(rowData);
					}
					tblCV.setModel(tblModel);
				}
			}
		}
}
