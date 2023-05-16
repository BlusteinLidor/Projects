import java.util.Scanner;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Maman13EX2
 * @author Lidor Blustein
 * id 314993460
 * 
 * This class gets the menu info from a file and makes and
 * order with items accordingly
 *
 */

public class MenuInfo {
	
	
	private Order order;
	private final String[] typeArr = {"Beverages","Main Course","Appetizer","Dessert"};
	private boolean error = false;
	
	/**
     *MenuInfo constructor
     *@param order
     */
	public MenuInfo(Order order) {
		this.order = order;
	}
	
	/**
     *MenuInfo constructor
     *@param inputDir - the file's name
     */
	public MenuInfo(String inputDir) {
		//makes a new order
		order = new Order();
		//try to go over the file and add the items
		try {
			//marks the item's line
			int lineCount = 1;
			//marks the item's index in the order's array
			int itemIndex = 0;
			//scan the file
			Scanner input = new Scanner(new File(inputDir));
			//if the file is empty make an alert and close the input
			if(!input.hasNext()) {
				alert("File is empty");
				input.close();
			}
			//while the file has txt and there isn't an error
			while(input.hasNext() && !error) {
				//get the next line
				String st = input.nextLine();
				//if it's empty, go to the next line
				if(st.isEmpty()) {
					st = input.nextLine();
				}
				//the string is the name of the item
				if(lineCount == 1) {
					//add a new item and set it's name 
					order.addItem(new Item());
					order.getItem(itemIndex).setName(st);
					lineCount++;
				}
				//the string is the type of the item
				else if(lineCount == 2) {
					//marks if the string matches one of the types
					boolean typeFound = false;
					//goes over the types
					for(int i = 0; i < typeArr.length; i++) {
						//if matches - typeFound = true and get out of the loop
						if(st.equals(typeArr[i])) {
							typeFound = true;
							break;
						}
					}
					//if the type matched - add the type to the item
					if(typeFound) {
						order.getItem(itemIndex).setType(st);
						lineCount++;
					}
					//else - alert window - item type not found and exit
					else {
						error = true;
						alert("Item Type not found");
						input.close();
						Platform.exit();
					}
				}
				//the string is the cost of the item
				else {
					//convert cost string to int
					strToInt(st);
					//if there is an error - alert window and exit
					if(error) {
						alert("An item cost not found");
						input.close();
						Platform.exit();
					}
					//else - set the item's cost, checkbox and combobox
					else {
						Item item = order.getItem(itemIndex);
						item.setCost(st);
						item.setChBox();
						item.setCoBox();
						//set lineCount to 1 - prepare for the next item
						lineCount = 1;
						//move to the next item index
						itemIndex++;
					}
				}
			}
			//close file
			input.close();
			
		}
		//if file not found - catch and alert
		catch(FileNotFoundException e){
			alert("File not found");
		}
	}
	
	/**
     *set error to the given boolean value
     *@param bool
     */
	public void setError(boolean bool) {
		this.error = bool;
	}
	
	/**
     *convert cost string to int
     *@param st - the cost string
     *@throws NumberFormatException - if the string isn't a number
     *@return cost
     */
	private int strToInt(String st) throws NumberFormatException{
		return Integer.parseInt(st);
	}
	
	/**
     *alert window with a given text
     *@param error - text
     */
	private void alert(String error) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Error");
		alert.setHeaderText("Error");
		alert.setContentText(error);
		alert.showAndWait();
	}
	
	/**
     *get the menu info's order
     *@return order
     */
	public Order getMenuInfo() {
		return this.order;
	}
	
}
	
