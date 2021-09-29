package edu.weber.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import edu.weber.repository.DatabaseConnection;
import jakarta.servlet.ServletContextEvent;

import static org.mockito.Mockito.verify;

import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;

@RunWith(MockitoJUnitRunner.class)
public class FlywayServletTest {
	
	@Mock 
	DatabaseConnection db;
	
	@Mock
	FlywayServletListener fly;
	
	@Mock 
	ServletContextEvent sce;
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void testFlyway() {
	}
	
	@Test
	public void passTest() {
		Assert.assertEquals(true, true);
	}
}
