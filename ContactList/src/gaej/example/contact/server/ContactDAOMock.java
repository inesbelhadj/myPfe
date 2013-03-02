package gaej.example.contact.server;


import gaej.example.contact.client.ContactList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;


public class ContactDAOMock implements ContactDAO {
	
	Map<String, ContactList> map = new LinkedHashMap<String, ContactList>();
	
	 {
		
	        map.put("rhightower@mammatus.com", 
	          new ContactList("Rick Hightower", "rhightower@mammatus.com", "520-555-1212"));
	        map.put("scott@mammatus.com", 
	          new ContactList("Scott Fauerbach", "scott@mammatus.com", "520-555-1213"));
	        map.put("bob@mammatus.com", 
	          new ContactList("Bob Dean", "bob@mammatus.com", "520-555-1214"));

	    }
	 
	 public void addContact(ContactList contactList) {
	        String email = contactList.getEmail();
	        map.put(email, contactList);
	    }

	    public List<ContactList> listContacts() {
	        return Collections.unmodifiableList(new ArrayList<ContactList>(map.values()));
	    }

	    public void removeContact(ContactList contactList) {
	        map.remove(contactList.getEmail());
	    }

	    public void updateContact(ContactList contactList) {        
	        map.put(contactList.getEmail(), contactList);
	    }

}
