/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

import java.awt.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.*;
import javax.swing.event.*;

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
    private final ButtonGroup grSort;
    // Các nút trên thanh toolbar
    private final JButton btPlay, btPause, btRefresh, btInputData, btInputFile, btDel, btShow;
    // doi tuong luu buoc chay cac dong code
    private DefaultListModel<String> model;
    // list string hien thi code
    private JList<String> lsCode;
    // biến thanh menu bar
    private final JMenuBar MenuBar;
    // slider tốc độ , và thu phóng
    private final JSlider SliderTocDoMoPhong,SliderThuPhongCode;
    // thanh cuon khung code
    private final JScrollPane pnScrollCode; 
    // các nút radio chọn thuật toán
    private JRadioButton rdFIFO, rdOPT, rdLRU;
    // Thanh toolbar
    private final JToolBar ToolBar; 
    // TabbedPane 
    private final JTabbedPane TabbedPane;
    // các panel chính        
    private final JPanel PanelChiaTrang, PanelMoPhong ,PanelCode,PanelTrangThai;
    // mang luu cac label hien thi mo phong
    private JLabel[] lbArray, lbArray_Frame, lbArray_Error ;
    private JLabel[] lbArray_FrameSS, STT;
    // label hien thi cac bien tam i
    private JLabel status = new JLabel("");
    // label hien thi cac bien tam i,j,a,k,count;
    private JLabel lbI, lbI1, lbai, lbframe, lbloitrang, lbJ, lbViTriThayThe, lbViTriThayThe1, lbTonTai;
    // # Khai báo các biến trong chương trình
    // bien luu so phan tu mang
    public int num = 0, frame = 0;
    // mang int luu cac phan tu o nho
    private int[] array, frames;    
    // mang cac threads
    private Thread[] threads = new Thread[10000];
    // vi tri thread hien tai mặc định =-1
    private int curT = -1;
    // thoi gian nghi, thuc thi mili giay
    private int time = 35;
    //ket qua loi trang
    int count;     
     
    public MainFrame()
    {
	setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setTitle("Đồ Án Java");
        setSize(1280,720);
        try 
        {           
            Image logo = ImageIO.read(new File("icon/2.jpeg"));
            setIconImage(logo);  
        } 
        catch (IOException ex) {} 
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        setResizable(false);
        ////////////////////////////////////////////////////////////////////////
        // tạo thanh menu
        MenuBar = new JMenuBar();
        JMenu menuHelp = new JMenu("Help");  
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
        }); 
        MenuBar.add(menuHelp);
        MenuBar.add(menuAbout);       
        setJMenuBar(MenuBar);
        ///////////////////////////////////////////////////////////////////////
        // Tạo thanh công cụ
        ToolBar = new JToolBar();
        ToolBar.setFloatable(false);
        ToolBar.setBackground(Color.white);   
        ToolBar.setLayout(new FlowLayout(0, 0, 0));    
        ToolBar.setBorder(BorderFactory.createLineBorder(Color.PINK));
        ImageIcon Icon1 = new ImageIcon(new ImageIcon("icon/play-sign.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btPlay = new JButton(Icon1);
        btPlay.setBorder(new EmptyBorder(7, 7, 7, 7));
        btPlay.setText("Play");
        btPlay.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {                    
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
                XoaKhungMoPhong();
                if(num !=0)
                {
                   HienKhungMoPhong(); 
                }
                
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
                InputFrame f2 = new InputFrame();
                f2.setVisible(true);
            } 
        });       
        ImageIcon Icon5 = new ImageIcon(new ImageIcon("icon/txt.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btInputFile = new JButton(Icon5);
        btInputFile.setBorder(new EmptyBorder(7, 7, 7, 7));
        btInputFile.setText("Nhập bằng file");
        btInputFile.addActionListener( new ActionListener()
        { 
            public void actionPerformed(ActionEvent actionEvent) 
            { 
                InputFileFrame f3 = new InputFileFrame();
                f3.setVisible(true);
            } 
        }); 
        ImageIcon Icon6 = new ImageIcon(new ImageIcon("icon/hide.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btDel = new JButton(Icon6);
        btDel.setBorder(new EmptyBorder(7, 7, 7, 7));
        btDel.setText("Delete");
        btDel.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                XoaKhungMoPhong();
                num = 0;
            }
	}); 
        ImageIcon Icon7 = new ImageIcon(new ImageIcon("icon/eye.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        btShow = new JButton(Icon7);
        btShow.setBorder(new EmptyBorder(7, 7, 7, 7));
        btShow.setText("Show");
        btShow.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                if(num !=0)
                {
                   HienKhungMoPhong(); 
                }
            }
	}); 
        ToolBar.add(btRefresh);
        ToolBar.add(btPlay);
        ToolBar.add(btPause);    
        ToolBar.addSeparator();
        ToolBar.add(btDel);
        ToolBar.add(btShow);
        ToolBar.addSeparator();
        ToolBar.add(btInputData);
        ToolBar.add(btInputFile);      
        ToolBar.setSize(800, 400);
        add(ToolBar,BorderLayout.NORTH); 
        ////////////////////////////////////////////////////////////////////////
        // thêm Pannel chính hiển thị đồ họa
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
        SliderTocDoMoPhong.addChangeListener(new ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) 
            {
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
        PanelChiaTrang.setPreferredSize(new Dimension(900, 650));
        PanelMoPhong = new JPanel();
        PanelMoPhong.setPreferredSize(new Dimension(898, 650));
        PanelMoPhong.setBounds(3, 50, 892, 510);
        PanelMoPhong.setBorder(BorderFactory.createLineBorder(Color.red));
        PanelMoPhong.setBackground(Color.WHITE);
        PanelMoPhong.setLayout(null);
        PanelChiaTrang.add(PanelMoPhong);
        
        TabbedPane.add("Thuật toán Chia trang ô nhớ", PanelChiaTrang);
        add(TabbedPane,BorderLayout.WEST);
        ////////////////////////////////////////////////////////////////////////
        // tạo pannel hiển thị code
        PanelCode = new JPanel();
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
        SliderThuPhongCode.setBounds(120, 20, 150, 23);
        SliderThuPhongCode.setBackground(Color.WHITE);
        PanelCode.add(SliderThuPhongCode);
        
        SliderThuPhongCode.addChangeListener(new ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) 
            {
                lsCode.setFont(new Font("Monospaced",Font.BOLD,((JSlider)e.getSource()).getValue()));
                lb2.setText(((JSlider)e.getSource()).getValue() +" pixels");
                lsCode.repaint();
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
        ////////////////////////////////////////////////////////////////////////
        // thêm thanh trạng thái
        PanelTrangThai = new JPanel();
        PanelTrangThai.setBackground(Color.WHITE);
        PanelTrangThai.setBorder(BorderFactory.createLineBorder(Color.red));
        PanelTrangThai.setPreferredSize(new Dimension(800,30));
        JLabel Copyright = new JLabel("Copyright © 2018"); 
        Copyright.setHorizontalAlignment(SwingConstants.CENTER);
        Copyright.setVerticalAlignment(SwingConstants.CENTER);
        Copyright.setBackground(Color.WHITE);
        Copyright.setOpaque(true);
        PanelTrangThai.add(Copyright);  
        add(PanelTrangThai,BorderLayout.SOUTH);
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
        EventQueue.invokeLater( new Runnable() 
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
                btPlay.setEnabled(false);
                btPause.setEnabled(false);
                btRefresh.setEnabled(true);
                btInputData.setEnabled(true);
                btInputFile.setEnabled(true);
                rdFIFO.setEnabled(true);
                rdLRU.setEnabled(true);
                rdOPT.setEnabled(true);
                break;
            case 1:// trạng thái sau khi nhập dữ liệu
                btPlay.setEnabled(true);
                btPause.setEnabled(false);
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
                btRefresh.setEnabled(true);
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
    public void addFIFOcode()
    {
        model.addElement("void FIFO( n, frame, a[], frames[])           ");//0
        model.addElement("{                                             ");//1
        model.addElement("  for (int i = 0; i < frame; i++)             ");//2
        model.addElement("      frames[i] = -1;                         ");//3
        model.addElement("  int VTThayThe = -1;                         ");//4
        model.addElement("  for (int i = 0; i < num; i++)               ");//5
        model.addElement("  {                                           ");//6
        model.addElement("      SwapFrame(i);                           ");//7
        model.addElement("      int TonTai = 0;                         ");//8
        model.addElement("      for (int k = 0; k < frame; k++)         ");//9
        model.addElement("          if (frames[k] == array[i])          ");//10
        model.addElement("              TonTai = 1;                     ");//11
        model.addElement("      if (TonTai == 0)                        ");//12
        model.addElement("      {                                       ");//13
        model.addElement("          VTThayThe=(VTThayThe+1)%frame;      ");//14
        model.addElement("          frames[ViTriThayThe] = array[i];    ");//15
        model.addElement("          SetFalse(i);                        ");//16
        model.addElement("      }                                       ");//17
        model.addElement("      SwapFrame(i);                           ");//18
        model.addElement("  }                                           ");//19
        model.addElement("}                                             ");//20
    }    
    public void addOPTcode()
    {
        model.addElement("void OTP( n, frame, a[], frames[])            ");//0
        model.addElement("{                                             ");//1
        model.addElement("  for (int i = 0; i < frame; i++)             ");//2
        model.addElement("      frames[i] = -1;                         ");//3
        model.addElement("  int VTThayThe = -1;                         ");//4
        model.addElement("  for (int i = 0; i < num; i++)               ");//5
        model.addElement("  {                                           ");//6
        model.addElement("      SwapFrame(i);                           ");//7
        model.addElement("      int TonTai = 0;                         ");//8
        model.addElement("      for (int k = 0; k < frame; k++)         ");//9
        model.addElement("          if (frames[k] == array[i])          ");//10
        model.addElement("              TonTai = 1;                     ");//11
        model.addElement("      if (TonTai == 0)                        ");//12
        model.addElement("      {                                       ");//13
        model.addElement("          VTThayThe=TimViTriOPT();            ");//14
        model.addElement("          frames[ViTriThayThe] = array[i];    ");//15
        model.addElement("          SetFalse(i);                        ");//16
        model.addElement("      }                                       ");//17
        model.addElement("      SwapFrame(i);                           ");//18
        model.addElement("  }                                           ");//19
        model.addElement("}                                             ");//20
        model.addElement("int TimViTriLRU(i, n, frame, a[], frames[])   ");//21
        
        
    }  
    public void addLRUcode()
    {
        model.addElement("void LRU( n, frame, a[], frames[])            ");//0
        model.addElement("{                                             ");//1
        model.addElement("  for (int i = 0; i < frame; i++)             ");//2
        model.addElement("      frames[i] = -1;                         ");//3
        model.addElement("  int VTThayThe = -1;                         ");//4
        model.addElement("  for (int i = 0; i < num; i++)               ");//5
        model.addElement("  {                                           ");//6
        model.addElement("      SwapFrame(i);                           ");//7
        model.addElement("      int TonTai = 0;                         ");//8
        model.addElement("      for (int k = 0; k < frame; k++)         ");//9
        model.addElement("          if (frames[k] == array[i])          ");//10
        model.addElement("              TonTai = 1;                     ");//11
        model.addElement("      if (TonTai == 0)                        ");//12
        model.addElement("      {                                       ");//13
        model.addElement("          VTThayThe=TimViTriLRU();            ");//14
        model.addElement("          frames[ViTriThayThe] = array[i];    ");//15
        model.addElement("          SetFalse(i);                        ");//16
        model.addElement("      }                                       ");//17
        model.addElement("      SwapFrame(i);                           ");//18
        model.addElement("  }                                           ");//19
        model.addElement("}                                             ");//20
        model.addElement("int TimViTriLRU(i,n,frame,a[],frames[])       ");//21
    }    
    // hiển thị số lỗi trang và thông báo sap xep xong 
    public void Result()
    {
        curT ++;
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
                    //
                    
                    Thread.sleep(time);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
        JOptionPane.showMessageDialog(this, "Thực hiện xong!");
    }
    // di chuyển label 1 sang 2 theo chiều dọc: di chuyển lb1 đến vị trí lb2
    public void MoveHorizontal(JLabel[] lb1, int i, JLabel lb2) 
    {
        curT ++;
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
                    
                    if(lb1[i].getX()<lb2.getX())
                        while (lb1[i].getX() < lb2.getX()) 
                        {
                            
                            
                            //lb2.setOpaque(false);
                            for(int z = 0; z < frame+1; z++)
                            {        
                                lb1[z*num+i].setLocation(lb1[z*num+i].getX()+1, lb1[z*num+i].getY());
                            }  
                            PanelChiaTrang.repaint();
                            Thread.sleep(400);
                        }
                    if(lb1[i].getX()>lb2.getX())
                        while (lb1[i].getY() > lb2.getY()) 
                        {
                            //lb2.setOpaque(false);
                             for(int z = 0; z < frame+1; z++)
                            {      
                                lb1[z*num+i].setLocation(lb1[z*num+i].getX()-1, lb1[z*num+i].getY());
                            }    
                            PanelChiaTrang.repaint();
                            Thread.sleep(400);
                        }
                    Thread.sleep(3*time);
                    lb1[i].setLocation(lb2.getX(), lb2.getY());
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }    
    // di chuyển label 1 sang 2 theo chiều ngang
    public void MoveVertical(JLabel lb1, JLabel lb2) 
    {
        curT ++;
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
                    if(lb1.getX()<lb2.getX())
                        while (lb1.getX() < lb2.getX()) 
                        {
                            //lb2.setOpaque(false);
                            lb1.setLocation(lb1.getX()+1, lb1.getY());
                            Thread.sleep(time);
                        }
                    if(lb1.getX()>lb2.getX())
                        while (lb1.getX() > lb2.getX()) 
                        {
                            //lb2.setOpaque(false);
                            lb1.setLocation(lb1.getX()-1, lb1.getY());
                            Thread.sleep(time);
                        }
                    Thread.sleep(3*time);
                    lb1.setLocation(lb2.getX(), lb2.getY());
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    } 
    // tô màu ô bị thay thế
    public void LightLabel(JLabel lb, int i)
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
                    switch(i)
                    {
                        case -1: lb.setBackground(Color.WHITE);break;
                        case 0: lb.setBackground(Color.YELLOW);break;
                        case 1: lb.setBackground(Color.GREEN); Thread.sleep(1500);break;
                    }
                    
                    Thread.sleep(500);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    } 
    // đặt faile cho trang bị lỗi ô nhớ
    public void SetFalse(JLabel lb)
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
                    lb.setText("F");
                    lbViTriThayThe1.setVisible(false);
                    Thread.sleep(1000);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }   
    // đặt giá trị cho vị trí frame thay thế
    public void SetValue(JLabel lb, int value)
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
                    lb.setText(""+value);
                    Thread.sleep(500);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    } 
    // đặt giá trị vị trí label theo i, s
    public void SetlbI(int i) 
    {
        curT ++;
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
                        threads[cur - 1].join();
                    }   
                    lbI.setText("i="+i);
                    lbI.setBounds(lbArray[i].getX(), lbArray[i].getY()-30, 32, 25);
                    lbI1.setText("i="+i);
                    lbI1.setBounds(lbArray_FrameSS[i].getX(), lbArray_FrameSS[0].getY()-30,32,25);
                    Thread.sleep(1000);
                } catch (Exception e){}
            }
        });
        threads[cur].start();
    }
    
    // đặt giá trị vị trí label theo i, s
    public void SetlbTonTai(int i) 
    {
        curT ++;
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
                        threads[cur - 1].join();
                    }
                    
                    switch(i)
                    {
                        case -2: lbTonTai.setText("kiểm tra a[i] ??? ");break;
                        case -1:  lbTonTai.setText("Không tồn tại  ");break;
                        default: lbTonTai.setText("Tồn tạo tại : "+i+"  ");break;
                    }
                   
                        
                    //lbTonTai.setBounds(40, lbArray_FrameSS[frame].getY()+32, 100, 25);
                    Thread.sleep(2000);
                } catch (Exception e){}
            }
        });
        threads[cur].start();
    }
    
    // đặt giá trị vị trí label theo i, s
    public void SetlbTrangThai(String s, int i) 
    {
        curT ++;
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
                        threads[cur - 1].join();
                    }   
                    if(i==-1)
                    {
                        status.setText("gdgdg");
                    }
                    else
                    {
                        status.setText("Nhiệm vụ : "+s);
                    }
                    
                    //lbTonTai.setBounds(40, lbArray_FrameSS[frame].getY()+32, 100, 25);
                    Thread.sleep(1000);
                } catch (Exception e){}
            }
        });
        threads[cur].start();
    }
    
    
    // đặt giá trị vị trí label theo i, s
    public void SetlbVTTT(int i) 
    {
        curT ++;
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
                        threads[cur - 1].join();
                    }
                    if(i!=-1)
                    {
                        lbViTriThayThe1.setVisible(true);
                        lbViTriThayThe1.setBounds(55, lbArray_FrameSS[i+1].getY(), 32, 32);
                    }
                    
                    Thread.sleep(1000);
                } catch (Exception e){}
            }
        });
        threads[cur].start();
    }
    // dđặt vị trí của biến J di chuyển theo chiều dọc
    public void SetlbJ(JLabel lb, int i) 
    {
        curT ++;
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
                        threads[cur - 1].join();
                    }   
                    lb.setText("i="+i);
                    lb.setHorizontalAlignment(SwingConstants.CENTER);
                    lb.setVerticalAlignment(SwingConstants.CENTER);
                    lb.setBackground(Color.YELLOW);
                    lb.setBounds(lbArray[i].getX()-50, lbArray[i].getY()+ 34*i, 32, 25);
                    Thread.sleep(300);
                } catch (Exception e){}
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
                threads[i].resume();
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
    public void HienKhungMoPhong()
    {   
        // tính tọa độ x bắt đầu vẽ để khung hiển thị chính giữa
        int intx = 200+ 350-34*(num/2);
        
        this.frames             = new int[frame];
        this.lbArray            = new JLabel[num];
        this.lbArray_Frame      = new JLabel[num*(this.frame+1)];
        this.lbArray_Error      = new JLabel[num];
        // khai báo các biến tạm
        
        // label biến tạm i của vòng for
        lbI = new JLabel();
        lbI.setHorizontalAlignment(SwingConstants.CENTER);
        lbI.setVerticalAlignment(SwingConstants.CENTER);
        lbI.setBackground(Color.YELLOW);
        lbI.setOpaque(true);
        PanelMoPhong.add(lbI);
        
        lbI1 = new JLabel();
        lbI1.setHorizontalAlignment(SwingConstants.CENTER);
        lbI1.setVerticalAlignment(SwingConstants.CENTER);
        lbI1.setBackground(Color.YELLOW);
        lbI1.setOpaque(true);
        PanelMoPhong.add(lbI1);
        
        status = new JLabel();
        status.setHorizontalAlignment(SwingConstants.LEFT);
        status.setVerticalAlignment(SwingConstants.CENTER);
        status.setBackground(Color.WHITE);
        //status.setFont(new Font(20)); new font
        status.setBorder(BorderFactory.createLineBorder(Color.GREEN)); 
        status.setOpaque(true);
        status.setBounds(10, 10, 800, 30);
        PanelMoPhong.add(status);
        
        // label biến tạm j của vòng for
        lbJ = new JLabel();
        lbJ.setHorizontalAlignment(SwingConstants.CENTER);
        lbJ.setVerticalAlignment(SwingConstants.CENTER);
        lbJ.setBackground(Color.YELLOW);
        PanelMoPhong.add(lbJ);
        
        lbViTriThayThe = new JLabel("Vị trí Thay trang");    
        lbViTriThayThe.setHorizontalAlignment(SwingConstants.CENTER);
        lbViTriThayThe.setVerticalAlignment(SwingConstants.CENTER);
        lbViTriThayThe.setBackground(Color.YELLOW);
        lbViTriThayThe.setOpaque(true);
        lbViTriThayThe.setBounds(10, 150, 85, 32);
        PanelMoPhong.add(lbViTriThayThe);
        
        lbTonTai = new JLabel("");
        lbTonTai.setHorizontalAlignment(SwingConstants.RIGHT);
        lbTonTai.setVerticalAlignment(SwingConstants.CENTER);
        lbTonTai.setBackground(Color.GREEN);
        //lbTonTai.setBounds(10, 150, 120, 32);
        lbTonTai.setOpaque(true);
        
        PanelMoPhong.add(lbTonTai);
        
        lbViTriThayThe1 = new JLabel("X"); 
        lbViTriThayThe1.setHorizontalAlignment(SwingConstants.CENTER);
        lbViTriThayThe1.setVerticalAlignment(SwingConstants.CENTER);
        lbViTriThayThe1.setBackground(Color.YELLOW);
        lbViTriThayThe1.setOpaque(true);
        PanelMoPhong.add(lbViTriThayThe1);       
        
        // hiển thị mảng frames
        for(int i = 0; i< num*(frame+1); i++ )
        {
            lbArray_Frame[i]= new JLabel();
            lbArray_Frame[i].setBounds(intx+34*(i%num), 150+34*(i/num), 32, 32);
            lbArray_Frame[i].setHorizontalAlignment(SwingConstants.CENTER);
            lbArray_Frame[i].setVerticalAlignment(SwingConstants.CENTER);
            lbArray_Frame[i].setBackground(Color.WHITE);
            if((i/num) == 0)
            {
               lbArray_Frame[i].setText(""+array[i]);
               lbArray_Frame[i].setBorder(BorderFactory.createLineBorder(Color.GREEN)); 
            }
            else
            {
                lbArray_Frame[i].setBorder(BorderFactory.createLineBorder(Color.black));
            }
            lbArray_Frame[i].setOpaque(true);
            PanelMoPhong.add(lbArray_Frame[i]);
            
        }
        // hiển thị mảng lỗi trang
        for(int i=0;i<num;i++)
        {    
            lbArray_Error[i]= new JLabel();
            lbArray_Error[i].setText("");
            lbArray_Error[i].setBounds(intx + 34*(i%num),150 + 34*(frame+1) , 32, 32);
            lbArray_Error[i].setHorizontalAlignment(SwingConstants.CENTER);
            lbArray_Error[i].setVerticalAlignment(SwingConstants.CENTER);
            lbArray_Error[i].setBorder(BorderFactory.createLineBorder(Color.blue));
            lbArray_Error[i].setBackground(Color.WHITE);
            lbArray_Error[i].setOpaque(true);
            PanelMoPhong.add(lbArray_Error[i]);      
        }
        
        // Hiển thị mảng tham chiếu ban đầu
        for(int i = 0; i < num ;i++)
        {  
            lbArray[i] = new JLabel();
            lbArray[i].setText(""+array[i]);
            lbArray[i].setHorizontalAlignment(SwingConstants.CENTER);
            lbArray[i].setVerticalAlignment(SwingConstants.CENTER);
            lbArray[i].setBounds(intx+34*(i%num), 150+34*(i/num), 32, 32);
            lbArray[i].setBorder(BorderFactory.createLineBorder(Color.RED));
            lbArray[i].setBackground(Color.WHITE);
            lbArray[i].setOpaque(true);
            PanelMoPhong.add(lbArray[i]);
            
        }
        // khai báo frame vị trí so sánh
        lbArray_FrameSS = new JLabel[frame+1];
        for(int i = 0; i < frame+1 ;i++)
        {  
            lbArray_FrameSS[i] = new JLabel();
            lbArray_FrameSS[i].setHorizontalAlignment(SwingConstants.CENTER);
            lbArray_FrameSS[i].setVerticalAlignment(SwingConstants.CENTER);
            lbArray_FrameSS[i].setBounds(100, 150+34*i, 32, 32);
            lbArray_FrameSS[i].setBorder(BorderFactory.createLineBorder(Color.RED));
            lbArray_FrameSS[i].setBackground(Color.WHITE);
            lbArray_FrameSS[i].setOpaque(true);
            PanelMoPhong.add(lbArray_FrameSS[i]);      
        }
        
        // khai báo frame vị trí so sánh
        STT = new JLabel[frame];
        for(int i = 0; i < frame ;i++)
        {  
            STT[i] = new JLabel(""+i);
            STT[i].setHorizontalAlignment(SwingConstants.CENTER);
            STT[i].setVerticalAlignment(SwingConstants.CENTER);
            STT[i].setBounds(10, 150+34*(i+1), 32, 32);
            STT[i].setBorder(BorderFactory.createLineBorder(Color.RED));
            STT[i].setBackground(Color.WHITE);
            STT[i].setOpaque(true);
            PanelMoPhong.add(STT[i]);      
        }
        lbTonTai.setBounds(10, lbArray_FrameSS[frame].getY()+34, 120, 32);
        PanelChiaTrang.repaint();
    }   
    // hàm xóa khung mô phỏng
    public void XoaKhungMoPhong() 
    {
        PanelMoPhong.removeAll();
        // xóa tất cả các threads đang chạy
        StopAllThreads(); 
        // vẽ lại panel
        PanelChiaTrang.revalidate();
        PanelChiaTrang.repaint();
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
                    lsCode.ensureIndexIsVisible(line); 
                    
                    Thread.sleep(500);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }   
    // hàm lấy dữ liệu từ frame Input
    public void setData(int[] array, int frame)
    {
        XoaKhungMoPhong();
        // đặt lại giá trị, khởi tạo
        this.num                = array.length;
        this.frame              = frame;
        this.array              = new int[num];
        System.arraycopy( array, 0, this.array, 0, array.length );
        HienKhungMoPhong();
    }
    
    // di chuyển frame đang so sanh ra vị trí so sánh nếu x= -1 ; ngược lại x=1;
    public void SwapFrame(int i, int x) 
    {
        curT ++;
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
                    Thread.sleep(500);
                    if(x==-1)
                    while(lbArray_Frame[i].getX() > lbArray_FrameSS[0].getX())
                    {
                        for(int z=0;z<frame+1;z++)
                        {
                            lbArray_Frame[z*num+i].setLocation(lbArray_Frame[z*num+i].getX()- 1, lbArray_FrameSS[z].getY());
                        }
                        Thread.sleep(30);
                    }
                    if(x==1)
                    while(lbArray_Frame[i].getX() < 200+ 350-34*(num/2)+34*(i%num))
                    {
                        for(int z=0;z<frame+1;z++)
                        {
                            lbArray_Frame[z*num+i].setLocation(lbArray_Frame[z*num+i].getX() + 1, lbArray_FrameSS[z].getY());                           
                        }
                        Thread.sleep(30);
                    }
                   
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }    
    
    // di chuyển frame tìm kiếm ; với x là vị trí tồn tại -1, 0, 1, 2,....
    public void SwapFrameTimKiem(int i, int x) 
    {
        curT ++;
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
                    Thread.sleep(500);
                    if(x==-1)
                    {
                        while(lbArray_Frame[i].getY() < lbArray_FrameSS[frame].getY())
                        {
                            lbArray_Frame[i].setLocation(lbArray_Frame[i].getX(), lbArray_Frame[i].getY()+ 1);
                            Thread.sleep(50);
                        }
                    }
                    else
                    {
                        while(lbArray_Frame[i].getY() < lbArray_FrameSS[x+1].getY())
                        {
                            lbArray_Frame[i].setLocation(lbArray_Frame[i].getX(), lbArray_Frame[i].getY()+ 1);
                            Thread.sleep(50);
                        }
                    }
                    Thread.sleep(300);
                    // đưa lbArray_Frame[i] về vị trí cũ
                    lbArray_Frame[i].setLocation(lbArray_FrameSS[0].getX(), lbArray_FrameSS[0].getY());  
                   
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }   
    
     // di chuyển frame thay thế trang
    public void SwapFrameThayThe(int i, int vttt) 
    {
        curT ++;
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
                    Thread.sleep(500);
                    while(lbArray_Frame[i].getY() < lbArray_FrameSS[vttt+1].getY())
                    {
                        lbArray_Frame[i].setLocation(lbArray_Frame[i].getX(), lbArray_Frame[i].getY()+ 1);
                        Thread.sleep(50);
                    }
                    Thread.sleep(300);
                    // đưa lbArray_Frame[i] về vị trí cũ
                    lbArray_Frame[i].setLocation(lbArray_FrameSS[0].getX(), lbArray_FrameSS[0].getY());  
                   
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }   
    
    public void FIFO()
    {   
        HighLight(1);
        // gán giá trị frame khởi đầu = -1 tương đương rỗng
        SetlbTrangThai("khởi tạo fram ban đầu", 0);     
        for (int i = 0; i < frame; i++)
        {
            HighLight(2);
            frames[i] = -1;
            SetValue( lbArray_Frame[(i+1)*num], -1 );
            HighLight(3);
        }	
        HighLight(4);	
	int ViTriThayThe = -1;
        // khởi tạo vị trí thay thế ban đầu = 0;
        SetlbTrangThai("Khởi tạo vị trí thay thế ban đầu = -1", 0);
        SetlbVTTT(ViTriThayThe);
	for (int i = 0; i < num; i++)
	{
            HighLight(5);
            // gán frame mới bằng frame cũ lbArray_Frame[ 
            if(i<(num))
            {
                for(int k=0;k<frame;k++)
                    SetValue( lbArray_Frame[(k+1)*num+i], frames[k] );
            }
            // gán biến I 
            SetlbTrangThai("Kiểm tra thực hiện vòng lặp", 0);
            SetlbI(i);
            SetlbTrangThai("Chuyển frame ra vị trí so sánh", 0);
            HighLight(7);
            SwapFrame(i,-1);
            // kiểm tra a[i] đã tồn tại trong frame chưa
            int TonTai = 0;
            SetlbTonTai(-2);
            SetlbTrangThai("Kiểm tra a[i] có tồn tại trong frames", 0);
            int temp = -1;
            for (int k = 0; k < frame; k++)
            {
                HighLight(9);
                if (frames[k] == array[i])
                {    
                    TonTai = 1;
                    SwapFrameTimKiem(i,k); 
                    HighLight(10);
                    SetlbTonTai(k);
                    LightLabel(lbArray_Frame[i+num*(k+1)], 1);
                    temp=k;
                    break;
                }
                
            }
            if(temp!=-1)
            LightLabel(lbArray_Frame[i+num*(temp+1)], -1);
            if(TonTai==0)
            {
                SwapFrameTimKiem(i,-1);
                SetlbTonTai(-1);
            }

            
            HighLight(12);
            SetlbTrangThai("Nếu a[i] không tồn tại thì thay thế trang", 0);
            if (TonTai == 0) 
            {
                
                SetlbTrangThai("Tìm, tính vị trí thay thế trang", 0);
                HighLight(14);    
                // tính lại vị trí thay thế
                ViTriThayThe = (ViTriThayThe + 1) % frame;
                SetlbVTTT(ViTriThayThe);
                // swap frame[ThayThe] = a[i] 
                HighLight(15); 
                frames[ViTriThayThe] = array[i];
                SetlbTrangThai("Thay thế trang ô nhớ", 0);
                SwapFrameThayThe(i,ViTriThayThe);
                SetValue( lbArray_Frame[(ViTriThayThe+1)*num+i], array[i] );
                LightLabel(lbArray_Frame[(ViTriThayThe+1)*num+i],0);
                   
                // đánh dấu lỗi trang tại frame-error[i];
                SetlbTrangThai("Đánh dấu lỗi trang", 0);
                HighLight(16);
                SetFalse(lbArray_Error[i]);
            }
            // chuyển frame so sánh về vị trí cũ
            SetlbTrangThai("Chuyển frame về vị trí cũ", 0);
            HighLight(18);
            SwapFrame(i,1);
            
            PanelMoPhong.repaint();
            HighLight(19);
        }
        HighLight(20);
        SetlbTrangThai("Hoàn thành mô phỏng", 0);
    }            
}             
                
