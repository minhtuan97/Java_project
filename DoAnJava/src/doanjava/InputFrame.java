/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

import java.awt.EventQueue;
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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author SiVai
 */


public class InputFrame extends JFrame  implements ItemListener
        
        
{
   
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JSpinner spNum,spFrame,spTrang;
    private JSpinner[] txtArrays;
    private JLabel[] lbArrays;
    private JCheckBox cbRandom;
    private int num = 0,frame = 0, Trang = 0;
    private int[] arrays;
    private int[] frames;
    private JButton btnOK,btReset,btTaoMang;    


    public InputFrame()
    {
        setTitle("Nhập dữ liệu");
        setResizable(false);
	setSize(500,400);
        setLocationRelativeTo(null);
        
	contentPane = new JPanel();
	contentPane.setBackground(SystemColor.menu);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
		
	JLabel lb1 = new JLabel("Nhập số trang (<10):");
	lb1.setBounds(10, 10, 150, 25);
	contentPane.add(lb1);
        
        JLabel lb2 = new JLabel("Nhập số phần tử mảng (<20):");
	lb2.setBounds(10, 40, 150, 25);
	contentPane.add(lb2);
        
        JLabel lb3 = new JLabel("Nhập số khung trang (<20):");
	lb3.setBounds(10, 70, 150, 25);
	contentPane.add(lb3);
        
        SpinnerModel sm1 = new SpinnerNumberModel(2, 1, 10, 1);
	spTrang = new JSpinner(sm1);
	JFormattedTextField txt3 = ((JSpinner.NumberEditor) spTrang.getEditor()).getTextField();
	((NumberFormatter) txt3.getFormatter()).setAllowsInvalid(true);
	spTrang.setBounds(180, 10, 80, 25);
	contentPane.add(spTrang);	
		
	SpinnerModel sm2 = new SpinnerNumberModel(2, 1, 20, 1);
	spNum = new JSpinner(sm2);
	JFormattedTextField txt1 = ((JSpinner.NumberEditor) spNum.getEditor()).getTextField();
	((NumberFormatter) txt1.getFormatter()).setAllowsInvalid(true);
	spNum.setBounds(180, 40, 80, 25);
	contentPane.add(spNum);	
        
        SpinnerModel sm3 = new SpinnerNumberModel(2, 1, 20, 1);
	spFrame = new JSpinner(sm3);
	JFormattedTextField txt2 = ((JSpinner.NumberEditor) spFrame.getEditor()).getTextField();
	((NumberFormatter) txt2.getFormatter()).setAllowsInvalid(true);
	spFrame.setBounds(180, 70, 80, 25);
	contentPane.add(spFrame);	
        
        btReset = new JButton("Đặt lại");
	btReset.setBackground(SystemColor.activeCaption);
        btReset.setBounds(380, 10, 80, 25);
	contentPane.add(btReset);
	
        
        cbRandom = new JCheckBox("Ngẫu nhiên",false);
        cbRandom.setBounds(290, 10, 80, 25);
        contentPane.add(cbRandom);
        cbRandom.addItemListener(this);
        
        
	btTaoMang = new JButton("Tạo Mảng");
	btTaoMang.setBackground(SystemColor.activeCaption);
        btTaoMang.setBounds(290, 40, 170, 25);
	contentPane.add(btTaoMang);
        
	btTaoMang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createArray();
			}
		});
	
		
	btnOK = new JButton("Xác nhận");
        btnOK.setBackground(SystemColor.activeCaption);
	btnOK.setBounds(290, 70, 170, 25);
	contentPane.add(btnOK);
	btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beAccepted();
			}
		});
        
    }
	
	public void createArray() {
		deleteArrays();
		num = (Integer)spNum.getValue();
		arrays = new int[num];
		lbArrays = new JLabel[num];
		txtArrays = new JSpinner[num];
		arrays = new int[num];
		
		for (int i = 0; i < num; i++) {
			lbArrays[i] = new JLabel("A[" + (i+1) + "]:");
			SpinnerModel smValue = new SpinnerNumberModel(0, 0, 20, 1);
			txtArrays[i] = new JSpinner(smValue);
			JFormattedTextField txt = ((JSpinner.NumberEditor) txtArrays[i].getEditor()).getTextField();
			((NumberFormatter) txt.getFormatter()).setAllowsInvalid(true);
			contentPane.add(lbArrays[i]);
			contentPane.add(txtArrays[i]);
			lbArrays[i].setSize(40,30);
			if (i == 0 || i == 5 || i == 10 || i == 15) 
                                lbArrays[i].setLocation(10, 100+ 40*(i+1)/3);
                           
			else
				lbArrays[i].setLocation(lbArrays[i-1].getX()+95, lbArrays[i-1].getY());
			
                        int rand = rand(0, 100);
                        // gia tri cua cac phan tu
                        txtArrays[i].setSize(40,30);
			txtArrays[i].setLocation(lbArrays[i].getX() + 40, lbArrays[i].getY());// toa do x cua lbArrays[i] cach txtArrays la 40
                        
                        if(cbRandom.isSelected() == true)
                            txtArrays[i].setValue(rand);
                        
                    
                        
		}
		contentPane.setVisible(true);
		contentPane.validate();
		contentPane.repaint();
                    btReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < num; i++) {
                                    txtArrays[i].setValue(0);
                                }
                               
			}
		});
                
	}
        
        // ham bat su kien cho Checkbox Random. (Phai co)
        @Override
        public void itemStateChanged(ItemEvent e){
         
        }
        // ham tao random
        public static int rand(int min, int max)
        {
            try
            {
                Random rn = new Random();
                int range = max - min + 1;
                int randomNum = min + rn.nextInt(range);
                return randomNum;       
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return -1;
            }
         }
        
	public void deleteArrays() {
		for (int i = 0; i < num; i++) {
			lbArrays[i].setVisible(false);
			txtArrays[i].setVisible(false);
			contentPane.remove(lbArrays[i]);
			contentPane.remove(txtArrays[i]);
		}
	}
	
	public void beAccepted() {
		for (int i = 0; i < num; i++) {
			arrays[i] = (int) txtArrays[i].getValue();
			System.out.println(arrays[i]);	
		}
		Frame[] listFrames = Frame.getFrames();
		//((FormMain) listFrames[0]).setArray(arrays);
		
		if (num != 0) {
			JOptionPane.showMessageDialog(this, "Đã tạo dữ liệu mảng thành công!\nChuẩn bị thoát!");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else {
			JOptionPane.showMessageDialog(this, "Chưa tạo được dữ liệu mảng!\nChuẩn bị thoát!");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
			
	}
        
    // tra du lieu ve frame chinh
    
    //tra ve so phan tu chuoi tham chieu
    public int getNum()
    {
	return num;
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

    // tra ve so khung trang
    public int getFrame()
    {
	return frame;
    }
    
    // tra ve mang tham chieu
    public int[] getArrays()
    {
        return arrays;
    }
    
    // tra ve mang frame ban dau
    public int[] getFrames1()
    {
        return frames;
    }

}