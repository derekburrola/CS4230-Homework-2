package edu.weber.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseConnection {

	private static final String MYSQL_URL_KEY = "MYSQL_URL";
	private static final String MYSQL_USER_KEY = "MYSQL_USER";
	private static final String MYSQL_PASSWORD_KEY = "MYSQL_PASSWORD";
	private static final String MYSQL_DATABASE_KEY = "MYSQL_DATABASE";

	
	
	private static DataSource dataSource;
	
	public DatabaseConnection() {
		 
	}
	
	public static DataSource getDataSource() {
		if(null == dataSource) {
			// Create data source
			String url = System.getenv(MYSQL_URL_KEY);
			String schema = System.getenv(MYSQL_DATABASE_KEY);
			
			MysqlDataSource source = new MysqlDataSource();
			source.setDatabaseName(schema);
			source.setUrl(getMySQLUrl(url, schema));
			source.setUser(System.getenv(MYSQL_USER_KEY));
			source.setPassword(System.getenv(MYSQL_PASSWORD_KEY));
			
			dataSource = source;
			
			// Attempt connection
			try {
				dataSource.getConnection().isValid(5);
			}
			catch (SQLException e){
				e.printStackTrace();
				throw new RuntimeException("Failed to connect to the database.");
			}
		}
		return dataSource;
	}
	
	
	/*
	 * Returns the sql url based on the existing url type
	 * */
	protected static String getMySQLUrl(String url, String schema) {		
		if(url.startsWith("jdbc:mysql")) {
			return url;
		} else {
			String newURL = String.format("%s%s/%s", "jdbc:mysql://", url, schema); 
			return newURL;
		}
	}
}
