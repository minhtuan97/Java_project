/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author SiVai
 */

// Lớp MainFrame kế thừa từ JFrame 
public class MainFrame extends JFrame
{  
    // Khai báo các component
    private JButton bt1,bt2,bt3,bt4;
    private JPanel pn1,pn2,pn3,pn4,pn5;
    private JSpinner jsp1;
    private JSlider slSize;
    private JScrollPane pnScroll; 
    private JToolBar tb1,tb2,tb3;
    private JRadioButton bbb1;
    private JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8;
    private final Color cl1 = new Color(255, 153, 153);
    private final Color cl2 = new Color(20, 153, 153);
    private final Color cl3 = new Color(20, 15, 255);
    private final Color cl4 = new Color(20, 255, 255);
    private final Color cl5 = new Color(20, 150, 255);
    private final Color cl6 = new Color(200, 15, 255);
    private final Color cl7 = new Color(200, 150, 150);
    
    private ButtonGroup grSort;
    private JMenuBar mb1;
    
    
    
    // Tạo menuBar
    private void CreateMenu()
    {
        mb1 = new JMenuBar();
        
        JMenu menuFile = new JMenu("File");
        JMenuItem news = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");
        menuFile.add(news);
        menuFile.add(open);
        menuFile.add(save);
        menuFile.addSeparator();
        menuFile.add(exit);
        
        JMenu menuEdit = new JMenu("Edit");
        JMenuItem font = new JMenuItem("Font");
        JMenuItem size = new JMenuItem("Size");
        menuEdit.add(font);
        menuEdit.add(size);
        
        JMenu menuHelp = new JMenu("Help");
        JMenuItem guide = new JMenuItem("Guide");
        JMenuItem tutorial = new JMenuItem("Tutorial");
        menuHelp.add(guide);
        menuHelp.add(tutorial);
        
        JMenu menuAbout = new JMenu("Abouts");
        JMenuItem info = new JMenuItem("Information");
        JMenuItem checkupdate = new JMenuItem("Check Update");
        JMenuItem fellback = new JMenuItem("Feel Back");
        menuAbout.add(info);
        menuAbout.add(checkupdate);
        menuAbout.add(fellback);
        
        mb1.add(menuFile);
        mb1.add(menuEdit);
        mb1.add(menuHelp);
        mb1.add(menuAbout);
        
        setJMenuBar(mb1);
        
    }
    
    private void CreateToolbar()
    {
        tb1 = new JToolBar();
        // Kéo thả thanh toolbar
        tb1.setFloatable(false);
        // Đặt màu nền
        tb1.setBackground(Color.white);
        
        tb1.setBorder(BorderFactory.createLineBorder(Color.PINK));
        //tb1.setLayout(new FlowLayout());
        //ImageIcon exitIcon = new ImageIcon("2.jpeg");
        ImageIcon Icon1 = new ImageIcon(new ImageIcon("icon/2.jpeg").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton Button1 = new JButton(Icon1);
        Button1.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        ImageIcon Icon2 = new ImageIcon(new ImageIcon("icon/a2.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton Button2 = new JButton(Icon2);
        Button2.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        ImageIcon Icon3 = new ImageIcon(new ImageIcon("icon/a3.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton Button3 = new JButton(Icon3);
        Button3.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        ImageIcon Icon4 = new ImageIcon(new ImageIcon("icon/a4.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton Button4 = new JButton(Icon4);
        Button4.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        
        JButton Button5 = new JButton("Nhập dữ liệu");
        
        JButton Button6 = new JButton("Nhập bằng file");
        
        JButton Button7 = new JButton("");
        
        JButton Button8 = new JButton("");
        
        
        lb5 = new JLabel("Tốc độ: ");
        lb5.setPreferredSize(new Dimension(100, 30));
        
        
        SpinnerModel value =  new SpinnerNumberModel(2, //initial value  
                0, //minimum value  
                5, //maximum value  
                0.5); //step  
        JSpinner spinner = new JSpinner(value); 
        spinner.setPreferredSize(new Dimension(50, 20));
        //spinner.setSize(50,40);
        //spinner.setBounds(100,100,50,30);    
         
        
        tb1.add(Button1);
        tb1.add(Button2);
        tb1.add(Button3);
        tb1.add(Button4);
        tb1.add(Button5);
        tb1.add(Button6);
        tb1.add(lb5);
        tb1.add(spinner);   
        
        
        
        tb1.setSize(800, 400);
        add(tb1,BorderLayout.NORTH);
    }
    
    private void CreatePannelMain()
    {
        JTabbedPane tp1 = new JTabbedPane();
        tp1.setBorder(BorderFactory.createLineBorder(Color.yellow));
        pn1 = new JPanel();
        pn1.setBackground(Color.WHITE);
        lb2 = new JLabel("Độ thu phóng: "); 
        pn1.add(lb2);
        JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 0, 100, 30); 
        pn1.add(slider2);
        // setPreferredSize() thiết lập lại kích thước đối tượng, không chịu ảnh hưởng của Layout bên ngoài
        pn1.setPreferredSize(new Dimension(900, 650));
        
        pn4 = new JPanel();
        pn4.setBackground(Color.LIGHT_GRAY);
        lb6 = new JLabel("Định thì CPU"); 
        pn4.add(lb6);
        
        // setPreferredSize() thiết lập lại kích thước đối tượng, không chịu ảnh hưởng của Layout bên ngoài
        pn4.setPreferredSize(new Dimension(900, 650));
        tp1.add("Chia trang ô nhớ", pn1);
        tp1.add("Định thì CPU", pn4);
        add(tp1,BorderLayout.WEST);
    }
    
     private void CreatePannelCode()
    {
        pn3 = new JPanel();
        //pn3.setBorder(BorderFactory.createLineBorder(Color.red));
        //pn3.setBorder(new TitledBorder(null, "Code C/C++", TitledBorder.LEADING, TitledBorder.LEFT, null, null));
        pn3.setBackground(Color.white);   
        pn3.setForeground(cl1);
        pn3.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        pn3.setPreferredSize(new Dimension(380, 650));
        lb3 = new JLabel("Độ thu phóng: "); 
        pn3.add(lb3);
        JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 30); 
        pn3.add(slider1);
        
        
        add(pn3,BorderLayout.CENTER);
    }
     
    private void CreatePannelStatus()
    {
        pn2 = new JPanel();
        pn2.setBackground(Color.WHITE);
        pn2.setBorder(BorderFactory.createLineBorder(Color.red));
        pn2.setSize(800, 100);
        lb1 = new JLabel("thanh trang thai");
        lb1.setSize(800,50);
        //Bt1.setPreferredSize(new Dimension(200,50));
        pn2.add(lb1);
        add(pn2,BorderLayout.SOUTH);
    }
     
    // constructor cua lop MainFrame
    public MainFrame()
    {
        // khởi tạo frame
        // Đặt Tiêu đề cho frame
        setTitle("Đồ Án Java");
        // Đặt kích thước frame
        setSize(1280,720);
        
        // Đặt logo cho ứng dụng
        try {           
            Image logo = ImageIO.read(new File("icon/2.jpeg"));
            setIconImage(logo);
       } catch (IOException ex) {
            // handle exception...
       }
        
        //ImageIcon Icon0 = new ImageIcon(new ImageIcon("icon/a2.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        
        // Đặt layout cho frame
        setLayout(new BorderLayout());
        // hiển thị frame ở giữa màn hình
        setLocationRelativeTo(null);
        // tắt chương trình khi bấm nút X
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Đặt kích thước tối đa cho frame
        //setMaximumSize(new Dimension(10000, 10000));
        // Đặt kích thước tối thiểu cho frame
        setMinimumSize(new Dimension(300, 300));
        //
        //pack();
        setResizable(false);
        
        
        // tạo thanh menu
        CreateMenu();
        // Tạo thanh công cụ
        CreateToolbar();  
        // thêm Pannel chính hiển thị đồ họa
        CreatePannelMain();
        // thêm thanh trạng thái
        CreatePannelStatus();
        // tạo pannel hiển thị code
        CreatePannelCode();
        
        //pack();
        
        
    }
    public static void main(String[] args)
    {
        MainFrame f = new MainFrame();
        f.setVisible(true);
    }
    
    
    
    
}
