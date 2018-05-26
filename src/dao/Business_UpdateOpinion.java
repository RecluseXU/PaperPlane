package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

public class Business_UpdateOpinion implements Business {

	@Override
	public List<String> implement(List<String> target) throws SQLException {//传入意见
		// TODO 自动生成的方法存根
	    String sql;  
	    DataBase_connecter db = new DataBase_connecter();
	    Connection con = db.getCon();  
	    Statement stmt = con.createStatement();     
	    
	    //插入存文本框中获取的数据
	    sql = "insert into opinion(opinion) values(\""+target.get(0).toString()+"\")"; 
	    int rw = stmt.executeUpdate(sql);
	    if(rw <= 0)             //判断数据是否插入成功  
	        JOptionPane.showMessageDialog(null, "上传意见失败");  
	    else
	        JOptionPane.showMessageDialog(null, "上传意见成功");
	    stmt.close();
	    con.close();
	    db.close();
	    return null;
	}

}
