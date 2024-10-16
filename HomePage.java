package application;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class HomePage extends Application{
	private BorderPane mainPage = new BorderPane();
	private Button homeBtn = new Button("Create an account");


	@Override
	public void start(Stage primary) {
		try {
			mainPage.setCenter(homeBtn);
			
			homeBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> openAccountPage(primary));
			
			
			Scene homeScene = new Scene(mainPage,1024,600);
			homeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primary.setScene(homeScene);
			primary.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void openAccountPage(Stage primary) {	
		GridPane ap = new GridPane();
		
		TextField accName = new TextField();
		TextField accBal = new TextField();
		TextField accDate = new TextField();
		
		Button back = new Button("Home");
		ap.add(back, 0,  0);
		ap.add(accName, 0, 3);
		ap.add(accBal, 1, 3);
		ap.add(accDate, 2, 3);
		
		back.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> backToHomePage(primary));
		
		Scene accountPage = new Scene(ap, 1024, 600);
		
		primary.setScene(accountPage);
		primary.show();
		
	}
	
	private void backToHomePage(Stage primary) {
		HomePage hp = new HomePage();
		hp.start(primary);
	}

}
