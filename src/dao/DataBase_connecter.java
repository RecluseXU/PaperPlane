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
		   	 String url="jdbc:mysql://localhost:3306/paper_plane";//连接数据库的url
		   	 String user="root";//mysql登录名
		   	 String pass="a444640050";//mysql登录密码（写自己之前设置的）
		   	 //加载数据库连接驱动并连接
		   	 Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection(url,user,pass); 
			 System.out.println("连接成功");  
		} catch (SQLException | ClassNotFoundException e1) {
			// TODO 自动生成的 catch 块
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    }
}  
