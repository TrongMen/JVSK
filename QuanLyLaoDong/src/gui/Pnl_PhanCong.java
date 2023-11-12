package gui;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;

public class Pnl_PhanCong extends JPanel implements ActionListener, MouseListener {
	private JLabel lblHeader;
	private Box pnlNor, b, b1, b2, b3, b4,b5,b6;
	private JPanel pnlHeader;
	private JPanel pnlCen;
	private JPanel pnlA,pnlC;
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
	private JTable tblSach;
	private JPanel pnlMid;
	private JButton btnTim;

	private Component horizontal = Box.createHorizontalStrut(60);
	private Component horizontal1 = Box.createHorizontalStrut(60);
	private Component horizontal2 = Box.createHorizontalStrut(60);
	private JLabel lblTenCT;
	private JLabel lblTenNV;
	private JTextField txtTenCT;
	private JTextField txtTenNV;
	public Pnl_PhanCong() {
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
		lblHeader = new JLabel("BẢNG PHÂN CÔNG");
		lblHeader.setLabelFor(pnlHeader);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setForeground(new Color(255, 255, 255));
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHeader.setOpaque(false);
		pnlHeader.add(lblHeader);

		//Tạo fields và các button
		add(pnlCen = new JPanel(new BorderLayout()), BorderLayout.CENTER);

		pnlCen.add(b = Box.createVerticalBox(), BorderLayout.NORTH);

		b.add(pnlA = new JPanel());
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
		pnlB.add(btnSua = new JButton("Sửa"));
		btnSua.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnlB.add(horizontal);
		pnlB.add(btnThem = new JButton("Thêm"));
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnlB.add(horizontal1);
		pnlB.add(btnXoa = new JButton("Xóa"));
		btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnlB.add(horizontal2);
		pnlB.add(btnTim = new JButton("Tìm"));
		btnTim.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		//Tạo Table
		pnlCen.add(pnlMid = new JPanel(), BorderLayout.CENTER);
		String[] header = "STT;Mã Công Trình;Mã Nhân viên;Ngày công".split(";");
		tblModel = new DefaultTableModel(header, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(tblSach = new JTable(tblModel));
		tblSach.setRowHeight(25);
		scroll.setPreferredSize(new Dimension(1080,800));
		tblSach.addMouseListener(this);
		pnlMid.add(scroll);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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

	}
}
