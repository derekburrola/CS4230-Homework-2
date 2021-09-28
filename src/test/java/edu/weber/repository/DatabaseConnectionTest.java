package edu.weber.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mysql.cj.jdbc.MysqlDataSource;


@RunWith(MockitoJUnitRunner.class)
public class DatabaseConnectionTest {

	@Mock 
	DataSource dataSource;
	
	@Mock
	MysqlDataSource source;
	
	DatabaseConnection conn;

	
	@Before
	public void setup() {
		conn = new DatabaseConnection();
	}
	
	@Test
	public void testGetURLStringFormat() {
		String expected = "jdbc:mysql://localhost:2525/megadb";
		String url = "localhost:2525";
		String schema = "megadb";
		
		
		String result = conn.getMySQLUrl(url, schema);
		Assert.assertEquals(result, expected);
	}
	
	@Test
	public void testGetURLString() {
		String expected = "jdbc:mysql://localhost:2525/megadb";
		String url = "jdbc:mysql://localhost:2525/megadb";
		String schema = "megadb";
		
		
		String result = conn.getMySQLUrl(url, schema);
		Assert.assertEquals(result, expected);
	}

	@Test
	public void testGetDataSource() throws SQLException {
		
		source.setDatabaseName("cs4230");
		source.setUrl("localhost:3306");
		source.setUser("cs4230-user");
		source.setPassword("password");
		
		
		//when(conn.getSQLSource(ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class))).thenReturn(source);
		//when(dataSource.getConnection().isValid(5)).thenReturn(true);
		conn.getDataSource();
		Assert.assertEquals(dataSource, null);
		//PowerMockito.mockStatic(System.class);
	}
	
	@Test
	public void testDatabaseFails() {
		try {
			DatabaseConnection.getDataSource();
		}catch(Exception e) {
			Assert.assertTrue(true);
		}
	}
}


