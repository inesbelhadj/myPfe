package gaej.example.contact.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("contacts")
public interface ContactService extends RemoteService {
	
	
	List<ContactList> listContacts();
    void addContact(ContactList contactList);
    void removeContact(ContactList contactList);
    void updateContact(ContactList contactList);

}
