package com.creatio.crm.framework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	// This class is intended to contain common methods for database operations
	static Properties prop = PropUtil.readData("Config.properties");
	
	//Common method to connect to the database and get the raw data
	public static ResultSet getData(String query) throws SQLException {
		ResultSet resultSet = null;		
		Connection connection = DriverManager.getConnection(prop.getProperty("DB_URL") , prop.getProperty("DB_USER"), prop.getProperty("DB_PASS"));
		resultSet =connection.createStatement().executeQuery(query);
		return resultSet;		
	}

}
