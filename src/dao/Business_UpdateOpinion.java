package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

public class Business_UpdateOpinion implements Business {

	@Override
	public List<String> implement(List<String> target) throws SQLException {//�������
		// TODO �Զ����ɵķ������
	    String sql;  
	    DataBase_connecter db = new DataBase_connecter();
	    Connection con = db.getCon();  
	    Statement stmt = con.createStatement();     
	    
	    //������ı����л�ȡ������
	    sql = "insert into opinion(opinion) values(\""+target.get(0).toString()+"\")"; 
	    int rw = stmt.executeUpdate(sql);
	    if(rw <= 0)             //�ж������Ƿ����ɹ�  
	        JOptionPane.showMessageDialog(null, "�ϴ����ʧ��");  
	    else
	        JOptionPane.showMessageDialog(null, "�ϴ�����ɹ�");
	    stmt.close();
	    con.close();
	    db.close();
	    return null;
	}

}
