package gaej.example.contact.server;
import gaej.example.contact.client.ContactList;
import gaej.example.contact.client.ContactService;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ContactServiceImpl extends RemoteServiceServlet implements ContactService{
	private static final long serialVersionUID = 1L;
    private ContactDAO contactDAO = new ContactDAOMock();

    public void addContact(ContactList contactList) {
        contactDAO.addContact(contactList);
    }

    public List<ContactList> listContacts() {
        List<ContactList> listContacts = contactDAO.listContacts();
        return new ArrayList<ContactList> (listContacts);
    }

    public void removeContact(ContactList contactList) {
        contactDAO.removeContact(contactList);
    
    }

    public void updateContact(ContactList contactList) {
        contactDAO.updateContact(contactList);
    }
}
