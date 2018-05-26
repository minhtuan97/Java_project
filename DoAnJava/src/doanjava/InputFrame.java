/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author SiVai
 */


public class InputFrame extends JFrame implements ItemListener
{
    
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JSpinner spNum;
    private final JSpinner spFrame;
    private final JSpinner spTrang;
    private final JCheckBox cbRandom;
    private int num = 0,frame = 0, Trang = 0;
    public static int[] arrays; 
    public static int[] arraySaveData;
    private JLabel[] lbArrays;
    private JSpinner[] InputArrays;
    private final JButton btnOK; 
    private final JButton btReSet; 
    private final JButton btTaoMang;
    private final SpinnerModel sm1;
    private final SpinnerModel sm2;
    private final SpinnerModel sm3;
    


    public InputFrame()
    {
        setTitle("Nhập dữ liệu");
        setResizable(false);
	setSize(500,400);
        setLocationRelativeTo(null);
        
	contentPane = new JPanel();
	contentPane.setBackground(SystemColor.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
		
	JLabel lb1 = new JLabel("Nhập số trang (<=10):");
	lb1.setBounds(10, 10, 150, 25);
	contentPane.add(lb1);
        
        JLabel lb2 = new JLabel("Nhập số phần tử mảng (<=20):");
	lb2.setBounds(10, 40, 170, 25);
	contentPane.add(lb2);
        
        JLabel lb3 = new JLabel("Nhập số khung trang (<=6):");
	lb3.setBounds(10, 70, 150, 25);
	contentPane.add(lb3);
        sm1 = new SpinnerNumberModel(2, 1, 10, 1);
	spTrang = new JSpinner(sm1);
	JFormattedTextField txt3 = ((JSpinner.NumberEditor) spTrang.getEditor()).getTextField();
	((NumberFormatter) txt3.getFormatter()).setAllowsInvalid(true);
	spTrang.setBounds(180, 10, 80, 25);
	contentPane.add(spTrang);	
		
	sm2 = new SpinnerNumberModel(2, 1, 20, 1);
	spNum = new JSpinner(sm2);
	JFormattedTextField txt1 = ((JSpinner.NumberEditor) spNum.getEditor()).getTextField();
	((NumberFormatter) txt1.getFormatter()).setAllowsInvalid(true);
	spNum.setBounds(180, 40, 80, 25);
	contentPane.add(spNum);	
        
        sm3 = new SpinnerNumberModel(2, 1, 6, 1);
	spFrame = new JSpinner(sm3);
	JFormattedTextField txt2 = ((JSpinner.NumberEditor) spFrame.getEditor()).getTextField();
	((NumberFormatter) txt2.getFormatter()).setAllowsInvalid(true);
	spFrame.setBounds(180, 70, 80, 25);
	contentPane.add(spFrame);	
        
        btReSet = new JButton("Đặt lại");
	btReSet.setBackground(SystemColor.activeCaption);
        btReSet.setBounds(380, 10, 80, 25);
	contentPane.add(btReSet);
        btReSet.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
		for(int i = 0;i<num;i++){
                    InputArrays[i].setValue(0);
                }
            }
	});
        
        cbRandom = new JCheckBox("Ngẫu nhiên",false);
        cbRandom.setBounds(290, 10, 80, 25);
        contentPane.add(cbRandom);	
        cbRandom.addItemListener(this);
        
	btTaoMang = new JButton("Tạo Mảng");
	btTaoMang.setBackground(SystemColor.activeCaption);
        btTaoMang.setBounds(290, 40, 170, 25);
	contentPane.add(btTaoMang);
        btTaoMang.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
		CreateArray();
            }
	});
        
		
	btnOK = new JButton("Xác nhận");
        btnOK.setBackground(SystemColor.activeCaption);
	btnOK.setBounds(290, 70, 170, 25);
	contentPane.add(btnOK);
	btnOK.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                OK();
            }
	});
        
    }
	
    public void CreateArray() 
    {
        ResetArray();
        num = (Integer)spNum.getValue();
        frame = (Integer)spFrame.getValue();
        Trang = (Integer)spTrang.getValue();
        lbArrays = new JLabel[num];
        InputArrays = new JSpinner[num];   
        
        for (int i = 0; i < num; i++) 
        { 
           
            lbArrays[i] = new JLabel("A[" + (i+1) + "]:");
   
            SpinnerModel smValue = new SpinnerNumberModel(0,0,20,1);
            InputArrays[i] = new JSpinner(smValue);
            JFormattedTextField txt = ((JSpinner.NumberEditor) InputArrays[i].getEditor()).getTextField();
            ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(true);
            contentPane.add(lbArrays[i]);
            contentPane.add(InputArrays[i]);
            lbArrays[i].setSize(40,30);
            
            
            if (i == 0 || i == 5 || i == 10 || i == 15) 
                    lbArrays[i].setLocation(10, 100+ 40*(i+1)/3);
            else
                    lbArrays[i].setLocation(lbArrays[i-1].getX()+95, lbArrays[i-1].getY());
            InputArrays[i].setSize(40,30);
            InputArrays[i].setLocation(lbArrays[i].getX() + 40, lbArrays[i].getY());
           // arraySaveData[i] = (int) InputArrays[i].getValue();
            
            int rand = rand(0, 100);
           if(cbRandom.isSelected()==true)
                InputArrays[i].setValue(rand);
                
        }
        contentPane.setVisible(true);
        contentPane.validate();
        contentPane.repaint();
    }

    // ham bat su kien cho Checkbox Random. (Phai co)
    @Override
       public void itemStateChanged(ItemEvent e){         
        }
    //tao mang random cho mang
    public static int rand(int min, int max){
        try{
            Random rn = new Random();
            int range = max - min + 1;
            int randomNum = min + rn.nextInt(range);
            return randomNum;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    public void ResetArray() 
    {
        if(lbArrays != null )
        for( JLabel aa :lbArrays)
        {
            contentPane.remove(aa);
        }
        if(InputArrays != null)
        for( JSpinner bb : InputArrays)
        {
            contentPane.remove(bb);
        }
        contentPane.repaint();
        num = 0;
        frame = 0;
        Trang = 0;
        lbArrays = null;
        InputArrays = null;    
    }

    public void OK() 
    {
        
        
        if (num != 0) 
        {
            arrays = new int[num];
            for (int i = 0; i < num; i++) 
            {
                arrays[i] = (int) InputArrays[i].getValue();      
            }
            
            Frame[] listFrames = Frame.getFrames();
            //((MainFrame) listFrames[0]).setData(arrays,num,frame);
            
            JOptionPane.showMessageDialog(this, "Đã tạo dữ liệu mảng thành công!");
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            
        }
        else 
        {
            JOptionPane.showMessageDialog(this, "Chưa tạo được dữ liệu mảng!");
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        MainFrame mf = new MainFrame();
        mf.setData(arrays);
    }
}