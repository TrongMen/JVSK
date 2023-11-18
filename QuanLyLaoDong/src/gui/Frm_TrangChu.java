package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

import entity.NhanVien;
import javaswingdev.*;
public class Frm_TrangChu extends JFrame{
	
	private JPanel pnlCen;
	private JPanel pnlTrangChu;
	private JPanel Pnl_PhanCong = new Pnl_PhanCong();
	private JPanel Pnl_BaoCaoCongTrinh = new Pnl_BaoCaoCongTrinh();
	private JPanel Pn_QLCT = new Pn_QLCT();
	private JPanel Pn_QLPB = new Pn_QLPB();
	private JPanel Pnl_BaoCao = new Pnl_BaoCaoCongTrinh();
	private JPanel Pnl_QuanLyNhanVien = new Pnl_QuanLyNhanVien<NhanVien>();
	public Frm_TrangChu() {
		initComponents();
		setSize(1920, 1080);
        setLocationRelativeTo(null);
        
        
        GradientDropdownMenu menu = new GradientDropdownMenu();
        menu.setFont(new Font("Segoe UI", Font.BOLD, 20));
        menu.setGradientColor(new Color(28, 214, 65), new Color(44, 235, 194));
        menu.setBackground(new Color(22,36,33));
        menu.addItem("Trang chủ");
        menu.addItem("Nhân Viên","Quản lý nhân viên");
        menu.addItem("Công Trình","Quản lí công trình");
        menu.addItem("Công việc","Phân công công việc");
        menu.addItem("Phòng ban","Quản lý phòng ban");
        menu.addItem("Báo cáo", "Báo cáo ngày công", "Báo cáo công trình");
        menu.applay(this);
        pnlCen = new JPanel();
        pnlCen.setLayout(new BorderLayout());
        pnlCen.add(Pnl_BaoCaoCongTrinh, BorderLayout.CENTER);
        add( pnlCen,BorderLayout.CENTER);
        menu.addEvent(new MenuEvent() {

			@Override
			public void selected(int index, int subIndex, boolean menuItem) {
				// TODO Auto-generated method stub
				if(menuItem) {
					String selectedSubItem = menu.getMenuNameAt(index, subIndex).trim();
					if(selectedSubItem.equals("Phân công công việc")) {
						pnlCen.removeAll();
						pnlCen.add(Pnl_PhanCong,BorderLayout.CENTER);
						pnlCen.revalidate();
						pnlCen.repaint();
					}
					if(selectedSubItem.equals("Quản lí công trình")) {
						pnlCen.removeAll();
						pnlCen.add(Pn_QLCT,BorderLayout.CENTER);
						pnlCen.revalidate();
						pnlCen.repaint();
					}
					if(selectedSubItem.equals("Quản lí nhân viên")) {
						pnlCen.removeAll();
						pnlCen.add(Pnl_QuanLyNhanVien,BorderLayout.CENTER);
						pnlCen.revalidate();
						pnlCen.repaint();
					}
					if(selectedSubItem.equals("Quản lý phòng ban")) {
						pnlCen.removeAll();
						pnlCen.add(Pn_QLPB,BorderLayout.CENTER);
						pnlCen.revalidate();
						pnlCen.repaint();
					}
					
				}
			}
		});
       
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			new Frm_TrangChu().setVisible(true);
		});
	}
	
	private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
	
}
