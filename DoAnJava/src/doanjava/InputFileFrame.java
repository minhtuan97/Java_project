/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

/**
 *
 * @author SiVai
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author SiVai
 */


public class InputFileFrame extends JFrame 
{
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField filepath;
    private int frame = 0, num = 0;
    private int[] arrays;
    private JButton btOK, btPath; 
    private JFileChooser  fileDialog;
    private FileNameExtensionFilter filter;

    public InputFileFrame()
    {
        setTitle("Nhập dữ liệu từ File");
        setResizable(false);
	setSize(500,220);
        setLocationRelativeTo(null);
        
	contentPane = new JPanel();
	contentPane.setBackground(SystemColor.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
		
	JLabel lb1 = new JLabel("Chọn đường dẫn File :");
	lb1.setBounds(10, 10, 450, 25);
	contentPane.add(lb1);
        
        filepath = new JTextField();
        filepath.setBounds(10, 40, 450, 25);
        filepath.setEditable(false);
        contentPane.add(filepath);
        
        JLabel lb2 = new JLabel("file đọc có định dạng .txt\n");
	lb2.setBounds(20, 70, 450, 25);
	contentPane.add(lb2);
        JLabel lb3 = new JLabel("Định dạng lưu như sau :\n");
	lb3.setBounds(20, 95, 450, 25);
	contentPane.add(lb3);
        JLabel lb4 = new JLabel("       Dòng 1 lưu số khung trang");
	lb4.setBounds(20, 120, 450, 25);
	contentPane.add(lb4);
        JLabel lb5 = new JLabel("       Dòng 2 lưu chuỗi tham chiếu, mỗi phần tử chuỗi cách nhau bởi khoản trắng");
        lb5.setBounds(20, 145, 450, 25);
	contentPane.add(lb5);
        
        btPath = new JButton("Chọn File");
	btPath.setBackground(SystemColor.activeCaption);
        btPath.setBounds(380, 10, 80, 25);
	contentPane.add(btPath);
        btPath.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {  
                fileDialog = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                fileDialog.setFileFilter(filter);
		int returnValue = fileDialog.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) 
                {
                    File selectedFile = fileDialog.getSelectedFile();
                    filepath.setText(selectedFile.getAbsolutePath());
		}
            }
        });
        
        btOK = new JButton("Xác nhận");
	btOK.setBackground(SystemColor.activeCaption);
        btOK.setBounds(280, 10, 80, 25);
	contentPane.add(btOK);
        btOK.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {  
                ReadFile();     
            }
        });
        
    }
    
    public void ReadFile()
    {
        num = 0;
        frame = 0;
        arrays = null;
        String path = filepath.getText().toString();
        if(path.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn File!");
        }
        else
        {
            try
            {
                File f = new File(path);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                if((line = br.readLine()) != null)
                {
                    try 
                    {  
                        frame = Integer.parseInt(line); 
                    }
                    catch (NumberFormatException e) 
                    {
                        JOptionPane.showMessageDialog(this, "File không đúng định dạng!"); 
                        return;
                    }             
                }
                if((line = br.readLine()) != null)
                {                                 
                    try 
                    {  
                        String[] aa = line.split("\\s");
                        // đếm số phần tử chuỗi
                        for (String s1 : aa)
                        {
                            if(s1 != null)
                            {
                                Integer.parseInt(s1);
                                num = num + 1;
                            }
                        }
                        // lưu chuỗi vào mảng
                        arrays = new int[num];
                        //System.out.println(num);
                        int i=0;
                        for (String s1 : aa)
                        {
                            if(s1 != null)
                            {
                                arrays[i] = Integer.parseInt(s1);
                                //System.out.println(arrays[i]);
                                i++;
                            }
                        }
                    }
                    catch (NumberFormatException e) 
                    {
                        JOptionPane.showMessageDialog(this, "Định dạng nội dụng file không đúng!"); 
                        return;
                    }
                    
                }
                fr.close();
                br.close();
                Frame[] listFrames = Frame.getFrames();
                ((MainFrame) listFrames[0]).setData(arrays,num,frame);
                JOptionPane.showMessageDialog(this, "Đã tạo dữ liệu mảng thành công!");
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); 
            }            
            catch(IOException exc)
            {
                JOptionPane.showMessageDialog(this, "Lỗi đọc file!"+exc);
                return;
            }   
        }
    }  


    
}
