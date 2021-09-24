package edu.weber.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public List<Contact> getAllContacts() throws SQLException {
		PreparedStatement contactStatement = db.prepareStatement(CONTACT_GET.toString());
		ResultSet rs = contactStatement.executeQuery();
		
		List<Contact> response = new ArrayList<Contact>();
		while(rs.next()) {
			Contact c = new Contact();
			c.setFirstName(rs.getString("firstName"));
			response.add(c);
		}
		return response;
	}
	
	public void addContact(Contact c)  {
		PreparedStatement contactStatement;
		try {
			contactStatement = db.prepareStatement(CONTACT_POST.toString());
			ResultSet rs = contactStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static final String CONTACT_GET = "SELECT "
			+ "CONTACT.ID as contactID, "
			+ "CONTACT.FIRST_NAME as firstName, "
			+ "CONTACT.LAST_NAME as lastName, "
			+ "CONTACT.PHONE_NUMBER as phoneNumber, "
			+ "ADDRESS.ID as addressID, "
			+ "ADDRESS.ADDRESS_TYPE as addressType, "
			+ "ADDRESS.ADDRESS1 as address1, "
			+ "ADDRESS.ADDRESS2 as address2, "
			+ "ADDRESS.CITY as city, "
			+ "ADDRESS.STATE as state, "
			+ "ADDRESS.ZIPCODE as zipCode "
			+ "FROM CONTACT "
			+ "INNER JOIN ADDRESS "
			+ "ON CONTACT.ID = ADDRESS.CONTACT_ID ";
	
	private static final String CONTACT_POST = "INSERT INTO CONTACT(FIRST_NAME, LAST_NAME, PHONE_NUMBER "
			+ "VALUES () ";








}
