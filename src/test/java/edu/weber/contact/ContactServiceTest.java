package edu.weber.contact;

import org.junit.Test;

import static org.mockito.Mockito.when;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import edu.weber.model.Contact;
import edu.weber.repository.ContactRepository;


public class ContactServiceTest {
	
	ContactService obj;
	
	@Mock 
	ContactRepository contactRepo;
	
	@Before
	public void setup() {
		obj = new ContactService(contactRepo); 
	}
	

	@Test
	public void testGetContactsIsNull() throws SQLException {
		when(contactRepo.getAllContacts()).thenReturn(null);
		Collection<Contact> result = obj.getContacts();
		Assert.assertEquals(result, null);
	}
	
	@Test
	public void testGetContactsHasContact() throws SQLException{
		Contact a = new Contact("Derek", "Burrola");
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(a);
		contacts.add(a);
		Collection<Contact> expected = contacts;
		
		when(contactRepo.getAllContacts()).thenReturn(contacts);
		Collection<Contact> result = obj.getContacts();
		Assert.assertEquals(result, expected);
	}

	
	@Test
	public void testAddContact() throws SQLException {
		int expectedCount = 1;
		Contact a = new Contact("Derek", "Burrola");
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(a);
		when(contactRepo.getAllContacts()).thenReturn(contacts);
		
		obj.addContact(a);
		
		Collection<Contact> result = obj.getContacts();
		int count = result.size();
		Assert.assertEquals(count, expectedCount);
	}

}


