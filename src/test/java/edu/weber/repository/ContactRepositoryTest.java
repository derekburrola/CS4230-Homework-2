package edu.weber.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import edu.weber.model.Contact;
import edu.weber.model.Address;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;

import org.mockito.ArgumentMatchers;

@RunWith(MockitoJUnitRunner.class)
public class ContactRepositoryTest {

	@Test
	public void passTest() {

		Assert.assertEquals(true, true);
	}
	ContactRepository repo;

	@Mock
	private DataSource datasource;

	@Mock
	private Connection db;

	@Mock
	private PreparedStatement stmt;

	@Mock
	private ResultSet rs;

	@Mock
	private Contact cont;



	// ArgumentMatchers.any(String.class
	@Before
	public void setup() throws Exception {
		assertNotNull(datasource);
		when(db.prepareStatement(any(String.class))).thenReturn(stmt);
		when(datasource.getConnection()).thenReturn(db);


		cont = new Contact();
		cont.setFirstName("Derek");
		cont.setLastName("Burrola");


		when(rs.getString("firstName")).thenReturn(cont.getFirstName());
		when(rs.getString("lastName")).thenReturn(cont.getLastName());
		when(rs.getString("phoneNumber")).thenReturn("555-555-5555");
		when(stmt.executeQuery()).thenReturn(rs);
	}

	@Test
	public void testGetContacts() throws SQLException {



		Collection<Contact> result = repo.getAllContacts();
		Assert.assertEquals(result, null);
	}

	@Test 
	public void testGetAllContactsEmpty() throws SQLException {
		//when(contactRepo.getAllContacts()).thenReturn(null);

		when(rs.getString("firstName")).thenReturn("Derek");
		when(rs.getString("lastName")).thenReturn("Derek");
		when(rs.getString("phoneNumber")).thenReturn("555-555-5555");

		when(rs.getString("address1")).thenReturn("Rainbow Road");
		when(rs.getString("address2")).thenReturn("");
		when(rs.getString("city")).thenReturn("Mushroom Kingdom");
		when(rs.getString("state")).thenReturn("N64");
		when(rs.getString("zipCode")).thenReturn("83443");
		when(rs.getString("addressType")).thenReturn("Work");

		Collection<Contact> result = repo.getAllContacts();
		Assert.assertEquals(result, null);
	}
	
	
	@Test
	public void testAddAddres() throws SQLException {
		Address a = new Address();
		when(stmt.executeUpdate()).thenReturn(1);
		when(this.db.prepareStatement(Mockito.startsWith("INSERT "))).thenReturn(null);
		repo.addAddress(a);
		
		//Mockito.verify(repo.addAddress(a), Mockito.atLeastOnce()).execute();
	}
	
	
	
	
	
	
	
}