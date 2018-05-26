package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

final class Business_GetAllNote implements Business{//获取用户名与分数的记录

	@Override
	public List<String> implement(List<String> target) throws SQLException {
		List<String> result = new ArrayList<String>();
		String sql;
		DataBase_connecter db = new DataBase_connecter();
		Connection con = db.getCon();  
		Statement stmt = con.createStatement();
		sql = "SELECT account,POINT FROM my ORDER BY POINT DESC LIMIT 10";  
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{	
			result.add(rs.getString("account")+" "+rs.getString("point"));
		}
	    stmt.close();
	    con.close();
	    db.close();
		return result;
	}

}
