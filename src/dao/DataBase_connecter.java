package dao;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
  
final class DataBase_connecter{
    /**
	 * writen by recluse
	 */
	
    Connection con;  
      
    public DataBase_connecter(){
    	try {
		   	 String url="jdbc:mysql://localhost:3306/paper_plane";//�������ݿ��url
		   	 String user="root";//mysql��¼��
		   	 String pass="a444640050";//mysql��¼���루д�Լ�֮ǰ���õģ�
		   	 //�������ݿ���������������
		   	 Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection(url,user,pass); 
			 System.out.println("���ӳɹ�");  
		} catch (SQLException | ClassNotFoundException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}         
 
    }
    
    
    public Connection getCon() {
		return con;
	}


	public void setCon(Connection con) {
		this.con = con;
	}


	public void close()
    {
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
}  
