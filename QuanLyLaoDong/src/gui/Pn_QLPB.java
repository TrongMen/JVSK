package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import connectDB.DatabaseConnection;
import dao.PhongBanDAO;
import entity.CongTrinh;
import entity.PhongBan;

public class Pn_QLPB extends JPanel implements ActionListener,MouseListener{
	private Box pnNor;
	private JPanel pnHead;
	private JLabel lbHead;
	private JPanel pnCen;
	private Box b;
	private JPanel pnA;
	private Box b1;
	private Box b2;
	private Box b3;
	private JPanel pnCen1;
	private Box b4;
	private Box b5;
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
	private JLabel lbMaPB;
	private JTextField txtMaPB;
	private JLabel lbTenPB;
	private JComboBox cb;
	private String selectedValue;
	private JLabel SLNV;
	private JTextField txMaNV;
	PhongBanDAO dspb=new PhongBanDAO();
	private JTextField txSLNV;
	private JLabel lbSLNV;

	public Pn_QLPB(){
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
		lbHead = new JLabel("QUẢN LÝ PHÒNG BAN");
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
		b1.add(lbMaPB = new JLabel("Mã phòng ban:"));
		lbMaPB.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		b1.add(txtMaPB = new JTextField(30));
		b1.add(Box.createHorizontalStrut(20));
		
		
		b.add(pnCen1 = new JPanel());
		pnCen1.add(b2 = Box.createHorizontalBox());
		b2.add(lbTenPB = new JLabel("Tên phòng ban:"));
		lbTenPB.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		String[] pb= {"phòng kế toán","phòng dự án","phòng chuyên môn","phòng kinh doanh","phòng kỹ thuật"};
		b2.add(cb= new JComboBox());
		updateComboBox();
		selectedValue = (String) cb.getSelectedItem();
		b2.add(Box.createHorizontalStrut(20));
		
		
	
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
		String[] header = "Mã phòng ban;Phòng ban".split(";");
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
		
		DatabaseConnection.getInstance().getConnection();
		updateeTableData();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	private void updateComboBox() {
	PhongBanDAO ds = new PhongBanDAO();
		
		List<PhongBan>  list= ds.docTuBang();
		for(PhongBan s : list) {
		cb.addItem(s.getTenPB());}}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row= table.getSelectedRow();
		txtMaPB.setText(table.getValueAt(row, 0).toString());
		cb.setSelectedItem(table.getValueAt(row, 1).toString().trim());
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
			String maPB=txtMaPB.getText();
			String tenPB=(String) cb.getSelectedItem();
			PhongBan pb= new PhongBan(maPB,tenPB);
			if(dspb.create(pb)) {
				String[] row= {maPB,tenPB};
				tblModel.addRow(row);
				JOptionPane.showMessageDialog(null,"Thêm thành công");
				xoaRongTextfields();
			}else {
				JOptionPane.showMessageDialog(null,"Trùng mã công trình");
				txtMaPB.selectAll();
				txtMaPB.requestFocus();
			}
			tblModel.getDataVector().removeAllElements();
			updateeTableData();
		}
		else if(o.equals(btnXoa)) {
			int row=table.getSelectedRow();
			if(row>=0) {
				String maPB=(String) table.getValueAt(row,0);
				if(dspb.delete(maPB)) {
					tblModel.removeRow(row);
					xoaRongTextfields();
				}
			}
			tblModel.getDataVector().removeAllElements();
			updateeTableData();
		}
		else if(o.equals(btnSua)) {
			int row=table.getSelectedRow();
			if(row>=0) {
				PhongBan pb=reverSPFromTextFile();
				if(dspb.update(pb)) {
					table.setValueAt(cb.getSelectedItem().toString(), row,1);
				}
			}
			tblModel.getDataVector().removeAllElements();
			updateeTableData();
		}
		else if(o.equals(btnTim)) {
			PhongBanDAO ds= new PhongBanDAO();
			List<PhongBan> list= ds.getpbtheomaPB(txtMaPB.getText());
			
			if(txtMaPB.getText().equals(""))
				JOptionPane.showMessageDialog(this,"Vui lòng nhập mã công trình cần tìm!");
			else if(list.size()==0)
					JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			else {
				tblModel.getDataVector().removeAllElements();
				for(PhongBan s:list) {
					String[] rowData= {s.getMaPB(),s.getTenPB()};
					tblModel.addRow(rowData);
				}
				table.setModel(tblModel);
			}
			tblModel.getDataVector().removeAllElements();
			updateeTableData();
		}
	}
	private void xoaRongTextfields() {
		txtMaPB.setText("");
		cb.setSelectedItem(null);
		txSLNV.setText("");
		txtMaPB.requestFocus();
	}
	private PhongBan  reverSPFromTextFile() {
		String maPB=txtMaPB.getText().toString();
		String tenPB=cb.getSelectedItem().toString();

		return new PhongBan(maPB,tenPB);
	}
	private void updateeTableData() {
		PhongBanDAO ds= new PhongBanDAO();
		List<PhongBan> list= ds.docTuBang();
		for(PhongBan pb:list) {
			String[] rowData= {pb.getMaPB(),pb.getTenPB()};
			tblModel.addRow(rowData);
		}
		table.setModel(tblModel);
	}

}
