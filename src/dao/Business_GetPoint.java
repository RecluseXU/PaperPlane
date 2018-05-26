package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

final class Business_GetPoint implements Business{

	@Override
	public List<String> implement(List<String> target) throws SQLException {
		List<String> result = new ArrayList<String>();
		String sql;
		DataBase_connecter db = new DataBase_connecter();
		Connection con = db.getCon();  
		Statement stmt = con.createStatement();
		sql = "select * from my where account=\""+target.get(0)+"\"";  
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next())
		{	
			String k = rs.getString("point");
			if(k == null)
				result.add("0");
			else
				result.add(k);
		}
		
	    stmt.close();
	    con.close();
	    db.close();
		return result;
	}

}
