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
			MysqlDataSource source = new MysqlDataSource();
			source.setDatabaseName(System.getenv(MYSQL_DATABASE_KEY));
			source.setUrl(getMySQLUrl());
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
	protected static String getMySQLUrl() {
//		String url = System.getenv(MYSQL_URL_KEY);
//		String schema = System.getenv(MYSQL_DATABASE_KEY);
		
		
		String url = getEnv(MYSQL_URL_KEY);
		String schema = getEnv(MYSQL_DATABASE_KEY);
		
		if(url.startsWith("jdbc:mysql")) {
			System.out.println(url);
			return url;
		} else {
			String newURL = String.format("%s%s/%s", "jdbc:mysql://", url, schema); 
			return newURL;
		}
	}
	
	public static String getEnv(String s) {
		return System.getenv(s);
	}
	
	public static Map<String,String> getEnvironmentVariables(){
		Map<String,String> m = new HashMap<String,String>();
		
		m.put(MYSQL_URL_KEY, System.getenv(MYSQL_URL_KEY));
		m.put(MYSQL_USER_KEY, System.getenv(MYSQL_USER_KEY));
		m.put(MYSQL_PASSWORD_KEY, System.getenv(MYSQL_PASSWORD_KEY));
		m.put(MYSQL_DATABASE_KEY, System.getenv(MYSQL_DATABASE_KEY));
		
		return m;
	}
}
