package edu.weber.servlet;

import org.flywaydb.core.Flyway;

import edu.weber.repository.DatabaseConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class FlywayServletListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		Flyway flyway = Flyway.configure().dataSource(DatabaseConnection.getDataSource()).load();
		System.out.println("Starting FlyWay Migration");
		flyway.migrate();
		System.out.println("Finished FlyWay Migration");

	}


}
