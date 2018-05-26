package login;

import javax.swing.JFrame;

import setting.Single_screen_resolution;

import java.awt.FlowLayout;  
import javax.swing.*;  
  
final class Registration_Frame extends JFrame{  //此注册页面采用Box布局方式；  
    /**
	 * writen by recluse
	 */
	private JLabel accountLabel,nameLabel;  
    private JButton okButton,resetButton;  
    private JTextField accountText,nameText;     
      
    Registration__Control regist;  
      
    Registration_Frame(){  
        init();  
    }  
      
      
    void init(){  
        setLayout(new FlowLayout());  
        accountLabel = new JLabel("账号");  
        nameLabel= new JLabel("密码");  
        accountText = new JTextField(10);  
        nameText = new JTextField(20);  
        okButton = new JButton("确定");  
        resetButton = new JButton("重置");  
          
        regist = new Registration__Control();  
          
          
        Box box1 = Box.createVerticalBox();  
        box1.add(accountLabel);  
        box1.add(Box.createVerticalStrut(8));  
        box1.add(nameLabel);  
        Box box2 = Box.createVerticalBox();  
        box2.add(accountText);  
        box2.add(Box.createVerticalStrut(8));  
        box2.add(nameText);  
        Box box3 = Box.createHorizontalBox();  
        box3.add(okButton);  
        box3.add(Box.createHorizontalStrut(15));  
        box3.add(resetButton);  
        Box baseBox1 = Box.createHorizontalBox();  
        baseBox1.add(box1);  
        baseBox1.add(Box.createHorizontalStrut(8));  
        baseBox1.add(box2);  
        Box baseBox2 = Box.createVerticalBox();  
        baseBox2.add(baseBox1);  
        baseBox2.add(Box.createVerticalStrut(10));  
        baseBox2.add(box3);  
        add(baseBox2);  
          
        okButton.addActionListener(regist);  
        resetButton.addActionListener(regist);  
          
          
        regist.setaccountField(accountText);  
        regist.setTextpasswordField(nameText);  
        regist.setokButton(okButton);  
        regist.setresetButton(resetButton);  
        
        setBounds(Single_screen_resolution.getSr_width()/3,Single_screen_resolution.getSr_height()/3,Single_screen_resolution.getSr_width()/3,Single_screen_resolution.getSr_height()/3);  
        setVisible(true);  
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        setTitle("用户注册界面");  
    }  
      
      
      

}  
