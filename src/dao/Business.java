package dao;

import java.sql.SQLException;
import java.util.List;

interface Business {
	List<String> implement(List<String> target) throws SQLException;
}
