package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;
final class Business_RegisterNewAccount implements Business{

	public List<String> implement(List<String> target)
	{
		try {
		    String sql;  
		    DataBase_connecter db = new DataBase_connecter();
		    Connection con = db.getCon();  
		    Statement stmt = con.createStatement();     
		    
		    //������ı����л�ȡ�����ݣ�  
		    sql = "insert into my(account,password,point,unpoint) values('"+target.get(0).toString()+"','"+target.get(1).toString()+"','"+"0"+"','"+"0"+"')";  
		    int rw = stmt.executeUpdate(sql);
		    if(rw <= 0)             //�ж������Ƿ����ɹ�  
		        JOptionPane.showMessageDialog(null, "ע��ʧ��");  
		    else
		        JOptionPane.showMessageDialog(null, "ע��ɹ�");
		    stmt.close();
		    con.close();
		    db.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
		}
	    return null;
	}


	
}
