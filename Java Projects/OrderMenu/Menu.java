import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Maman13EX2
 * @author Lidor Blustein
 * id 314993460
 * 
 * This class is the main class - it launches the Menu program using FXML
 *
 */

public class Menu extends Application{ 
	
	public void start(Stage stage) throws Exception{ 
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("Menu.fxml")); 
			Scene scene = new Scene(root); 
			stage.setTitle("Menu"); 
			stage.setScene(scene); 
			stage.show(); 
		}
		catch(LoadException e) {
    		
    	}
		
	} 
	
	
	public static void main(String[] args) { 
		launch(args); 
		System.out.println();
		
	} 
}
