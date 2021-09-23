package edu.weber.contact;

import java.util.HashSet;
import java.util.Set;
import java.sql.SQLException;
import java.util.Collection;

import edu.weber.model.Address;
import edu.weber.model.Contact;
import edu.weber.repository.ContactRepository;

public class ContactService {

	private static ContactService INSTANCE;
	
	private static Set<Contact> contacts = new HashSet<Contact>();

	private ContactService() {
		resetSet();
	}
	
	protected void resetSet() {
		contacts = new HashSet<Contact>();
		Address addr = new Address("12 Starvalley", "galaga", "Space", "00000");
		Address addr2 = new Address("21 Andraway", "galaga", "Space", "00071");
		
		Address addresses[] = new Address[2];
		
		addresses[0] = addr;
		addresses[1] = addr2;
		
		Contact c1 = new Contact("Derek", "Burrola");
		Set<String> phns = new HashSet<String>();
		phns.add("123456789");
		phns.add("0987654");
		c1.setPhoneNumbers(phns);
		c1.setAddress(addresses);
		contacts.add(c1);
		
		Contact c2 = new Contact("Edgar", "Poe");
		c2.setPhoneNumbers(phns);
		c2.setAddress(addresses);
		contacts.add(c2);
	}
	
	
	public Collection<Contact> getContacts(){
		//return contacts;
		ContactRepository repo = ContactRepository.getInstance();
		
		try {
			return repo.getAllContacts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void addContact(Contact c) {
		contacts.add(c);
	}
	
	public static ContactService getInstance() {
		if(INSTANCE == null){
			INSTANCE = new ContactService();
		}
		return INSTANCE;
	}
}
