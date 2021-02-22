package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Contact;
import model.ContactManager;

public class ContactManagerGUI {

	@FXML
	private TextField txtName;

    @FXML
    private TextField txtEmail;
    	
    @FXML
    private Pane mainPane;
    
    @FXML
    private TableView<Contact> lstContact;

    @FXML
    private TableColumn<Contact, String> columnName;

    @FXML
    private TableColumn<Contact, String> columnEmail;
    
    
    
    private ContactManager objContactMan;
    
    
    
    public ContactManagerGUI(ContactManager objContactMan) {
    	this.objContactMan=objContactMan;
    	
    }
    
    @FXML
    public void goToAddContact(ActionEvent event) throws IOException {
    	// Parent addContact=FXMLLoader.load(getClass().getResource("add-contact.fxml"));
    	FXMLLoader addFXML=new FXMLLoader(getClass().getResource("add-contact.fxml"));
    	addFXML.setController(this);
    	Parent addPane=addFXML.load();
    	mainPane.getChildren().setAll(addPane);
    	
    }

    @FXML
    public void goToList(ActionEvent event) throws IOException {
    	// Parent contactList=FXMLLoader.load(getClass().getResource("contact-list.fxml")); Se reemplaza por las de abajo, pero creo que cumplen la misma función.
    	FXMLLoader listFXML=new FXMLLoader(getClass().getResource("contact-list.fxml"));
    	listFXML.setController(this);
    	Parent listPane=listFXML.load();
    	mainPane.getChildren().setAll(listPane);
    	
    	initializeTableView();

    }
    
    @FXML
	public void saveContact(ActionEvent event) {
      	
    	if(!txtName.getText().equals("") && !txtEmail.getText().equals("")) {
        	String name=txtName.getText();
        	String email=txtEmail.getText();
        	
        	objContactMan.addContact(name, email);
        	
        	txtName.setText("");
        	txtEmail.setText("");

    	}

    }
    
    public void initializeTableView() {
    	ObservableList<Contact> listContacts=FXCollections.observableArrayList(objContactMan.getContacts());
    	
    	columnName.setCellValueFactory(new PropertyValueFactory<Contact,String>("name"));
    	columnEmail.setCellValueFactory(new PropertyValueFactory<Contact,String>("email"));
    	
    	lstContact.setItems(listContacts);
    }


}
