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
		    
		    //插入存文本框中获取的数据；  
		    sql = "insert into my(account,password,point,unpoint) values('"+target.get(0).toString()+"','"+target.get(1).toString()+"','"+"0"+"','"+"0"+"')";  
		    int rw = stmt.executeUpdate(sql);
		    if(rw <= 0)             //判断数据是否插入成功  
		        JOptionPane.showMessageDialog(null, "注册失败");  
		    else
		        JOptionPane.showMessageDialog(null, "注册成功");
		    stmt.close();
		    con.close();
		    db.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
		}
	    return null;
	}


	
}
