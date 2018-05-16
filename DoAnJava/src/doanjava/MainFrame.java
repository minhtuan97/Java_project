/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
    
    private JButton btPlay,btPause;
    // doi tuong luu buoc chay cac dong code
    private DefaultListModel<String> model;
    
    private JLabel lb1,lb2,lb5,lb6;
    
    private JList<String> lsCode;
    
    private JMenuBar MenuBar;
    
    private JSpinner jsp1;
    
    private JSlider SliderThuPhongMoPhong,SliderThuPhongCode;
    // thanh cuon khung code
    private JScrollPane pnScrollCode; 
    
    private JRadioButton rdFIFO, rdOPT, rdLRU;
   
    private JToolBar ToolBar; 
    
    JTabbedPane TabbedPane;
            
    private JPanel PanelChiaTrang,PanelDinhThi,PanelCode,PanelTrangThai;
    
    // mang luu cac label hien thi mo phong
    private JLabel[] lbArrays;
    // label hien thi cac bien tam i
    private JLabel lbPoint1 = new JLabel();
    // label hien thi cac bien tam j
    private JLabel lbPoint2 = new JLabel();
    // label hine thi max min trong mo phong thuat toan
    private JLabel lbPointM = new JLabel();
     
    // # Khai báo các biến trong chương trình
    
    // bien luu so phan tu mang
    public int num;
    // mang int luu cac phan tu o nho
    private int[] array;
    // mang int luu cac phan tu frame
    private int[] frames;
    private int frame;
    // mang cac threads
    private Thread[] threads = new Thread[1000];
    // vi tri thread hien tai
    private int curT = -1;
    // thoi gian nghi, thuc thi
    private int time = 10;
    // thoi gian nghi, thuc thi mili giay
    // bien luu buoc thuc hien
    private int step = 0;	
    
    // file doc du lieu tu file
    private File file = new File ("src//array.txt");
    // bien toc do
    private float speed;
    
    
    // Khai báo các màu(Color)
    private final Color cl1 = new Color(255, 153, 153);
    private final Color cl2 = new Color(20, 153, 153);
    private final Color cl3 = new Color(20, 15, 255);
    private final Color cl4 = new Color(20, 255, 255);
    private final Color cl5 = new Color(20, 150, 255);
    private final Color cl6 = new Color(200, 15, 255);
    private final Color cl7 = new Color(200, 150, 150);
    private Color processingColor = new Color(255, 153, 153);
    // # Khai báo các biến Hàm sử lý sự kiện
    
    // su kien su ly cac radio button
    private ActionListener eInterchangeSort, eSelectionSort;
    private ActionListener nn,nm,mm;
    private ChangeListener eSize;
    
    // # Khai báo các màu(Color)
     
    //private final Color cl1 = new Color(255, 153, 153);  
    
    
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
        
        info.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent actionEvent) { InfoFrame f1 = new InfoFrame();
        f1.setVisible(true);
        } } );
        MenuBar.add(menuFile);
        MenuBar.add(menuEdit);
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
        btPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deleteArrays();
				//setState(0);
                                num=12;
                                frame=3;
                                array= new int[num];
                                array[0]=1;
                                array[1]=2;
                                array[2]=3;
                                array[3]=4;
                                array[4]=1;
                                array[5]=2;
                                array[6]=5;
                                array[7]=1;
                                array[8]=2;
                                array[9]=3;
                                array[10]=4;
                                array[11]=5;
                                frames= new int[frame];
                                lbArrays= new JLabel[num*(frame+2)];
                                FIFO();
			}
		});
        
        ImageIcon Icon2 = new ImageIcon(new ImageIcon("icon/pause-symbol.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btPause = new JButton(Icon2);
        btPause.setBorder(new EmptyBorder(7, 7, 7, 7));
        btPause.setText("Pause");
//        btPause.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//deleteArrays();
//				//setState(0);
//			}
//		});
        
        ImageIcon Icon3 = new ImageIcon(new ImageIcon("icon/a2.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton Button3 = new JButton(Icon3);
        Button3.setBorder(new EmptyBorder(7, 7, 7, 7));
        
        ImageIcon Icon4 = new ImageIcon(new ImageIcon("icon/a1.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JButton Button4 = new JButton(Icon4);
        Button4.setBorder(new EmptyBorder(7, 7, 7, 7));
        
        
        JButton Button5 = new JButton("Nhập dữ liệu");
        
        Button5.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent actionEvent) { InputFrame f2 = new InputFrame();
        f2.setVisible(true);
        } } );
        
        JButton Button6 = new JButton("Nhập bằng file");
        
        
        
        lb5 = new JLabel("  Tốc độ: ");
        lb5.setPreferredSize(new Dimension(100, 30));
        
        
        SpinnerModel value =  new SpinnerNumberModel(2, //initial value  
                0, //minimum value  
                5, //maximum value  
                0.5); //step  
        JSpinner spinner = new JSpinner(value); 
        //spinner.setSize(50, 30);
        spinner.setPreferredSize(new Dimension(50, 30));
        //spinner.setSize(50,40);
        //spinner.setBounds(100,100,50,30);    
         
        
        ToolBar.add(btPlay);
        ToolBar.add(btPause);
        ToolBar.add(Button3);
        ToolBar.add(Button4);
        ToolBar.add(Button5);
        ToolBar.add(Button6);
        ToolBar.add(lb5);
        ToolBar.add(spinner);
                

        
        
        
        ToolBar.setSize(800, 400);
        add(ToolBar,BorderLayout.NORTH);
    }
    
    // Pannel Mô phỏng thuật giải
    private void CreatePanelMoPhong()
    {
        TabbedPane = new JTabbedPane();
        TabbedPane.setBorder(BorderFactory.createLineBorder(Color.yellow));
        // panel mo phong thuat toan
        PanelChiaTrang = new JPanel();
        PanelChiaTrang.setBackground(Color.WHITE);
        PanelChiaTrang.setLayout(null); 
           
	JLabel 	lptt = new JLabel("Chọn thuật toán : ");
        lptt.setBounds(5, 10, 120, 20);
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
        
        JLabel 	lptd = new JLabel("Độ thu phóng : ");
        lptd.setBounds(320, 10, 120, 20);
        lptd.setBackground(Color.WHITE);
        PanelChiaTrang.add(lptd);
        
        lb2 = new JLabel("Độ thu phóng: ");
        PanelChiaTrang.add(lb2);
        
        SliderThuPhongMoPhong = new JSlider(JSlider.HORIZONTAL, 0, 100, 30); 
        SliderThuPhongMoPhong.setBounds(480, 10, 150, 20);
        SliderThuPhongMoPhong.setBackground(Color.WHITE);
        PanelChiaTrang.add(SliderThuPhongMoPhong);
        //JLabel[] label = new JLabel[5];
        //Object[][] objects = new Object[5][5];  
        
        // setPreferredSize() thiết lập lại kích thước đối tượng, không chịu ảnh hưởng của Layout bên ngoài
        PanelChiaTrang.setPreferredSize(new Dimension(900, 650));
        
        // panel dinh thi cpu
        PanelDinhThi = new JPanel();
        PanelDinhThi.setBackground(Color.WHITE);      
        PanelDinhThi.setPreferredSize(new Dimension(900, 650));
        
        TabbedPane.add("Chia trang ô nhớ", PanelChiaTrang);
        TabbedPane.add("Định thì CPU", PanelDinhThi);
        add(TabbedPane,BorderLayout.WEST);
    }
    
    // Pannel hiển thị Code
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
         }
      });
        

        add(PanelCode,BorderLayout.CENTER);
        
        pnScrollCode = new JScrollPane();
	pnScrollCode.setBounds(10, 50, 350, 530); // default 10, 53, 486, 223
	PanelCode.add(pnScrollCode);
	model = new DefaultListModel<>();
	lsCode = new JList<String>(model);
	lsCode.setBorder(new LineBorder(Color.cyan));
	lsCode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	lsCode.setFont(new Font("Monospaced",Font.BOLD,11));
	pnScrollCode.setViewportView(lsCode);
        
        model.removeAllElements();
        addFIFOcode();    
        lsCode.ensureIndexIsVisible(lsCode.getSelectedIndex());    
                         
    }
    
    // Thanh trạng thái của ứng dụng
    private void CreatePanelStatus()
    {
        PanelTrangThai = new JPanel();
        PanelTrangThai.setBackground(Color.WHITE);
        PanelTrangThai.setBorder(BorderFactory.createLineBorder(Color.red));
        PanelTrangThai.setSize(800, 100);
        lb1 = new JLabel("thanh trang thai");
        lb1.setBackground(Color.YELLOW);
        lb1.setSize(800,50);
        //Bt1.setPreferredSize(new Dimension(200,50));
        PanelTrangThai.add(lb1);
        add(PanelTrangThai,BorderLayout.SOUTH);
    }
     
    // # constructor cua lop MainFrame
    public MainFrame()
    {
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
    
    // set lock and feel
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
    
    
    // Hàm Main
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
            case 0:
                //button.setEnabled(false);
                //
                //
                break;
            case 1:
                //
                //
                //
                break;
            case 2:
                //
                //
                //
                break;
            case 3:
                //
                //
                //
                break;
            default:
                //
                ;
        }
    }
    
    // Khoi tao mang cac o nho
    public void TaoMang()
    {
        //
    }
    
    // xoa mang o nho
    public void XoaMang()
    {
        //
    }
    
    // tao mang o nho random
    public void TaoRanDomMang(int num, int max)
    {
        Random rand = new Random();
        for(int i = 0; i<num; i++)
        {
            int ranNum = rand.nextInt(max) + 0;
            //lbArrays[i].setText(String.valueOf(ranNum));
            //lbArrays[i].setForeground(Color.BLUE);
            //array[i] = ranNum;
        }
    }
    
    // tao Du lieu chay ben o code
    // thuat toan FIFO
    public void addFIFOcode()
    {
        model.addElement("void FIFO(int n, int frame, int *a, int *frames)");
        model.addElement("{");
        model.addElement("for (int i = 0; i<frame; i++)");
        model.addElement("frames[i] = -1;");
        model.addElement("int i, j = 0, k, available, count = 0;");
        model.addElement("printf( \" Chuoi \\t|Khung trang\");");
        model.addElement("for (int k = 0; k < frame - 1; k++)");
        model.addElement("printf( \"\\t\");");
        model.addElement("printf( \"|\\n\");");
        model.addElement("for (i = 1; i <= n; i++)");
        model.addElement("{");
        model.addElement("printf(\" %d\\t\", a[i]);");
        model.addElement("available = 0;");
        model.addElement("for (k = 0; k<frame; k++)");
        model.addElement("if (frames[k] == a[i])");
        model.addElement("available = 1; ");
        model.addElement("if (available == 0) ");
        model.addElement("{");
        model.addElement("frames[j] = a[i];");
        model.addElement("j = (j + 1) % frame;");
        model.addElement("count++;");
        model.addElement("printf( \"|\");");
        model.addElement("for (k = 0; k < frame; k++)");
        model.addElement("printf(\"%d\\t\", frames[k]);");
        model.addElement("printf( \"| F\");");
        model.addElement("}");
        model.addElement("else");
        model.addElement("{");
        model.addElement("printf( \"|\");");
        model.addElement("for (k = 0; k < frame; k++)");
        model.addElement("printf(\"%d\\t\", frames[k]);");
        model.addElement("printf( \"|\");");
        model.addElement("}");
        model.addElement("printf( \"\\n\");");
        model.addElement("}");
        model.addElement("printf( \"So trang loi la: %d\\n\", count);"); 
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
        
        int intx=450-42*(num/2);
        String str;
        for(int i=0; i<lbArrays.length; i++)
         {
             lbArrays[i]= new JLabel();
             lbArrays[i].setBounds(intx+42*(i%num), 50+42*(i/num), 40, 40);
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
     
       
        
        
        for (int i = 0; i<frame; i++)
		frames[i] = -1;
        for(int i=0;i<frame;i++)
        {
            lbArrays[num*(i+1)].setText(""+frames[i]);
        }
	int i, j = 0, k, available, count = 0;

	for (i = 0; i < num; i++)
	{
		available = 0; 
                //so sanh
                
		for (k = 0; k<frame; k++)
			if (frames[k] == array[i])
                        {
                            Move(lbArrays[i],lbArrays[i+num*k]);
                            available = 1; 
                            break;
                        }


		if (available == 0) 
		{
                        SetValue(lbArrays[i+num*1],frames[0]);
                        SetValue(lbArrays[i+num*2],frames[1]);
                        SetValue(lbArrays[i+num*3],frames[2]);
                        frames[j]=array[i];
			frames[j] = array[i];
                        Move(lbArrays[i],lbArrays[i+num*frame]);
                        SetF(lbArrays[i+num*(frame+1)]);
                        LightLabel(lbArrays[i+num*(j+1)]);
                        Move(lbArrays[i],lbArrays[i+num*(j+1)]);
                        LightLabel(lbArrays[i]);
                        SetValue(lbArrays[i],array[i]);
                        
                                
			j = (j + 1) % frame;
                        
			count++;
		}
		else
		{
                    SetValue(lbArrays[i+num*1],frames[0]);
                    SetValue(lbArrays[i+num*2],frames[1]);
                    SetValue(lbArrays[i+num*3],frames[2]);
                    Move(lbArrays[i],lbArrays[i+num*(k+1)]);
                    SetT(lbArrays[i+num*(frame+1)]);
		}
	}
	System.out.print( "So trang loi la: "+count+"\n");
         
     }
	public void Move(JLabel lb1, JLabel lb2) {
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
            }
     }               
                
}
