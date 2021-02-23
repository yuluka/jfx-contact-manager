package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
	
	private List<Contact> contacts;
	
	private final static String SEPARATOR=";";
	
	public ContactManager() {
		contacts=new ArrayList<Contact>();
		
	}
	
	public void addContact(String name, String email) {
		contacts.add(new Contact(name,email));
		
	}
	
	public List<Contact> getContacts() {
		return contacts;
		
	}
	
	public void importData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    
		String line = br.readLine();
	    
		while(line!=null){
	      String[] parts = line.split(SEPARATOR);
	     
	      addContact(parts[0],parts[1]);
	      
	      line = br.readLine();
	    
		}
	    
		br.close();
	}
	
	public void exportData(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fileName);
		
		for(int i = 0; i<contacts.size(); i++) {
			Contact contactAux = contacts.get(i);
			
			pw.println(contactAux.getName()+ SEPARATOR + contactAux.getEmail());
		}
		
		pw.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
