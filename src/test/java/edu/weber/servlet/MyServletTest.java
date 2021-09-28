package edu.weber.servlet;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import edu.weber.contact.ContactService;
import edu.weber.model.Contact;
import edu.weber.repository.ContactRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class MyServletTest {

	@Test
	public void passTest() {

		Assert.assertEquals(true, true);
	}
	
	@Mock
	RequestDispatcher requestDispatcher;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock 
	ContactService cs;
	
	@Mock 
	ContactRepository contactRepo;

	MyServlet testObj;

	@Before
	public void setup() {
		testObj = new MyServlet();
	} 



		@Test
		public void doGetHasRequestAttributeContacts() throws ServletException, IOException {
			ArgumentCaptor<Collection<String>> servletRequestCapture = ArgumentCaptor.forClass(Collection.class);
			//ArgumentCaptor<String> src = ArgumentCaptor.forClass(String.class);
			when(request.getRequestDispatcher(ArgumentMatchers.any(String.class))).thenReturn(requestDispatcher);
			when(request.getParameter("err")).thenReturn("");
			testObj.doGet(request, response);
	
	
			verify(request, times(2)).setAttribute(ArgumentMatchers.any(String.class), servletRequestCapture.capture());
			//verify(request, times(1)).setAttribute(ArgumentMatchers.any(String.class), src);
			Assert.assertNotNull(servletRequestCapture.getValue());
	
		}
	
		@Test
		public void doGetHasRequestAttributeContactsHasDefault() throws ServletException, IOException {
			ArgumentCaptor<Set<Contact>> servletRequestCapture = ArgumentCaptor.forClass(Set.class);
	
			when(request.getRequestDispatcher(ArgumentMatchers.any(String.class))).thenReturn(requestDispatcher);
			//when(request.getParameter("err")).thenReturn("");
			testObj.doGet(request, response);
	
			verify(request, times(2)).setAttribute(ArgumentMatchers.any(String.class), servletRequestCapture.capture());
	
			Set contactsCollection = servletRequestCapture.getValue();
			Assert.assertTrue(contactsCollection.size() > 0);
	
		}	 



		@Test
		public void testPost() throws ServletException, IOException{
			ArgumentCaptor<String> servletRequestCapture = ArgumentCaptor.forClass(String.class);
	
			when(request.getParameter(ArgumentMatchers.any(String.class))).thenReturn("aaa");
			testObj.doPost(request, response);
	
			verify(request, times(9)).getParameter(servletRequestCapture.capture());
	
			Assert.assertNotNull(servletRequestCapture.getValue());
		}

	@Test public void testPostMethod() throws ServletException, IOException, SQLException{
		ArgumentCaptor<String> servletRequestCapture = ArgumentCaptor.forClass(String.class);

		when(request.getParameter(ArgumentMatchers.any(String.class))).thenReturn("aaa");
		//when(cs.getInstance()).thenReturn(cs.getInstance());
		when(contactRepo.getAllContacts()).thenReturn(null);
		testObj.doPost(request, response);
	}


	@Test
	public void doAreInputsValidTrue() {

		MyServlet ms = new MyServlet();

		String fN = "11";
		String lN = "11";
		String add1 = "11";
		String add2 = "11";
		String phn = "11";
		String city = "11";
		String state = "11";
		String zip = "11";
		String type = "11";

		Map<String, String> inputMap = new HashMap<String, String>();
		inputMap.put("inputfName", fN);
		inputMap.put("inputlName", lN);
		inputMap.put("inputAddress", add1);
		inputMap.put("inputAddress2", add2);
		inputMap.put("inputPhone", phn);
		inputMap.put("inputCity", city);
		inputMap.put("inputState", state);
		inputMap.put("inputZip", zip);
		inputMap.put("inpuAddressType", type);

		Assert.assertTrue(ms.areInputsValid(inputMap));

	}

	@Test
	public void doAreInputsValidFalse() {

		MyServlet ms = new MyServlet();

		String fN = "";
		String lN = "11";
		String add1 = "11";
		String add2 = "11";
		String phn = "11";
		String city = "11";
		String state = "11";
		String zip = "11";
		String type = "11";

		Map<String, String> inputMap = new HashMap<String, String>();
		inputMap.put("inputfName", fN);
		inputMap.put("inputlName", lN);
		inputMap.put("inputAddress", add1);
		inputMap.put("inputAddress2", add2);
		inputMap.put("inputPhone", phn);
		inputMap.put("inputCity", city);
		inputMap.put("inputState", state);
		inputMap.put("inputZip", zip);
		inputMap.put("inpuAddressType", type);

		Assert.assertFalse(ms.areInputsValid(inputMap));

	}

}
