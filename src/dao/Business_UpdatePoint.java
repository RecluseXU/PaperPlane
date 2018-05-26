package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import setting.Single_State;

final class Business_UpdatePoint implements Business{

	@Override
	public List<String> implement(List<String> target) throws SQLException {
        String sql;
        DataBase_connecter db = new DataBase_connecter();
        Connection con = db.getCon();
        Statement stmt = con.createStatement();  
        
        sql = "update my set point=\""+target.get(0)+"\" where account=\""+Single_State.getAccount()+"\"";    //ÒªÖ´ÐÐµÄSQL
        stmt.executeUpdate(sql);
	    stmt.close();
	    con.close();
	    db.close();
        return null;
	}

}
