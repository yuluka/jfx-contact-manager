package model;

import java.util.ArrayList;
import java.util.List;

public class ContactManager {
	
	private List<Contact> contacts;
	
	public ContactManager() {
		contacts=new ArrayList<Contact>();
		
	}
	
	public void addContact(String name, String email) {
		contacts.add(new Contact(name,email));
		
	}
	
	public List<Contact> getContacts() {
		return contacts;
		
	}
}
