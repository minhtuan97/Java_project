/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.UIManager.*;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author SiVai
 */

// Lớp MainFrame kế thừa từ JFrame 
public class MainFrame extends JFrame
{  

    // Serial Version
    private static final long serialVersionUID = 1L;
    
    // # Khai báo các các phần tử trong frame
     
    // Gom nhom cac radio button
    private ButtonGroup grSort;
    // Các nút trên thanh toolbar
    private JButton btPlay, btPause, btRefresh, btInputData, btInputFile;
    // doi tuong luu buoc chay cac dong code
    private DefaultListModel<String> model;
    // list string hien thi code
    private JList<String> lsCode;
    // biến thanh menu bar
    private JMenuBar MenuBar;
    // slider tốc độ , và thu phóng
    private JSlider SliderTocDoMoPhong,SliderThuPhongCode;
    // thanh cuon khung code
    private JScrollPane pnScrollCode; 
    // các nút radio chọn thuật toán
    private JRadioButton rdFIFO, rdOPT, rdLRU;
    // Thanh toolbar
    private JToolBar ToolBar; 
    // TabbedPane 
    private JTabbedPane TabbedPane;
    // các panel chính        
    private JPanel PanelChiaTrang,PanelCode,PanelTrangThai;
    // mang luu cac label hien thi mo phong
    private JLabel[] lbArrays;
    //tao mang label hang dau
    private JLabel[] lbArray_page;
    // label hien thi cac bien tam i
    private JLabel lbPoint1 = new JLabel();
    // label hien thi cac bien tam j
    private JLabel lbPoint2 = new JLabel();
    // label hine thi max min trong mo phong thuat toan
    private JLabel lbPointM = new JLabel();
    //button hien thi loi trang
    private JButton btnLoiTrang= new JButton();
     
    // # Khai báo các biến trong chương trình
    
    // bien luu so phan tu mang
    public int num = 20;
    // biến lưu số frame
    private int frame = 3;
    // mang int luu cac phan tu o nho
    private int[] array = {1,2,3,4,1,2,5,1,2,3,4,5,4,5,2,3,1,2,3,2};
    // mang int luu cac phan tu frame
    private int[] frames = new int[3];
    
    // mang cac threads
    private Thread[] threads = new Thread[10000];
    // vi tri thread hien tai mặc định =-1
    private int curT = -1;
    // thoi gian nghi, thuc thi mili giay
    private int time = 35;
    //ket qua loi trang
    int count;
    // trạng thái pause
    private int Pause = 1;        
    // Khai báo các màu(Color)
    private final Color cl7 = new Color(200, 150, 150);
    // # Khai báo các biến Hàm sử lý sự kiện

    
    // # Tạo Thanh MenuBar
    private void CreateMenuBar()
    {
        MenuBar = new JMenuBar();
        
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
        
        JMenu menuHelp = new JMenu("Help");
        JMenuItem guide = new JMenuItem("Guide");
        menuHelp.add(guide);
        
        JMenu menuAbout = new JMenu("Abouts");
        JMenuItem info = new JMenuItem("Information");
        JMenuItem fellback = new JMenuItem("Feel Back");
        menuAbout.add(info);
        menuAbout.add(fellback);
        
        info.addActionListener( new ActionListener() 
        { 
            public void actionPerformed(ActionEvent actionEvent) 
            { 
                InfoFrame infoframe = new InfoFrame();
                infoframe.setVisible(true);
            }
        } );
        
        MenuBar.add(menuFile);
        MenuBar.add(menuHelp);
        MenuBar.add(menuAbout);
        
        setJMenuBar(MenuBar);
        
    }    
    // Thanh công cụ
    private void CreateToolbar()
    {
        ToolBar = new JToolBar();
        // Kéo thả thanh toolbar
        ToolBar.setFloatable(false);
        // Đặt màu nền
        ToolBar.setBackground(Color.white);
        
        ToolBar.setLayout(new FlowLayout(0, 0, 0));
        
        ToolBar.setBorder(BorderFactory.createLineBorder(Color.PINK));
        //tb1.setLayout(new FlowLayout());
        //ImageIcon exitIcon = new ImageIcon("2.jpeg");
        ImageIcon Icon1 = new ImageIcon(new ImageIcon("icon/play-sign.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btPlay = new JButton(Icon1);
        btPlay.setBorder(new EmptyBorder(7, 7, 7, 7));
        btPlay.setText("Play");
        btPlay.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                DatTrangThai(0);
                if(Pause==1)
                {
                    ResumeAllThreads();
                    Pause = 0;
                }
                else
               {
                    //deleteArrays();
                    //setState(0);
                    
                    if(rdFIFO.isSelected())
                    {
                        FIFO();
                    }
                    if(rdOPT.isSelected())
                    {
                        //OPT();
                    }
                    if(rdLRU.isSelected())
                    {
                        //LRU();
                    }
                    waitEnd();
                    DatTrangThai(0);
                }
            }
        });
        
        ImageIcon Icon2 = new ImageIcon(new ImageIcon("icon/pause-symbol.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btPause = new JButton(Icon2);
        btPause.setBorder(new EmptyBorder(7, 7, 7, 7));
        btPause.setText("Pause");
        btPause.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                DatTrangThai(0);
                Pause = 1;
                SuspendAllThreads();
            }
	});
        
        ImageIcon Icon3 = new ImageIcon(new ImageIcon("icon/refresh.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btRefresh = new JButton(Icon3);
        btRefresh.setBorder(new EmptyBorder(7, 7, 7, 7));
        btRefresh.setText("Refresh");
        btRefresh.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                DatTrangThai(0);
                XoaKhungMoPhong();
                //HienKhungMoPhong();
            }
	});
       
        ImageIcon Icon4 = new ImageIcon(new ImageIcon("icon/folder.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btInputData = new JButton(Icon4);
        btInputData.setBorder(new EmptyBorder(7, 7, 7, 7));
        btInputData.setText("Nhập dữ liệu");        
        btInputData.addActionListener( new ActionListener()
        { 
            public void actionPerformed(ActionEvent actionEvent) 
            { 
                DatTrangThai(0);
                //JOptionPane.showMessageDialog(MainFrame, "I want to set this dialog on top of frame");
                InputFrame f2 = new InputFrame();
                f2.setVisible(true);
            } 
        } );
        
        ImageIcon Icon5 = new ImageIcon(new ImageIcon("icon/txt.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btInputFile = new JButton(Icon5);
        btInputFile.setBorder(new EmptyBorder(7, 7, 7, 7));
        btInputFile.setText("Nhập bằng file");
        btInputFile.addActionListener( new ActionListener()
        { 
            public void actionPerformed(ActionEvent actionEvent) 
            { 
                DatTrangThai(0);
                HienKhungMoPhong();
            } 
        } );        
        
        ToolBar.add(btRefresh);
        ToolBar.add(btPlay);
        ToolBar.add(btPause);
        
        ToolBar.addSeparator();
        ToolBar.add(btInputData);
        ToolBar.add(btInputFile);
        
        ToolBar.setSize(800, 400);
        add(ToolBar,BorderLayout.NORTH);
        
    }
    
    // Pannel Mô phỏng thuật giải ###
    private void CreatePanelMoPhong()
    {
        TabbedPane = new JTabbedPane();
        TabbedPane.setBorder(BorderFactory.createLineBorder(Color.yellow));
        
        PanelChiaTrang = new JPanel();
        PanelChiaTrang.setBackground(Color.WHITE);
        PanelChiaTrang.setLayout(null); 
           
	JLabel 	lptt = new JLabel("Chọn thuật toán : ");
        lptt.setBounds(25, 10, 120, 20);
        lptt.setBackground(Color.WHITE);
        PanelChiaTrang.add(lptt);
        
        rdFIFO = new JRadioButton("FIFO");
	rdFIFO.setBounds(125, 10, 50, 20);
        rdFIFO.setBackground(Color.WHITE);
	PanelChiaTrang.add(rdFIFO);
        
        rdOPT = new JRadioButton("OPT");
	rdOPT.setBounds(180, 10, 50, 20);
        rdOPT.setBackground(Color.WHITE);
	PanelChiaTrang.add(rdOPT);
        
        rdLRU = new JRadioButton("LRU");
	rdLRU.setBounds(240, 10, 50, 20);
        rdLRU.setBackground(Color.WHITE);
	PanelChiaTrang.add(rdLRU);
        
        grSort = new ButtonGroup();
        grSort.add(rdFIFO);
        grSort.add(rdLRU);
        grSort.add(rdOPT);
        
        rdFIFO.setSelected(true);
        // Sự kiện chọn radio button FIFO
        rdFIFO.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {              
                model.removeAllElements();
                addFIFOcode();
                lsCode.setSelectedIndex(0);
            }
        });
        // Sự kiện chọn radio button OPT
        rdOPT.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent event) 
            {      
                model.removeAllElements();
                addOPTcode();
                lsCode.setSelectedIndex(0);
            }
        });
        // Sự kiện chọn radio button LRU
        rdLRU.addActionListener(new ActionListener() 
        { 
            @Override
            public void actionPerformed(ActionEvent event) 
            {    
                model.removeAllElements();
                addLRUcode(); 
                lsCode.setSelectedIndex(0);
            }
        });
        
        JLabel 	lptd = new JLabel("||            Tốc độ mô phỏng:");
        lptd.setBounds(320, 10, 200, 20);
        lptd.setBackground(Color.WHITE);
        PanelChiaTrang.add(lptd);
        
        SliderTocDoMoPhong = new JSlider(JSlider.HORIZONTAL, 1, 6, 2); 
        SliderTocDoMoPhong.setBounds(480, 10, 150, 20);
        SliderTocDoMoPhong.setBackground(Color.WHITE);
        // Sự kiện thay đổi thanh tốc độ mô phỏng
        SliderTocDoMoPhong.addChangeListener(new javax.swing.event.ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) {
                switch(((JSlider) e.getSource()).getValue())
                {
                    case 1:
                        time = 42;
                        break;
                    case 2:
                        time = 35;
                        break;
                    case 3:
                        time = 30;
                        break;
                    case 4:
                        time = 22;
                        break;
                    case 5:
                        time = 15;
                        break;
                    case 6:
                        time = 8;
                        break;
                }
            }
        });     
        PanelChiaTrang.add(SliderTocDoMoPhong);
        // thiết lập lại kích không chịu ảnh hưởng của Layout bên ngoài
        PanelChiaTrang.setPreferredSize(new Dimension(900, 650));
        
        TabbedPane.add("Chia trang ô nhớ", PanelChiaTrang);
        add(TabbedPane,BorderLayout.WEST);
        DatTrangThai(0);
    }
    
    // Pannel hiển thị Code ###
     private void CreatePanelCode()
    {
        PanelCode = new JPanel();
        //PanelCode.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        PanelCode.setLayout(null);
        PanelCode.setBorder(new TitledBorder(null, "Code C/C++", TitledBorder.LEADING, TitledBorder.LEFT, null, null));
        PanelCode.setBackground(Color.white);   
        PanelCode.setForeground(Color.GREEN);
        PanelCode.setPreferredSize(new Dimension(380, 650));
        JLabel lb1 = new JLabel("Độ thu phóng: "); 
        lb1.setBounds(10, 20, 150, 23);
        JLabel lb2 = new JLabel("10 pixel"); 
        lb2.setBounds(290, 20, 150, 23);
        PanelCode.add(lb1);
        PanelCode.add(lb2);
        SliderThuPhongCode = new JSlider(JSlider.HORIZONTAL, 8, 20, 10);
        //slider1.setMajorTickSpacing(10);
        //slider1.setMinorTickSpacing(1);
        //slider1.setPaintTicks(true);
        //slider1.setPaintLabels(true);
        SliderThuPhongCode.setBounds(120, 20, 150, 23);
        SliderThuPhongCode.setBackground(Color.WHITE);
        PanelCode.add(SliderThuPhongCode);
        
        SliderThuPhongCode.addChangeListener(new javax.swing.event.ChangeListener() {
         public void stateChanged(ChangeEvent e) {
            lsCode.setFont(new Font("Monospaced",Font.BOLD,((JSlider)e.getSource()).getValue()));
            lb2.setText(((JSlider)e.getSource()).getValue() +" pixels");
            //lsCode.repaint();
         }
      });
        
        pnScrollCode = new JScrollPane();
	pnScrollCode.setBounds(10, 50, 350, 530);
	PanelCode.add(pnScrollCode);
	model = new DefaultListModel<>();
	lsCode = new JList<String>(model);
	lsCode.setBorder(new LineBorder(Color.cyan));
	lsCode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	lsCode.setFont(new Font("Monospaced",Font.BOLD,11));
	pnScrollCode.setViewportView(lsCode);
        
        model.removeAllElements();
        addFIFOcode();   
        lsCode.setSelectedIndex(0);
        
        add(PanelCode,BorderLayout.CENTER);
                         
    }
    
    // Thanh trạng thái của ứng dụng ###
    private void CreatePanelStatus()
    {
        PanelTrangThai = new JPanel();
        PanelTrangThai.setBackground(Color.LIGHT_GRAY);
        PanelTrangThai.setBorder(BorderFactory.createLineBorder(Color.red));
        PanelTrangThai.setPreferredSize(new Dimension(800,30));
        add(PanelTrangThai,BorderLayout.SOUTH);
    }
     
    // constructor cua lop MainFrame ###
    public MainFrame()
    {
        lbArrays = new JLabel[num*(frame+2)];
        lbArray_page=new JLabel[num];
        // thiet lap kieu chay chuong trinh
	setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        // khởi tạo frame
        // Đặt Tiêu đề cho frame
        setTitle("Đồ Án Java");
        // Đặt kích thước frame
        setSize(1280,720);
        // Đặt logo cho ứng dụng
        try 
        {           
            Image logo = ImageIO.read(new File("icon/2.jpeg"));
            setIconImage(logo);
        } 
        catch (IOException ex) 
        {
            // handle exception...
        }  
        //ImageIcon Icon0 = new ImageIcon(new ImageIcon("icon/a2.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)); 
        // Đặt layout cho frame
        setLayout(new BorderLayout());
        // hiển thị frame ở giữa màn hình
        setLocationRelativeTo(null);
        // tắt chương trình khi bấm nút X
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        // khong thay doi kich thuoc
        setResizable(false);
        // Đặt kích thước tối đa cho frame
        //setMaximumSize(new Dimension(10000, 10000));
        // Đặt kích thước tối thiểu cho frame
        //setMinimumSize(new Dimension(300, 300));
        // frame có kích thước vừa đủ với nội dung của frame 
        //pack();
          
        // tạo thanh menu
        CreateMenuBar();
        // Tạo thanh công cụ
        CreateToolbar();  
        // thêm Pannel chính hiển thị đồ họa
        CreatePanelMoPhong();
        // tạo pannel hiển thị code
        CreatePanelCode();
        // thêm thanh trạng thái
        CreatePanelStatus(); 
    }
    
    // set lock and feel ###
    public static void setLockAndFeel() 
    {
        // CDE/Motif  ; Windows ; Windows classic; Metal; Nimbus
        try 
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
            {
                if ("Windows".equals(info.getName())) 
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (Exception ex) 
        {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
    }
    
    // Hàm Main ###
    public static void main(String[] args)
    {
        // chạy ứng dụng trong một luồng do EventQueue quản lý : đảm bảo an toàn cho ứng dụng
        EventQueue.invokeLater(
                new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        setLockAndFeel();
                        try 
                        {
                            MainFrame f = new MainFrame();
                            //set JFrame full screen
                            //f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                             f.setVisible(true);
                        } 
                        catch (Exception e) 
                        {
                            e.printStackTrace();
                        }
                    }
		});          
    }      
    
    // Hàm sử lý trạng thái các nút
    public void DatTrangThai(int s)
    {
        switch(s)
        {
            case 0:// trạng thái lúc khởi chạy chương trình
                btPlay.setEnabled(true);
                btPause.setEnabled(true);
                btRefresh.setEnabled(true);
                btInputData.setEnabled(true);
                btInputFile.setEnabled(true);
                rdFIFO.setEnabled(true);
                rdLRU.setEnabled(true);
                rdOPT.setEnabled(true);
                break;
            case 1:// trạng thái sau khi nhập dữ liệu
                btPlay.setEnabled(true);
                btPause.setEnabled(true);
                btRefresh.setEnabled(true);
                btInputData.setEnabled(true);
                btInputFile.setEnabled(true);
                rdFIFO.setEnabled(true);
                rdLRU.setEnabled(true);
                rdOPT.setEnabled(true);
                break;
            case 2:// trạng thái khi play
                btPlay.setEnabled(false);
                btPause.setEnabled(true);
                btRefresh.setEnabled(true);
                btInputData.setEnabled(false);
                btInputFile.setEnabled(false);
                rdFIFO.setEnabled(false);
                rdLRU.setEnabled(false);
                rdOPT.setEnabled(false);
            case 3:// trạng thái khi pause
                btPlay.setEnabled(true);
                btPause.setEnabled(false);
                btRefresh.setEnabled(true);
                btInputData.setEnabled(false);
                btInputFile.setEnabled(false);
                rdFIFO.setEnabled(false);
                rdLRU.setEnabled(false);
                rdOPT.setEnabled(false);
            case 4:// trạng thái khi hoàn tất
                btPlay.setEnabled(false);
                btPause.setEnabled(false);
                btRefresh.setEnabled(false);
                btInputData.setEnabled(true);
                btInputFile.setEnabled(true);
                rdFIFO.setEnabled(true);
                rdLRU.setEnabled(true);
                rdOPT.setEnabled(true);
                break;
            default:
                //
                ;
        }
    }
    
    // tao Du lieu chay ben o code
    // thuat toan FIFO
    public void addFIFOcode()
    {
        model.addElement("void FIFO(int n, int frame, int *a, int *frames)");
        model.addElement("{");
        model.addElement("int i, j = 0, k, available, count = 0;");
        model.addElement("for (i = 1; i <= n; i++)");
        model.addElement("{");
        model.addElement("  available = 0;");
        model.addElement("  for (k = 0; k<frame; k++)");
        model.addElement("      if (frames[k] == a[i])");
        model.addElement("          available = 1; ");
        model.addElement("  if(available == 0) ");
        model.addElement("  {");
        model.addElement("      frames[j] = a[i];");
        model.addElement("      j = (j + 1) % frame;");
        model.addElement("      count++;");
        model.addElement("  }");
        model.addElement("}");
    }
    
    public void addOPTcode()
    {
        model.addElement("void OTP(int n, int frame, int *a, int *frames)");
        model.addElement("for (int i = 1; i <= frame; i++)");
        model.addElement("frames[i] = -1;");
        model.addElement("int i, j, available, count = 0;");
        model.addElement("printf( \" Chuoi\\t|Khung trang\");");
        model.addElement("for (int k = 0; k<frame - 1; k++)");
        model.addElement("printf( \"\\t\");");
        model.addElement("printf( \"|\\n\");");
        model.addElement("for (i = 1; i <= n; i++)");
        model.addElement("{");
        model.addElement("printf( \" %d\\t\",a[i]);");
        model.addElement("available = 0;");
        model.addElement("for (int k = 1; k <= frame; k++)");
        model.addElement("if (frames[k] == a[i])");
        model.addElement("available = 1;");
        model.addElement("if (available == 0)");
        model.addElement("{");
        model.addElement("int j = TimViTriLRU(i, n, frame, a, frames);");
        model.addElement("frames[j] = a[i];");
        model.addElement("count++;");
        model.addElement("printf( \"|\");");
        model.addElement("for (int k = 1; k <= frame; k++)");
        model.addElement("printf( \"%d\\t\",frames[k]);");
        model.addElement("printf( \"| F\");");
        model.addElement("}");
        model.addElement("else");
        model.addElement("{");
        model.addElement("printf( \"|\");");
        model.addElement("for (k = 1; k <= frame; k++)");
        model.addElement("printf( \"%d\\t\",frames[k]);");
        model.addElement("printf( \"|\");");
        model.addElement("}");
        model.addElement("printf( \"So trang loi la: %d\\n\",count);");
        model.addElement("}");
        model.addElement("");
        model.addElement("int TimViTriLRU(int i, int n, int frame, int *a, int *frames)");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        
        
    }  
    
    public void addLRUcode()
    {
        model.addElement("retet");
        model.addElement("tete");
        model.addElement("etete");
        model.addElement("ete");
        model.addElement("etet");
        model.addElement("tet");
        model.addElement("tet");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
        model.addElement("");
    } 
    
    public void FIFO()
    {     
        HighLight(0);
        for (int i = 0; i<frame; i++)
		frames[i] = -1;
//        for(int i=0;i<frame;i++)
//        {
//            lbArrays[num*(i+1)].setText("x");//+frames[i]);
//        }
        HighLight(2);
	int i, j = 0, k, available;
        count = 0;
        //HighLight(3);
	for (i = 0; i < num; i++)
	{
                HighLight(3);
		available = 0; 
                //so sanh
                
		for (k = 0; k<frame; k++)
			if (frames[k] == array[i])
                        {
                            Move(lbArrays[i],lbArrays[i+num*k]);
                            available = 1; 
                            break;
                        }

                //
		if (available == 0) 
		{
                        SetValue(lbArrays[i+num*1],frames[0]);
                        SetValue(lbArrays[i+num*2],frames[1]);
                        SetValue(lbArrays[i+num*3],frames[2]);
                        frames[j]=array[i];
			frames[j] = array[i];
                        HighLight(6);
                        Move(lbArrays[i],lbArrays[i+num*frame]);
                        HighLight(9);
                        SetF(lbArrays[i+num*(frame+1)]);
                        LightLabel(lbArrays[i+num*(j+1)]);
                        HighLight(11);
                        Move(lbArrays[i],lbArrays[i+num*(j+1)]);
                        LightLabel(lbArrays[i]);
                        SetValue(lbArrays[i],array[i]);     
			j = (j + 1) % frame;
			count++;
		}
		else
		{
                    HighLight(8);
                    SetValue(lbArrays[i+num*1],frames[0]);
                    SetValue(lbArrays[i+num*2],frames[1]);
                    SetValue(lbArrays[i+num*3],frames[2]);
                    Move(lbArrays[i],lbArrays[i+num*(k+1)]);
                    SetT(lbArrays[i+num*(frame+1)]);
		}
	}
        Result();
    }
    public void Result()
    {
            curT ++;
            int cur = curT;
            threads[cur] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                            if (cur != 0) {
                                    threads[cur-1].join();
                            }
                            btnLoiTrang.setText("Số lỗi trang là: "+count); 
                            btnLoiTrang.setBackground(Color.GREEN);
                            Thread.sleep(time);
                    } catch (Exception e) {
                    }
                }
            });
            threads[cur].start();
    }
    public void Move(JLabel lb1, JLabel lb2) 
    {
            curT ++;
            int cur = curT;
            threads[cur] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                            if (cur != 0) {
                                    threads[cur-1].join();
                            }
                            if(lb1.getY()<lb2.getY())
                                while (lb1.getY() < lb2.getY()) {
                                    lb2.setOpaque(false);
                                    lb1.setLocation(lb1.getX(), lb1.getY() + 1);
                                    Thread.sleep(time);
                            }
                            if(lb1.getY()>lb2.getY())
                                while (lb1.getY() > lb2.getY()) {
                                    lb2.setOpaque(false);
                                    lb1.setLocation(lb1.getX(), lb1.getY() - 1);
                                    Thread.sleep(time);
                            }
                            Thread.sleep(3*time);
                            lb1.setLocation(lb2.getX(), lb2.getY());
                    } catch (Exception e) {
                    }
                }
            });
            threads[cur].start();
    }
    
    public void LightLabel(JLabel lb)
    {
        curT++;

            int cur = curT;
            threads[cur] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                            if (cur != 0) {
                                    threads[cur-1].join();
                            }
                            lb.setBackground(Color.YELLOW);
                            Thread.sleep(3*time);
                    } catch (Exception e) {

                    }
                }
            });
            threads[cur].start();
    }
    
    public void SetF(JLabel lb)
    {
        curT++;

            int cur = curT;
            threads[cur] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                            if (cur != 0) {
                                    threads[cur-1].join();
                            }
                            lb.setText("F");
                            Thread.sleep(2*time);
                    } catch (Exception e) {

                    }
                }
            });
            threads[cur].start();
    }
    
    public void SetT(JLabel lb)
    {
        curT++;

            int cur = curT;
            threads[cur] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                            if (cur != 0) {
                                    threads[cur-1].join();
                            }
                            lb.setText("T");
                            Thread.sleep(time);
                    } catch (Exception e) {

                    }
                }
            });
            threads[cur].start();
    }
    public void SetValue(JLabel lb, int x)
    {
        curT++;

            int cur = curT;
            threads[cur] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                            if (cur != 0) {
                                    threads[cur-1].join();
                            }
                            lb.setText(""+x);
                            Thread.sleep(3*time);
                    } catch (Exception e) {

                    }
                }
            });
            threads[cur].start();
    } 
    
    public void SetLabel()
    {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        if (cur != 0) {
                                threads[cur-1].join();
                        }
                        for(int z=0;z<frame;z++)
                        {
                            lbArrays[z+num*(frame)].setText(""+frames[z]);
                        }
                } catch (Exception e) {

                }
            }
        });
        threads[cur].start();
    }
    
    // hàm khi thực hiện mô phỏng xong
    public void waitEnd() 
    {
    	curT++;	
        int cur = curT;
        threads[cur] = new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                try 
                {
                    if (cur != 0) 
                    {
                        threads[cur-1].join();
                    }
                    setState(4);
                    for (int i = 0; i < num; i++) 
                    {
                        //lbArrays[i].setForeground(Color.darkGray);
                    }
                    lbPoint1.setText("");
                    lbPoint2.setText("");
                    lbPointM.setText("");
                    //FormCompleteSorted form = new FormCompleteSorted();
                    //form.setVisible(true);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }
    
    // dừng tất cả threads
    public void StopAllThreads() 
    {
    	for (int i = 0; i < curT; i++) 
        {
            try 
            {
                threads[i].interrupt();
            } catch (Exception e) {}
        }
	curT = -1;
    }
    
    // tiếp tục tất cả threads
    public void ResumeAllThreads() 
    {
    	for (int i = 0; i < curT; i++) 
        {
            try 
            {
                threads[i].resume();;
            } catch (Exception e) {}
        }
    }
    
    // tạm dừng tất cả threads
    public void SuspendAllThreads() 
    {
    	for (int i = 0; i < curT; i++) 
        {
            try 
            {
                threads[i].suspend();
            } catch (Exception e) {}
        }
    }
    
    // hàm hiển thị khung mảng ra màn hình
    public void HienKhungMoPhong()//int[] a,int frame) 
    {
        btnLoiTrang.setFont(new Font("Arial", Font.PLAIN, 20));
        btnLoiTrang.setText("Số lỗi trang là: ");
        btnLoiTrang.setBounds(300,40,200,40);  
        PanelChiaTrang.add(btnLoiTrang);                     
        int frame = 3;
        /////////////////////////////////////////////
        int intx=450-42*(num/2);
        //String str;
        
        for(int i=0; i< num*(frame+2) ;i++ )//((a.length)*(frame+2)); i++ )//lbArrays.length; i++)
        {
            lbArrays[i]= new JLabel();
            lbArrays[i].setBounds(intx+42*(i%num), 100+42*(i/num), 40, 40);
            lbArrays[i].setHorizontalAlignment(SwingConstants.CENTER);
            lbArrays[i].setVerticalAlignment(SwingConstants.CENTER);
            lbArrays[i].setBackground(Color.WHITE);
            lbArrays[i].setBorder(BorderFactory.createLineBorder(Color.black));
            lbArrays[i].setOpaque(true);
            PanelChiaTrang.add(lbArrays[i]);
            PanelChiaTrang.repaint();
        }
        for(int i=0;i<num;i++)
        {
            
            lbArrays[i].setText(""+array[i]);
            lbArrays[i].setBorder(BorderFactory.createLineBorder(Color.GREEN));
            
        }
        for(int i=num*(frame+1); i<lbArrays.length; i++)
        {
            lbArrays[i].setBorder(BorderFactory.createLineBorder(Color.blue));
        }
        for(int i=0; i<num; i++)
        {
            lbArray_page[i]= new JLabel();
            lbArray_page[i].setBounds(intx+42*i, 100, 40, 40);
            lbArray_page[i].setHorizontalAlignment(SwingConstants.CENTER);
            lbArray_page[i].setVerticalAlignment(SwingConstants.CENTER);
            lbArray_page[i].setBackground(Color.WHITE);
            lbArray_page[i].setText(""+array[i]);
            lbArray_page[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            lbArray_page[i].setOpaque(true);
            PanelChiaTrang.add(lbArray_page[i]);
            PanelChiaTrang.repaint();
        }
    }
    
    // hàm xóa khung mô phỏng
    public void XoaKhungMoPhong() 
    {
        for (int i = 0; i < num*(frame+2); i++) 
        {
            //lbArrays[i].setVisible(false);
            PanelChiaTrang.remove(lbArrays[i]);
        }
        for(int i=0; i<num; i++)
        {
            PanelChiaTrang.remove(lbArray_page[i]);
        }
        PanelChiaTrang.remove(btnLoiTrang);

//        lbPoint1.setText("");
//        lbPoint2.setText("");
//        lbPointM.setText("");
//        pnImitiate.remove(lbPoint1);
//        pnImitiate.remove(lbPoint2);
//        pnImitiate.remove(lbPointM);

        for (int i = 0; i < curT; i++) 
        {
            try 
            {
                threads[i].interrupt();
            } 
            catch (Exception e) {}
        }
        curT = -1;

        PanelChiaTrang.revalidate();
        PanelChiaTrang.repaint();
        //setState(0);
    }
    
    // thread đặt lại vị trí cho mảng
    public void threadReLocate() 
    {
    	curT++;	
        int cur = curT;
        threads[cur] = new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                try 
                {
                    if (cur != 0) 
                    {
                        threads[cur-1].join();
                    }
                    reLocate();
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }
    
    // hàm đặt lại vị trí của mảng
    public void reLocate() 
    {
    	for (int i = 0; i < num; i++) 
        {
            //set location label
            if (i == 0)
                lbArrays[i].setLocation(((int) ((18 - num) * 0.5) * 70) + 100, 150);
            else
                lbArrays[i].setLocation(lbArrays[i-1].getX() + 70, 150);
    	}
    }
    
    // đặt background label khi di chuyển
    public void setBackgroundMoving(JLabel lb1, JLabel lb2) {
    	lb1.setOpaque(true);
    	lb2.setOpaque(true);
    	
    	lb1.setBackground(SystemColor.ORANGE);
    	lb2.setBackground(SystemColor.ORANGE);
    }
    
    // đặt background label khi di chuyển xong
    public void setBackgroundDone(JLabel lb1, JLabel lb2) 
    {
    	lb1.setOpaque(true);
    	lb2.setOpaque(true);
    	lb1.setBackground(SystemColor.inactiveCaption);
    	lb2.setBackground(SystemColor.inactiveCaption);
    }
    
    // highLight dòng code đang thực hiện (0 ... n)
    public void HighLight(int line) 
    {
        curT++;
        int cur = curT;
        threads[cur] = new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                try 
                {
                    if (cur != 0) 
                    {
                        threads[cur-1].join();
                    }
                    lsCode.setSelectedIndex(line);
                    // Tu cuon den dong dang highlight
                    lsCode.ensureIndexIsVisible(line); 
                    Thread.sleep(2*time);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }
}             
                
