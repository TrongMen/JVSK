package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import connectDB.DatabaseConnection;
import dao.CongTrinhDAO;
import entity.CongTrinh;

public class Pn_QLCT extends JPanel implements ActionListener,MouseListener {

	private Box pnNor;
	private JPanel pnHead;
	private JLabel lbHead;
	private JPanel pnCen;
	private Box b;
	private JPanel pnA;
	private Box b1;
	private JLabel lbMaCT;
	private JTextField txtMaCT;
	private Box b2;
	private Box b3;
	private JLabel lbNgay;
	private JTextField txNgay;
	private JLabel lbTenCT;
	private JPanel pnCen1;
	private JLabel lbNCP;
	private Box b4;
	private JTextField txtNCP;
	private Box b5;
	private JLabel lbNKC;
	private JTextField txtNKC;
	private JLabel lbNKT;
	private JTextField txtNKT;
	private Box b6;
	private JPanel pnB;
	private JButton btnSua;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnTim;
	private Component horizontal = Box.createHorizontalStrut(60);
	private Component horizontal1 = Box.createHorizontalStrut(60);
	private Component horizontal2 = Box.createHorizontalStrut(60);
	private JPanel pnlMid;
	private DefaultTableModel tblModel;
	private JTable table;
	private JLabel lbDD;
	private JTextField txDD;
	private JTextField txTenCT;
	CongTrinhDAO dsct=new CongTrinhDAO();

	public Pn_QLCT(){
		super();
		
		//Panel
		setBackground(Color.WHITE);
		setMinimumSize(new java.awt.Dimension(0, 0));
		setName(""); // NOI18N
		setPreferredSize(new java.awt.Dimension(1920, 1080));
		setLayout(new java.awt.BorderLayout());
		
		//North
		add(pnNor = Box.createVerticalBox(), BorderLayout.NORTH);
		pnNor.setForeground(new Color(0, 153, 0));
		pnNor.setBackground(new Color(51, 153, 51));
		Component verticalStrut = Box.createVerticalStrut(60);
		pnNor.add(verticalStrut);
		
		//Head
		pnHead = new JPanel();
		pnHead.setBackground(new Color(0, 153, 51));
		pnHead.setPreferredSize(new Dimension(getWidth(), 100));
		pnNor.add(pnHead);
		pnHead.setLayout(new BorderLayout(0, 0));
		lbHead = new JLabel("QUẢN LÝ CÔNG TRÌNH");
		lbHead.setLabelFor(pnHead);
		lbHead.setHorizontalAlignment(SwingConstants.CENTER);
		lbHead.setForeground(new Color(255, 255, 255));
		lbHead.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbHead.setOpaque(false);
		pnHead.add(lbHead);
		
		//label và textfield
		add(pnCen = new JPanel(new BorderLayout()), BorderLayout.CENTER);

		pnCen.add(b = Box.createVerticalBox(), BorderLayout.NORTH);

		b.add(pnA = new JPanel());
		pnA.add(b1 = Box.createHorizontalBox());
		b1.add(lbMaCT = new JLabel("Mã công trình:"));
		lbMaCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b1.add(txtMaCT = new JTextField(20));
		b1.add(Box.createHorizontalStrut(20));
		pnA.add(b2 = Box.createHorizontalBox());
		b2.add(lbTenCT = new JLabel("Tên công trình:"));
		lbTenCT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b2.add(txTenCT = new JTextField(20));
		b2.add(Box.createHorizontalStrut(20));
		pnA.add(b3 = Box.createHorizontalBox());
		b3.add(lbDD = new JLabel("Địa điểm:"));
		lbDD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b3.add(txDD = new JTextField(20));
		
		b.add(pnCen1 = new JPanel());
		pnCen1.add(b4 = Box.createHorizontalBox());
		b4.add(lbNCP = new JLabel("Ngày cấp phép:"));
		lbNCP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b4.add(txtNCP = new JTextField(30));
		b4.add(Box.createHorizontalStrut(20));
		pnCen1.add(b5 = Box.createHorizontalBox());
		b5.add(lbNKC = new JLabel("Ngày khởi công:"));
		lbNKC.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b5.add(txtNKC = new JTextField(30));
		b5.add(Box.createHorizontalStrut(20));
		pnCen1.add(b6 = Box.createHorizontalBox());
		b6.add(lbNKT = new JLabel("Ngày kết thúc:"));
		lbNKT.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b6.add(txtNKT = new JTextField(30));
	
		//Button
		b.add(pnB = new JPanel());
		pnB.add(btnSua = new JButton("Sửa"));
		btnSua.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnB.add(horizontal);
		pnB.add(btnThem = new JButton("Thêm"));
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnB.add(horizontal1);
		pnB.add(btnXoa = new JButton("Xóa"));
		btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 12));
		pnB.add(horizontal2);
		pnB.add(btnTim = new JButton("Tìm"));
		btnTim.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		//Table
		pnCen.add(pnlMid = new JPanel(), BorderLayout.CENTER);
		String[] header = "Mã Công Trình;Tên công trình;Địa điểm;Ngày cấp phép;Ngày khởi công;Ngày kết thúc".split(";");
		tblModel = new DefaultTableModel(header, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tblModel));
		table.setRowHeight(25);
		scroll.setPreferredSize(new Dimension(1080,800));
		table.addMouseListener(this);
		pnlMid.add(scroll);
		
		
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTim.addActionListener(this);
		
		DatabaseConnection.getInstance().connect();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row= table.getSelectedRow();
		txtMaCT.setText(table.getValueAt(row, 0).toString());
		txTenCT.setText(table.getValueAt(row, 1).toString());
		txDD.setText(table.getValueAt(row, 2).toString());
		txtNCP.setText(table.getValueAt(row, 3).toString());
		txtNKC.setText(table.getValueAt(row, 0).toString());
		txtNKT.setText(table.getValueAt(row, 0).toString());
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
		Object o=e.getSource();
		if(o.equals(btnThem)) {
			xoaRongTextfields();
		}
		else if(o.equals(btnXoa)) {
			int row=table.getSelectedRow();
			if(row>=0) {
				String maCT=(String) table.getValueAt(row,0);
				if(dsct.delete(maCT)) {
					tblModel.removeRow(row);
					xoaRongTextfields();
				}
			}
		}
		else if(o.equals(btnSua)) {
			int row=table.getSelectedRow();
			if(row>=0) {
				CongTrinh ct=reverSPFromTextFile();
				if(dsct.update(ct)) {
					table.setValueAt(txTenCT.getText(), row,1);
					table.setValueAt(txDD.getText(), row,2);
					table.setValueAt(txtNCP.getText(), row,3);
					table.setValueAt(txtNKC.getText(), row,4);
					table.setValueAt(txtNKT.getText(), row,5);
				}
			}
		}
		else if(o.equals(btnTim)) {
			CongTrinhDAO ds= new CongTrinhDAO();
			List<CongTrinh> list= ds.getCTtheoMaCT(txtMaCT.getText());
			
			if(txtMaCT.getText().equals(""))
				JOptionPane.showMessageDialog(this,"Vui lòng nhập mã công trình cần tìm!");
			else if(list.size()==0)
					JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			else {
				tblModel.getDataVector().removeAllElements();
				for(CongTrinh s:list) {
					String[] rowData= {s.getMaCT(),s.getTenCT(),s.getNgayCP()+"",s.getNgayKC()+"",s.getNgayHTDK()+"",s.getDiaDiem()};
					tblModel.addRow(rowData);
				}
				table.setModel(tblModel);
			}
		}	
	}
	
	private void xoaRongTextfields() {
		txtMaCT.setText("");
		txTenCT.setText("");
		txDD.setText("");
		txtNCP.setText("");
		txtNKC.setText("");
		txtNKT.setText("");
		txtMaCT.requestFocus();
	}
	
	private CongTrinh  reverSPFromTextFile() {
		String maCT=txtMaCT.getText().toString();
		String tenCT=txTenCT.getText().toString();
		LocalDate NCP=LocalDate.parse(txtNCP.getText().toString());
		LocalDate NKC=LocalDate.parse(txtNKC.getText().toString());
		LocalDate NHTDK=LocalDate.parse(txtNKT.getText().toString());
		String DD=txDD.getText().toString();
		
		return new CongTrinh(maCT,tenCT,NCP,NKC,NHTDK,DD);
	}
	private void updateeTableData() {
		CongTrinhDAO ds= new CongTrinhDAO();
		List<CongTrinh> list= ds.docTuBang();
		for(CongTrinh ct:list) {
			String[] rowData= {ct.getMaCT(),ct.getTenCT(),ct.getDiaDiem(),ct.getNgayCP()+"",ct.getNgayKC()+"",ct.getNgayHTDK()+"",ct.getDiaDiem()};
			tblModel.addRow(rowData);
		}
		table.setModel(tblModel);
	}
}
