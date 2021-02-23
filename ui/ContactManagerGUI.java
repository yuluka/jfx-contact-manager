package ui;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
    public void showAbout(ActionEvent event) {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	
    	alert.setTitle("Contact Manager");
    	alert.setHeaderText("Credits");
    	alert.setContentText("Yuluka Gigante Muriel\nAPO II");
    	
    	alert.show();
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

    
    @FXML
    public void importContacts(ActionEvent event) {
    	// Fijarse en estas líneas en caso de no recordar cómo usar el file chooser.
    	FileChooser fc=new FileChooser();
    	fc.setTitle("Select the contact list");
    	File file = fc.showOpenDialog(mainPane.getScene().getWindow());
    	

		Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Import contacts");
    	
    	if(file!=null) {
    		try {
				objContactMan.importData(file.getAbsolutePath());
				
		    	alert.setContentText("Contact data was imported succesfuly");
		    	alert.show();
		    	
			} catch (IOException e) {
				alert.setContentText("Contact data wasn't imported succesfuly");
		    	alert.show();
			};
    	}
    }
    
    @FXML
    public void exportContacts(ActionEvent event) {
    	FileChooser fc=new FileChooser();
    	fc.setTitle("Select the contact list");
    	File file = fc.showSaveDialog(mainPane.getScene().getWindow());
    	
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Import contacts");
    	
    	if(file!=null) {
    		try {
				objContactMan.exportData(file.getAbsolutePath());
				
		    	alert.setContentText("Contact data was exported succesfuly");
		    	alert.show();
		    	
			} catch (IOException e) {
				alert.setContentText("Contact data wasn't exported succesfuly");
		    	alert.show();
			};
    	}
    }
}
