package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ContactManager;

public class Main extends Application{

	private ContactManager contactMan;
	private ContactManagerGUI contactManGUI;
	
	public Main() {
		contactMan=new ContactManager();
		contactManGUI=new ContactManagerGUI(contactMan);
		
	}
	
	public static void main(String[] args) {
		launch(args);
		

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Parent root=FXMLLoader.load(getClass().getResource("ContactManager.fxml")); Se va cambiar por otras tres líneas.
		
		FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("ContactManager.fxml"));
		fxmlLoader.setController(contactManGUI);
		Parent root=fxmlLoader.load();
		
		Scene sc=new Scene(root);
		primaryStage.setTitle("Contact Manager");
		primaryStage.setScene(sc);
		primaryStage.show();
		
	}

}
