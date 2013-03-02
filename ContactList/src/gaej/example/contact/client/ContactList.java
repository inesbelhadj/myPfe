package gaej.example.contact.client;

import java.io.Serializable;

public class ContactList implements Serializable{

	private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String phone;
	
	public ContactList(){
		
	}
	
	public ContactList(String name, String email, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
