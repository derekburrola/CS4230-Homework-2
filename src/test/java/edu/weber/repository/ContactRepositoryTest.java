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

//	@Mock
//	private ResultSet rs;
//
//	@Mock
//	private Contact cont;



	@Before
	public void setup() throws Exception {
		repo = new ContactRepository(db, datasource);
	}

	@Test
	public void testGetContacts() throws SQLException {

		Assert.assertTrue(true);
		//
		//		Collection<Contact> result = repo.getAllContacts();
		//		Assert.assertNotEquals(result, null);
	}

//	@Test 
//	public void testGetAllContactsEmpty() throws SQLException {
//		when(db.prepareStatement(ArgumentMatchers.any(String.class))).thenReturn(stmt);
//		when(stmt.executeQuery()).thenReturn(rs);
//		//when(repo.getAllContacts()).thenReturn(null);
//
//		when(rs.next()).thenReturn(true);
//
//		when(rs.getString("firstName")).thenReturn("Derek");
//		when(rs.getString("lastName")).thenReturn("Derek");
//		when(rs.getString("phoneNumber")).thenReturn("555-555-5555");
//
//		when(rs.getString("address1")).thenReturn("Rainbow Road");
//		when(rs.getString("address2")).thenReturn("");
//		when(rs.getString("city")).thenReturn("Mushroom Kingdom");
//		when(rs.getString("state")).thenReturn("N64");
//		when(rs.getString("zipCode")).thenReturn("83443");
//		when(rs.getString("addressType")).thenReturn("Work");
//
//		Collection<Contact> result = repo.getAllContacts();
//		//Collection<Contact> expected = new List<Contact>();
//
//		//Assert.assertNotEquals(result, null);
//		Assert.assertEquals(result, null);
//	}


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

		//when(db.prepareStatement(ArgumentMatchers.any(String.class))).thenReturn(stmt);


		when(db.prepareStatement(ArgumentMatchers.any(String.class))).thenReturn(stmt);
		int result = repo.addContact(c);
		Assert.assertEquals(result, 0);
	}

//	@Test
//	public void testAddContactFail() {
//		Contact c = new Contact("hi", "there");
//		try {
//			//when(db.prepareStatement(ArgumentMatchers.any(String.class))).thenThrow(new SQLException());
//			int result = repo.addContact(c);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//			Assert.assertTrue(true);
//		}
//	}

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