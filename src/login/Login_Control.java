package login;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.List;

import setting.Single_State;
import util.Exchange;

import javax.swing.*;

import dao.DB_Business;

 
final class Login_Control implements ActionListener {
    /**
	 * writen by recluse
	 */
    private JTextField accountT,passwordT;  
    private JButton okB,registB,noInternetB;  
    private Registration_Frame re;
      
    
    public void setAccountT(JTextField accountT) {
		this.accountT = accountT;
	}
	public void setPasswordT(JTextField passwordT) {
		this.passwordT = passwordT;
	}
	public void setButton(JButton b1,JButton b2,JButton b3){  
        okB = b1;  
        registB = b2;  
        noInternetB = b3;
    }  
      
    public void actionPerformed(ActionEvent e){  
        if(e.getSource() == okB){  
            if(this.accountT.getText().equals(""))           //判断用户输入是否为空；  
                JOptionPane.showMessageDialog(null, "请填写账号！");  
            else if(this.accountT.getText().length()>20)  
                JOptionPane.showMessageDialog(null,"请输入小于20个字符的账号"); 
            else if(this.passwordT.getText().equals(""))  
                JOptionPane.showMessageDialog(null, "请输入密码");  
            else if(this.passwordT.getText().length()>20)  
                JOptionPane.showMessageDialog(null,"请输入小于20个字符的密码"); 
            else{  
                try {
                    Exchange exc = new Exchange();
                    List<String> target = exc.strs_to_strlist(this.accountT.getText(),this.passwordT.getText());
                    DB_Business db_business = new DB_Business("Login");
                    List<String> result = db_business.execute(target);
                    if(result.get(0).equals("True"))//登陆成功就读取数据
                    {
                        JOptionPane.showMessageDialog(null, "登录成功");
                        Single_State.setLogin(true);
                        Single_State.setAccount(accountT.getText());
                        
                        db_business.business_select("GetPoint");
                        target = exc.strs_to_strlist(this.accountT.getText());
                        result = db_business.execute(target);
                        Single_State.setAll_point(Integer.parseInt(result.get(0)));
                    }                	
                    else{  
                        JOptionPane.showMessageDialog(null, "账号或密码不正确，请重新输入");  
                        this.accountT.setText("");  
                        this.passwordT.setText("");  
                    }  
                }   
                catch (Exception e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
        else if(e.getSource() == registB){  
            new JFrame().dispose();  
            re = new Registration_Frame();  
        }
        else if(e.getSource() == noInternetB)
        {
        	Single_State.setNoInternet(true);
        	Single_State.setLogin(true);
        }
    }  
      
}  