package gaej.example.contact.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ContactServiceDelegate {
	
	private ContactServiceAsync contactService = GWT.create(ContactService.class);
    ContactListGUI gui;
    
    void listContacts() {
        contactService.listContacts(new AsyncCallback<List<ContactList>> () {
                    public void onFailure(Throwable caught) {
                        gui.service_eventListContactsFailed(caught);
                    }
        
                    public void onSuccess(List<ContactList> result) {
                        gui.service_eventListRetrievedFromService(result);
                        
                    }
        }//end of inner class
        );//end of listContacts method call.
    }
    
    void addContact(final ContactList contactList) {
        contactService.addContact(contactList, new AsyncCallback<Void> () {
            public void onFailure(Throwable caught) {
                gui.service_eventAddContactFailed(caught);
            }

            public void onSuccess(Void result) {
                gui.service_eventAddContactSuccessful();
            }
        }//end of inner class
        );//end of addContact method call.        
    }

    void updateContact(final ContactList contactList) {
        contactService.updateContact(contactList, new AsyncCallback<Void> () {
            public void onFailure(Throwable caught) {
                gui.service_eventUpdateContactFailed(caught);
            }

            public void onSuccess(Void result) {
                gui.service_eventUpdateSuccessful();
            }
        }//end of inner class
        );//end of updateContact method call.        
    }

    void removeContact(final ContactList contactList) {
        contactService.removeContact(contactList, new AsyncCallback<Void> () {
            public void onFailure(Throwable caught) {
                gui.service_eventRemoveContactFailed(caught);
            }

            public void onSuccess(Void result) {
                gui.service_eventRemoveContactSuccessful();
            }
        }//end of inner class
        );//end of updateContact method call.        
    }

}
