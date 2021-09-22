package edu.weber.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.weber.contact.ContactService;
import edu.weber.model.Address;
import edu.weber.model.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		name="MyServlet",
		urlPatterns = "/"
		)

public class MyServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ContactService service = ContactService.getInstance()
;		req.setAttribute("err", req.getParameter("err"));
		req.setAttribute("contacts", service.getContacts());
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	} 

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fN = req.getParameter("inputfName");
		String lN = req.getParameter("inputlName");
		String add1 = req.getParameter("inputAddress");
		String add2 = req.getParameter("inputAddress2");
		String phn = req.getParameter("inputPhone");
		String city = req.getParameter("inputCity");
		String state = req.getParameter("inputState");
		String zip = req.getParameter("inputZip");
		String type = req.getParameter("inputAddressType");
		
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
		
		if(areInputsValid(inputMap) ) {

			ContactService cs = ContactService.getInstance();

			Set<String> phones = new HashSet<String>();
			phones.add(phn);

			Address addr[] = new Address[2];
			addr[0] = new Address(inputMap);
			//?? Handle adding a second address here
			//?? Make sure to validate the type of address being passed in

			Contact c = new Contact(fN, lN, phones, addr);
			cs.addContact(c); 


			resp.sendRedirect("./");
		}
		else {
			resp.sendRedirect("./?err=All fields with * must be filled in");
		}

	}
	
	protected boolean areInputsValid(Map<String,String> hMap) {
		for (Map.Entry<String,String> entry : hMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			
			if (key == "inputAddress2") {
				// Skip, This value isn't required to be filled
			} else {
				if(value.length() == 0 || value == "" ) {
					return false;
				}
			}
		}
		return true;
	}

}
