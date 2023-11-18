package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Pnl_QuanLyNhanVien<E> extends JPanel{

	public Pnl_QuanLyNhanVien() {
//		super("Quản lý dự án");
		setLayout(new BorderLayout(2,3));
		
	    //Center
		JPanel pnlCenter=new JPanel();
	    Box b=Box.createVerticalBox();
		Box b1;
		b.add(b1=Box.createHorizontalBox());
		JLabel lblMaNV;
		b1.add(lblMaNV=new JLabel("Mã nhân viên"));
	    b1.add(Box.createVerticalStrut(10));
	    JTextField txtMaNV;
		b1.add(txtMaNV=new JTextField());
		b.add(Box.createVerticalStrut(10));
		JLabel lblPhongBanNV;
		b1.add(lblPhongBanNV=new JLabel("Tên: "));
		b1.add(Box.createVerticalStrut(10));
		JTextField txtTenNV;
		b1.add(txtTenNV=new JTextField());
		
		Box b2;
		b.add(b2=Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
	    JLabel lblDiaChi;
		b2.add(lblDiaChi=new JLabel("Địa chỉ: "));
	    JTextField txtDiaChi;
		b2.add(txtDiaChi=new JTextField());
	    JLabel lblPhongBan;
		b2.add(lblPhongBan=new JLabel("Phòng ban: "));
	    String phongban[]= {"Phòng kế toán","Phòng kinh doanh","Phòng kỹ thuật","Phòng tổ chức","Phòng dự án","Phòng chuyên môn","Phòng phục vụ"};
		JComboBox cb=new JComboBox(phongban);
		cb.setBounds(100,50,150,20);
		b2.add(cb);
		    
		Box b3;
		b.add(b3= Box.createHorizontalBox());
		JLabel lblNgaySinh;
		b3.add(lblNgaySinh=new JLabel("Ngày sinh:"));
		JTextField txtNgaySinh;
		b3.add(txtNgaySinh= new JTextField());
		JLabel lblPhai;
		b3.add(lblPhai=new JLabel("Phái:"));
		JRadioButton radNu;
		b3.add(radNu=new JRadioButton("Nữ"));
		b.add(Box.createVerticalStrut(10));
			
		lblDiaChi.setPreferredSize(lblMaNV.getPreferredSize());
		lblPhai.setPreferredSize(lblMaNV.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
		
		Box b5;
		b.add(b5= Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		String[] headers= "MaNV;Ten;Dia Chi;Ngay Sinh;Phai;Phong Ban".split(";");
		DefaultTableModel tableModel = new DefaultTableModel(headers,0);
		JScrollPane scroll= new JScrollPane();
		JTable table;
		scroll.setViewportView(table= new JTable(tableModel));
		table.setRowHeight(25);
		b5.add(scroll);
		
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(split, BorderLayout.SOUTH);
		JPanel pnLeft, pnRight;
		split.add(pnLeft=new JPanel());
		split.add(pnRight=new JPanel());
		
		pnLeft.add(new JLabel("Nhập mã số cần tìm: "));
		JTextField txtTim;
		pnLeft.add(txtTim=new JTextField(10));
		JButton btnTim;
		pnLeft.add(btnTim=new JButton("Tìm"));
		JButton btnThem;
		pnRight.add(btnThem=new JButton("Thêm"));
		JButton btnXoaTrang;
		pnRight.add(btnXoaTrang=new JButton("Xóa trắng"));
		JButton btnXoa;
		pnRight.add(btnXoa=new JButton("Xóa"));
		JButton btnLuu;
		pnRight.add(btnLuu=new JButton("Lưu"));
		
		pnlCenter.add(b);
		add(pnlCenter, BorderLayout.CENTER);
			
		
	    
		setVisible(true);
		setSize(1920,1080);
		//setResizable(false);
	}
public static void main(String[] args) {
	new Pnl_QuanLyNhanVien();
}
}
