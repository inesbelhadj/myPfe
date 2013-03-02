package gaej.example.contact.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ContactServiceAsync {
	void listContacts(AsyncCallback<List <ContactList>> callback);
    void addContact(ContactList contactList, AsyncCallback<Void> callback);
    void removeContact(ContactList contactList, AsyncCallback<Void> callback);
    void updateContact(ContactList contactList, AsyncCallback<Void> callback);

}
