package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

final class Business_Login implements Business{
	@Override
	public List<String> implement(List<String> target) throws SQLException {//用户输入检查 
		List<String> result = new ArrayList<String>();
		String sql;
		DataBase_connecter db = new DataBase_connecter();
		Connection con = db.getCon();  
		Statement stmt = con.createStatement();
		sql = "select * from my where account=\""+target.get(0).toString()+"\" and password = \""+target.get(1)+"\"";  
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			result.add("True");
		}
		else
			result.add("False");
		
		
	    stmt.close();
	    con.close();
	    db.close();
		return result;
	}  
}

