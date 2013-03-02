package gaej.example.contact.server;

import gaej.example.contact.client.ContactList;

import java.util.List;

public interface ContactDAO {
	void addContact(ContactList contactList);

	void removeContact(ContactList contactList);

	void updateContact(ContactList contactList);

	List<ContactList> listContacts();
}
