package edu.weber.contact;

import java.util.HashSet;
import java.util.Set;
import java.sql.SQLException;
import java.util.Collection;

import edu.weber.model.Contact;
import edu.weber.repository.ContactRepository;

public class ContactService {

	private static ContactService INSTANCE;
	
	private static Set<Contact> contacts = new HashSet<Contact>();
	private ContactRepository repo;

	public ContactService() {
		repo = ContactRepository.getInstance();
	} 
	
	public ContactService(ContactRepository repo) {
		this.repo = repo;
	}
	
	public Collection<Contact> getContacts(){
		try {
			return repo.getAllContacts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void addContact(Contact c) {
		repo.addContact(c);
	}
	
	public static ContactService getInstance() {
		if(INSTANCE == null){
			INSTANCE = new ContactService();
		}
		return INSTANCE;
	}
}
