package edu.weber.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import edu.weber.model.Contact;

public class ContactRepository {

	private static ContactRepository contactRepository;
	private DataSource datasource;
	private Connection db;
	
	private ContactRepository(){
		this.datasource = DatabaseConnection.getDataSource();
		try {
			this.db = this.datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ContactRepository getInstance() {
		if(contactRepository == null) {
			contactRepository = new ContactRepository();
		}
		return contactRepository;
	}	
	
	public List<Contact> getAllContacts(){
		return null;
	}
	
	private static final String CONTACT_GET = "SELECT "
			+ "c.ID as contactID, "
			+ "c.FIRST_NAME as firstName, "
			+ "c.LAST_NAME as lastName, "
			+ "c.PHONE_NUMBER as phoneNumber, "
			+ "a.ID as addressID, "
			+ "a.ADDRESS_TYPE as addressType, "
			+ "a.ADDRESS1 as address1, "
			+ "a.ADDRESS2 as address2, "
			+ "a.CITY as city, "
			+ "a.STATE as state, "
			+ "a.ZIPCODE as zipCode ";
}
