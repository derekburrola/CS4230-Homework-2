package edu.weber.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class DatabaseConnectionTest {

	DatabaseConnection conn;

	
	@Before
	public void setup() {
		conn = new DatabaseConnection();
	}
	
	
	@Test
	public void getUrlStringTest() {
		
		//PowerMockito.mockStatic(System.class);
		
		try {
			
			//when(conn2.getEnv("MYSQL_URL")).thenReturn("localhost:3306");
			//when(conn2.getEnv("MYSQL_DATABASE")).thenReturn("cs4230");
			//when(conn.getEnv("MYSQL_URL")).thenReturn("localhost:3306");
			//when(conn.getEnv("MYSQL_DATABASE")).thenReturn("cs4230");
		} catch(Exception e) {
			e.printStackTrace();
//			System.out.println();
			
		}
		
		
		String expected = "jdbc:mysql://localhost:3306/cs4230";

		String s = conn.getMySQLUrl();
		System.out.println(s);
		Assert.assertEquals(s, expected);
	}
	

	@Test
	public void passTest() {

		Assert.assertEquals(true, true);
	}
}


