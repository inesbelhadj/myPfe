package gaej.example.contact.client;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class ContactListGUI {
	
	
	/* Constants. */
    private static final String CONTACT_LISTING_ROOT_PANEL = "contactListing";
    private static final String CONTACT_FORM_ROOT_PANEL = "contactForm";
    private static final String CONTACT_STATUS_ROOT_PANEL = "contactStatus";
    private static final String CONTACT_TOOL_BAR_ROOT_PANEL = "contactToolBar";
    private static final int EDIT_LINK = 3;
    private static final int REMOVE_LINK = 4;

    /* GUI Widgets */
    protected Button addButton;
    protected Button updateButton;
    protected Button addNewButton;
    protected TextBox nameField;
    protected TextBox emailField;
    protected TextBox phoneField;
    protected Label status;
    protected Grid contactGrid;
    protected Grid formGrid;
    
    /* Data model */
    private List<ContactList> contactLists;
    private ContactList currentContact;
    protected ContactServiceDelegate contactService;
        
    public void init() {
        addButton = new Button("Add new contact");
        addNewButton = new Button("Add new contact");
        updateButton = new Button("Update contact");
        nameField = new TextBox();
        emailField = new TextBox();
        phoneField = new TextBox();
        status = new Label();
        contactGrid = new Grid(2,5);

        buildForm();
        placeWidgets();
    }
    
    private void buildForm() {
        formGrid = new Grid(4,3);
        formGrid.setVisible(false);
        
        formGrid.setWidget(0, 0, new Label("Name"));
        formGrid.setWidget(0, 1, nameField);

        formGrid.setWidget(1, 0, new Label("email"));
        formGrid.setWidget(1, 1, emailField);
        
        formGrid.setWidget(2, 0, new Label("phone"));
        formGrid.setWidget(2, 1, phoneField);
        
        formGrid.setWidget(3, 0, updateButton);
        formGrid.setWidget(3, 1, addButton);
        
    }

    private void placeWidgets() {
        RootPanel.get(CONTACT_LISTING_ROOT_PANEL).add(contactGrid);
        RootPanel.get(CONTACT_FORM_ROOT_PANEL).add(formGrid);
        RootPanel.get(CONTACT_STATUS_ROOT_PANEL).add(status);
        RootPanel.get(CONTACT_TOOL_BAR_ROOT_PANEL).add(addNewButton);
    }

    private void loadForm(ContactList contactList) {
        this.formGrid.setVisible(true);
        currentContact = contactList;
        this.emailField.setText(contactList.getEmail());
        this.phoneField.setText(contactList.getPhone());
        this.nameField.setText(contactList.getName());
    }


    private void copyFieldDateToContact() {
        currentContact.setEmail(emailField.getText());
        currentContact.setName(nameField.getText());
        currentContact.setPhone(phoneField.getText());
    }

    public void gui_eventContactGridClicked(Cell cellClicked) {
         int row = cellClicked.getRowIndex();
         int col = cellClicked.getCellIndex();
        
         ContactList contactList = this.contactLists.get(row);
         this.status.setText("Name was " + contactList.getName() + " clicked ");
        
         if (col==EDIT_LINK) {
             this.addNewButton.setVisible(false);
             this.updateButton.setVisible(true);
             this.addButton.setVisible(false);
             this.emailField.setReadOnly(true);
             loadForm(contactList);
         } else if (col==REMOVE_LINK) {
             this.contactService.removeContact(contactList);
         }
    }


    public void gui_eventAddButtonClicked() {
        addNewButton.setVisible(true);
        formGrid.setVisible(false);
        copyFieldDateToContact();
        this.phoneField.getText();
        this.contactService.addContact(currentContact);
    }

    public void gui_eventUpdateButtonClicked() {
        addNewButton.setVisible(true);
        formGrid.setVisible(false);
        copyFieldDateToContact();
        this.contactService.updateContact(currentContact);
    }

    public void gui_eventAddNewButtonClicked() {
        this.addNewButton.setVisible(false);
        this.updateButton.setVisible(false);
        this.addButton.setVisible(true);
        this.emailField.setReadOnly(false);
        loadForm(new ContactList());
    }


    public void service_eventListRetrievedFromService(List<ContactList> result) {
        //tester contenu de result:
    	
    	for(int i=0;i<result.size();i++)
    	{
            System.out.println("result name is:"+result.get(i).getName());

    	}
    	
    	
    	
    	status.setText("Retrieved contact list");
        this.contactLists = result;
        this.contactGrid.clear();
        this.contactGrid.resizeRows(this.contactLists.size());
        
        System.out.println("contactLists.size() = :"+this.contactLists.size());
                
        for (int row =0;row<contactLists.size();row++) {
            System.out.println("add ===> "+contactLists.get(row).getName());
            this.contactGrid.setWidget(row, 0, new Label(contactLists.get(row).getName()));
            this.contactGrid.setWidget(row, 1, new Label (contactLists.get(row).getPhone()));
            this.contactGrid.setWidget(row, 2, new Label (contactLists.get(row).getEmail()));
            this.contactGrid.setWidget(row, EDIT_LINK, new Hyperlink("Edit", "Edit"));
            this.contactGrid.setWidget(row, REMOVE_LINK, new Hyperlink("Remove", "Remove"));
        }
    }

    public void service_eventAddContactSuccessful() {
        status.setText("Contact was successfully added");
        this.contactService.listContacts();
    }

    public void service_eventUpdateSuccessful() {
        status.setText("Contact was successfully updated");
        this.contactService.listContacts();
    }
    public void service_eventRemoveContactSuccessful() {
        status.setText("Contact was removed");
        this.contactService.listContacts();
        
    }

    public void service_eventUpdateContactFailed(Throwable caught) {
        status.setText("Update contact failed");
    }

    public void service_eventAddContactFailed(Throwable caught) {
        status.setText("Unable to update contact");
    }

    public void service_eventRemoveContactFailed(Throwable caught) {
        status.setText("Remove contact failed");
    }

    public void service_eventListContactsFailed(Throwable caught) {
        status.setText("Unable to get contact list");
        
    }

}
