package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import javaswingdev.*;
public class Frm_TrangChu extends JFrame{
	private JPanel pnlCen;
	private JPanel pnlTrangChu;
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
        add( pnlCen = new JPanel(),BorderLayout.CENTER);
        pnlCen.setLayout(new BorderLayout());
        pnlCen.add(pnlTrangChu = new JPanel(), BorderLayout.CENTER);
        menu.addEvent(new MenuEvent() {
			
			@Override
			public void selected(int arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				
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
