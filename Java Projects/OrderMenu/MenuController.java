import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Formatter;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;

/**
 * Maman13EX2
 * @author Lidor Blustein
 * id 314993460
 * 
 * This class controls the graphics, it gives the graphics instructions
 * on what to do.
 *
 */

public class MenuController {

    @FXML
    private GridPane grid;
    
    @FXML
    private Button orderBtn;
    
    //type "appetizer" column index
    private final int APP_COL = 0;
    //type "main course" column index
    private final int MAIN_COL = 1;
    //type "dessert" column index
    private final int DES_COL = 2;
    //type "beverages" column index
    private final int BEV_COL = 3;
    //Menu info input
    private MenuInfo currMenuInfo;
    
    @FXML
    /**
     *order button - opens the order summary window
     *with options - confirm/update/cancel order
     *@param event - pressing on the button
     */
    void makeOrder(ActionEvent event) {
    	//if the item's checkbox is ticked - the item is entered into
    	//the ordered items and the item's quantity is changed
    	//to the value set in the combobox
    	for(Item item : this.currMenuInfo.getMenuInfo().getArr()) {
    		if(item.getChBox().isSelected()) {
    			this.currMenuInfo.getMenuInfo().addItemOrdered(item);
    			item.setQuan((int)(item.getCoBox().getValue()));
    		}
    	}
    	//the options to choose from after pressing order
    	ArrayList<String> choices = new ArrayList<String>();
    	choices.add("Confirm order");
    	choices.add("Update order");
    	choices.add("Cancel order");
    	//sets the dialog window for the order summary 
    	ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
    	dialog.setTitle("Order Summary");
    	String orderDetails = "";
    	//sets the order summary 
    	for(Item item : this.currMenuInfo.getMenuInfo().getArrOrd()) {
    		orderDetails += item.getName() + " " + item.getQuan() + "\n";
    	}
    	orderDetails += "Total cost: " + this.currMenuInfo.getMenuInfo().getTotalCost();
    	dialog.setHeaderText("Your order: \n" + orderDetails);
    	dialog.setContentText("Please choose an option:");
    	Optional<String> result = dialog.showAndWait();
    	//if ok pressed
    	if(result.isPresent()) {
    		//if user chose confirm
    		if(result.get().equals("Confirm order")) {
    			//open a new dialog window and ask to enter name+ID
    			TextInputDialog orderConf = new TextInputDialog();
    			orderConf.setTitle("Password Confirmation");
    			orderConf.setHeaderText("Password Confirmation");
    			orderConf.setContentText("Please enter your password(name+ID):");
    			Optional<String> confRes = orderConf.showAndWait();
    			//if ok pressed
    			if(confRes.isPresent()) {
    				//the string entered by the user is saved
    				String password = confRes.get();
    				//try to make a txt file with the string
    				//entered for it's name
    				try {
    					File orderFile = new File(password + ".txt");
    					//if was possible
    					if(orderFile.createNewFile()) {
    						//open the file
    						Formatter output = new Formatter(password + ".txt");
    						//write to it the order details
    						output.format("Your order is: \n" + orderDetails, null);
    						output.close();
    						//open a new menu and clear the current items ordered array
    						this.currMenuInfo.getMenuInfo().getArrOrd().clear();
    						initialize();
    					}
    					//else - alert that there is already a file named like this
    					else {
    						Alert alert = new Alert(AlertType.CONFIRMATION);
        					alert.setTitle("Error");
        					alert.setHeaderText("Error");
        					alert.setContentText("The file already exists");
        					alert.showAndWait();
    					}
    				}
    				//catch - alert - couldn't make the file
    				catch(IOException e) {
    					Alert alert = new Alert(AlertType.CONFIRMATION);
    					alert.setTitle("Error");
    					alert.setHeaderText("Error");
    					alert.setContentText("Error occured");
    					alert.showAndWait();
    				}
    			}
    		}
    		//user chose cancel - open new menu and clear the current items ordered array
    		else if(result.get().equals("Cancel order")) {
    			initialize();
    		}
    		this.currMenuInfo.getMenuInfo().getArrOrd().clear();
    	}
    	//user chose update - return to the menu and clear the current items ordered array
    	else {
    		this.currMenuInfo.getMenuInfo().getArrOrd().clear();
    	}
    	
    }

    /**
     * initializes the window and what's in it
     */
    public void initialize() {
    	grid.getChildren().clear();
    	grid.setHgap(5);
    	//try to get new Menu Info from the menu.txt file
    	try {
    		MenuInfo mInfo = new MenuInfo("menu.txt");
    		this.currMenuInfo = mInfo;
    		//load the menu's items on the window
        	loadMenu(mInfo.getMenuInfo());
        	for(Item item : this.currMenuInfo.getMenuInfo().getArr()) {
        		item.getChBox().setSelected(false);
        		item.getCoBox().setValue(1);
        		item.setQuan(0);
        	}
    	}
    	//if the menu file has a wrong type
    	catch(IllegalStateException e) {
    		Platform.exit();
    		this.currMenuInfo.setError(true);
    	}
    	//if the menu file has a cost that's not a number
    	catch(NumberFormatException e) {
    		this.currMenuInfo.setError(true);
    	}
    }
    
    /**
     *load an item to the window
     *@param item
     */
    private void loadItem(Item item) {
    	//the column the item needs to be added to
    	int col = APP_COL;
    	if(item.getType().equals("Appetizer")) {
    		col = APP_COL;
    	}
    	else if(item.getType().equals("Main Course")) {
    		col = MAIN_COL;
    	}
    	else if(item.getType().equals("Dessert")) {
    		col = DES_COL;
    	}
    	else if(item.getType().equals("Beverages")){
    		col = BEV_COL;
    	}
    	//add the item's checkbox and combobox to the grid's given column
    	grid.addColumn(col, item.getChBox(), item.getCoBox());
    }

    /**
     *loads the menu - iterates over loadItem
     *@param order - the order's available items
     */
    private void loadMenu(Order order) {
    	//for item in array loadItem
    	for(Item item : order.getArr()) {
    		loadItem(item);
    	}
    }
    
    /**
     *makes an alert window with a given text
     *@param error - the alarm's text
     */
    private void alert(String error) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Error");
		alert.setHeaderText("Error");
		alert.setContentText(error);
		alert.showAndWait();
	}
    
}
