package dao;

import java.sql.SQLException;
import java.util.List;

public final class DB_Business {
	Business business;
	
	public DB_Business(String command)
	{
		this.business_select(command);
	}
	
	public void business_select(String command)
	{
		if(command == "RegisterNewAccount")
			this.business = new Business_RegisterNewAccount();
		else if(command == "Login")
			this.business = new Business_Login();
		else if(command == "GetPoint")
			this.business = new Business_GetPoint();
		else if(command == "UpdatePoint")
			this.business = new Business_UpdatePoint();
		else if(command == "GetAllNote")
			this.business = new Business_GetAllNote();
		else if(command == "GetMyRank")
			this.business = new Business_GetMyRank();
		else if(command == "UpdateOpinion")
			this.business = new Business_UpdateOpinion();
	}
	
	public List<String> execute(List<String> target) throws SQLException
	{
		return this.business.implement(target);
	}
}
