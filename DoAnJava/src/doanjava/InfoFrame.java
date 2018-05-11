/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author SiVai
 */
public class InfoFrame extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				InfoFrame frame = new InfoFrame();
			        frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public InfoFrame()
    {
        setResizable(false);
        setSize(400, 300);
        setTitle("Thông tin");
        // hiển thị frame ở giữa màn hình
        setLocationRelativeTo(null);
	pnInfo = new JPanel();
	pnInfo.setBackground(Color.WHITE);
	setContentPane(pnInfo);
	pnInfo.setLayout(null);
        
	//setAlwaysOnTop(rootPaneCheckingEnabled);
	JLabel lb1 = new JLabel("Phần mềm mô phỏng");
        JLabel lb2 = new JLabel("THUẬT TOÁN CHIA TRANG Ô NHỚ");
        JLabel lb3 = new JLabel("Đồ án môn : Ngôn ngữ lập trình java");
        JLabel lb4 = new JLabel("Thành viên nhóm");
        JLabel lb5 = new JLabel("Lê Minh Tuấn - 15520986");
        JLabel lb6 = new JLabel("Võ Đình Thắng - 15520665");
        JLabel lb7 = new JLabel("Võ Sĩ Vai - 15520999");
        JLabel lb8 = new JLabel("Copyright 2018");
        
        lb1.setBounds(0, 10, 390, 30);
        lb1.setForeground(Color.red);
        lb1.setFont(new Font("Arial", Font.PLAIN, 10));
        lb1.setHorizontalAlignment(SwingConstants.CENTER);
        
        lb2.setBounds(0, 50, 390, 30);
        lb2.setForeground(Color.GREEN);
        lb2.setFont(new Font("Arial", Font.PLAIN, 20));
        lb2.setHorizontalAlignment(SwingConstants.CENTER);
        
        lb3.setBounds(0, 90, 390, 30);
        lb3.setForeground(Color.red);
        lb3.setFont(new Font("Arial", Font.PLAIN, 12));
        lb3.setHorizontalAlignment(SwingConstants.CENTER);
        
        lb4.setBounds(0, 120, 390, 30);
        lb4.setForeground(Color.PINK);
        lb4.setFont(new Font("Arial", Font.PLAIN, 10));
        lb4.setHorizontalAlignment(SwingConstants.CENTER);
        
        lb5.setBounds(0, 150, 390, 30);
        lb5.setForeground(Color.PINK);
        lb5.setFont(new Font("Arial", Font.PLAIN, 10));
        lb5.setHorizontalAlignment(SwingConstants.CENTER);
        
        lb6.setBounds(0, 180, 390, 30);
        lb6.setForeground(Color.PINK);
        lb6.setFont(new Font("Arial", Font.PLAIN, 10));
        lb6.setHorizontalAlignment(SwingConstants.CENTER);
        
        lb7.setBounds(0, 210, 390, 30);
        lb7.setForeground(Color.PINK);
        lb7.setFont(new Font("Arial", Font.PLAIN, 10));
        lb7.setHorizontalAlignment(SwingConstants.CENTER);
        
        lb8.setBounds(0, 230, 390, 30);
        lb8.setForeground(Color.BLACK);
        lb8.setFont(new Font("Arial", Font.PLAIN, 11));
        lb8.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        pnInfo.add(lb1);
        pnInfo.add(lb2);
        pnInfo.add(lb3);
        pnInfo.add(lb4);
        pnInfo.add(lb5);
        pnInfo.add(lb6);
        pnInfo.add(lb7);
        pnInfo.add(lb8);
        
    }
    
}
