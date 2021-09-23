package edu.weber.servlet;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

import edu.weber.repository.DatabaseConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class FlywayServletListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		DatabaseConnection dbc = (DatabaseConnection) DatabaseConnection.getDataSource();
		Flyway flyway = Flyway.configure().dataSource((DataSource) dbc).load();
		System.out.println("Starting FlyWay Migration");
		flyway.migrate();
		System.out.println("Finished FlyWay Migration");

	}


}
