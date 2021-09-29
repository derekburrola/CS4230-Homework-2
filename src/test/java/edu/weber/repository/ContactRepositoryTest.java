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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;

@RunWith(MockitoJUnitRunner.class)
public class ContactRepositoryTest {


	ContactRepository repo;

	@Mock
	private DataSource datasource;

	@Mock
	private Connection db;

	@Mock
	private PreparedStatement stmt;

	@Mock
	private ResultSet rs;
//
//	@Mock
//	private Contact cont;



	@Before
	public void setup() throws Exception {
		repo = new ContactRepository(db, datasource, true);
	}



	@Test 
	public void testGetAllContactsEmpty() throws SQLException {
		when(db.prepareStatement(ArgumentMatchers.any(String.class))).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);

		Collection<Contact> result = repo.getAllContacts();

		Assert.assertEquals(result, new ArrayList<Contact>());
	}
	

	@Test
	public void testAddContact() throws SQLException {
		Contact c = new Contact();
		c.setFirstName("hi");
		c.setLastName("there");
		Set<String> phones = new HashSet<String>();
		String phn = "5555555";
		phones.add(phn);
		c.setPhoneNumbers(phones);
		Address ad[] = new Address[2];
		Address a = new Address();
		a.setAddress1("aa");
		a.setCity("a");
		a.setState("a");
		a.setZipCode("a");
		a.setAddressType("a");
		ad[0] = a;
		c.setAddress(ad);		

		when(db.prepareStatement(ArgumentMatchers.any(String.class))).thenReturn(stmt);
		int result = repo.addContact(c);
		Assert.assertEquals(result, 0);
	}


	@Test
	public void testAddAddress() throws SQLException {
		Address a = new Address();
		a.setAddress1("aa");
		a.setCity("a");
		a.setState("a");
		a.setZipCode("a");
		a.setAddressType("a");
		
		
		when(db.prepareStatement(ArgumentMatchers.any(String.class))).thenReturn(stmt);
		int result = repo.addAddress(a);
		Assert.assertEquals(result, 0);
	}

	@Test
	public void testAddAddressFail() throws SQLException {
		Address a = new Address();
		a.setAddress1("aa");
		a.setCity("a");
		a.setState("a");
		a.setZipCode("a");
		a.setAddressType("a");
		
		when(db.prepareStatement(ArgumentMatchers.any(String.class))).thenReturn(stmt);
		int result = repo.addAddress(a);
		Assert.assertEquals(result, 0);
		
	}




}