/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

import java.awt.EventQueue;
import java.awt.Window;
import javax.swing.JFrame;

/**
 *
 * @author SiVai
 */

// Lớp MainFrame kế thừa từ JFrame 
public class MainFrame extends JFrame
{  
    
    public MainFrame()
    {
        // Đặt Tiêu đề cho frame
        setTitle("Đồ Án Java");
        // Đặt kích thước frame
        setSize(800,500);
        // Đặt layout cho frame
        setLayout(null);
        // hiển thị frame ở giữa màn hình
        setLocationRelativeTo(null);
        // tắt chương trình khi bấm nút X
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
    public static void main(String[] args)
    {
        MainFrame f = new MainFrame();
        f.setVisible(true);
    }
    
    
    
    
}
