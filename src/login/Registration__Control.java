package login;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.sql.ResultSet;  
import java.sql.Statement;
import java.util.List;

import javax.swing.*;

import dao.DB_Business;
import util.Exchange;  
  
final class Registration__Control implements ActionListener{  
    /**
	 * writen by recluse
	 */
    JTextField textacc,textpassword;  
    JButton okButton,resetButton;  
    Statement stmt;  
    ResultSet rs;   
      
    public void setaccountField(JTextField a){  
        textacc = a;  
    }
    public void setTextpasswordField(JTextField textpassword) {
		this.textpassword = textpassword;
	}
	public void setokButton(JButton b1){  
        okButton = b1;  
    }  
    public void setresetButton(JButton b2){  
        resetButton = b2;  
    }  
      
    public void actionPerformed(ActionEvent e){  
        if(e.getSource() == okButton){  
            if(textacc.getText().equals(""))            //�ж��û������Ƿ�Ϊ�գ�  
                JOptionPane.showMessageDialog(null, "�������˺�","����Ի���",JOptionPane.WARNING_MESSAGE);  
            else if(textacc.getText().length()>20)  
                JOptionPane.showMessageDialog(null,"������С��20���ַ����˺�","����Ի���",JOptionPane.WARNING_MESSAGE); 
            else if(textpassword.getText().equals(""))  
                JOptionPane.showMessageDialog(null,"����������","����Ի���",JOptionPane.WARNING_MESSAGE); 
            else if(textpassword.getText().length()>20)  
                JOptionPane.showMessageDialog(null,"������С��20���ַ�������","����Ի���",JOptionPane.WARNING_MESSAGE); 
            else{  
                String acc = textacc.getText();  
                String password = textpassword.getText();  
                try {  
                    DB_Business db_bussiness = new DB_Business("RegisterNewAccount");
                    Exchange exchange = new Exchange();
                    List<String> target = exchange.strs_to_strlist(acc,password);
                    db_bussiness.execute(target);
                    
                } catch (Exception e1) {  
                    System.out.println("����ʧ��");  
                    e1.printStackTrace();  
                }  
            }  
        }  
        else if(e.getSource() == resetButton){  
            textacc.setText("");  
            textpassword.setText("");  
        }  
    }  
      
}  