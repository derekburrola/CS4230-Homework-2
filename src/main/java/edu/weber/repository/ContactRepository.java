 package edu.weber.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import edu.weber.model.Address;
import edu.weber.model.Contact;

public class ContactRepository {

	public static ContactRepository contactRepository;
	private DataSource datasource;
	private Connection db;
	
	public ContactRepository(Connection db, DataSource ds) {
		this.db = db;
		datasource = ds;
	}
	
	public ContactRepository(){
		this.datasource = DatabaseConnection.getDataSource();
		try { 
			this.db = this.datasource.getConnection();
		} catch (Exception e) {
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
			// Create the contact
			Contact c = new Contact();
			c.setFirstName(rs.getString("firstName"));
			c.setLastName(rs.getString("lastName"));
			Set<String> phones = new HashSet<String>();
			phones.add(rs.getString("phoneNumber"));
			c.setPhoneNumbers(phones);
			
			
			// Create the address
			Address addr[] = new Address[2];
			Address a = new Address();
			a.setAddress1(rs.getString("address1"));
			a.setAddress2(rs.getString("address2"));
			a.setCity(rs.getString("city"));
			a.setState(rs.getString("state"));
			a.setZipCode(rs.getString("zipCode"));
			a.setAddressType(rs.getString("addressType"));
			addr[0] = a;
			c.setAddress(addr);
			
			response.add(c);
		}
		return response; 
	}
	
	public int addContact(Contact c)  {
		int result = 0;
		PreparedStatement contactStatement;
		
		try {
			contactStatement = db.prepareStatement(CONTACT_POST.toString());
			
			contactStatement.setString(1, c.getFirstName());
			contactStatement.setString(2, c.getLastName());
			contactStatement.setString(3, c.getPhoneNumbers().toString());
			
			result = contactStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Add Contact Error: ");
		}
		addAddress(c.getAddress()[0]); 
		return result;
	}
	
	public int addAddress(Address a) {
		int result = 0;
		PreparedStatement contactStatement;
		try {
			contactStatement = db.prepareStatement(ADDRESS_POST.toString());
			 
			contactStatement.setString(1, a.getAddress1());
			contactStatement.setString(2, a.getAddress2());
			contactStatement.setString(3, a.getCity());
			contactStatement.setString(4, a.getState());
			contactStatement.setString(5, a.getZipCode());
			contactStatement.setString(6, a.getAddressType());
			
			result = contactStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Add Address Error: ");
		}
		return result;
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
	
	private static final String CONTACT_POST =  
			"INSERT INTO CONTACT(FIRST_NAME, LAST_NAME, PHONE_NUMBER) "
			+ "VALUES (?,?,?) ";

	private static final String ADDRESS_POST =
			"INSERT INTO ADDRESS(CONTACT_ID, ADDRESS1, ADDRESS2, CITY, STATE, ZIPCODE, ADDRESS_TYPE) "
			+ "VALUES ((SELECT ID FROM CONTACT ORDER BY ID DESC LIMIT 1), ?, ?, ?, ?, ?, ? ) ";




}
