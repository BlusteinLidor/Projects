import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;

/**
 * Maman13EX1
 * @author Lidor Blustein
 * id 314993460
 * 
 * This class controls the graphics, it gives the graphics instructions
 * on what to do.
 *
 */

public class SudokuController {

	@FXML
	private GridPane grid;

	@FXML
	private Button clearBtn;

	@FXML
	private Button setBtn;

	//row and column length
	private final int SIZE = 9;
	//lowest number that can be entered into a cell
	private final int LOWEST = 1;
	//highest number that can be entered into a cell
	private final int HIGHEST = 9;
	//counts how many cells left to fill
	private static int counter = 81;
	//the game is over when counter hits 0
	private final int GAME_OVER = 0;
	//array that saves the cells as textFields
	private TextField[] textFields;
	//marks if needed an alert(true) or not
	private boolean flag = true;
	//marks if the set button has already been pressed(true) or not
	private boolean tableSet = false;

	/**
     * initializes the window and what's in it
     */
	public void initialize() {
		newBlocks();
		colorCells();
	}
	/**
     *alerts for an error by entering a number that is already
     *in the same row or column
     *@param textField - the TextField that has an error
     */
	private void alert(TextField textField) {
		if (flag) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText("This number can't be entered here");
			//if there was a number entered there previously and was approved
			//the counter will add one to it, to compensate for the lost
			//accurate number
			if(textField.getStyle() == "-fx-text-fill: blue;-fx-border-color: black;") {
				counter++;
			}
			textField.setStyle("-fx-border-color: black;");
			textField.clear();
			alert.showAndWait();
		}
	}

	/**
     *colors 4 3x3 boxes of cells in the outer margin
     *and 1 3x3 box of cells in the center in gray
     */
	private void colorCells() {
		//outer boxes cells' row and column indexes
		int[] outerBoxes = {0,1,2,6,7,8};
		//inner box cells' row and column indexes
		int[] innerBox = {3,4,5};
		//going over the cells and checks which cells needed to
		//be colored by using binary search over the arrays
		for(int i = 0; i < SIZE*SIZE; i++) {
			if(Arrays.binarySearch(outerBoxes, getRow(i)) >= 0 && 
					Arrays.binarySearch(outerBoxes, getCol(i)) >= 0) {
				textFields[i].setStyle("-fx-background-color: gray; -fx-border-color: black;");
			}
			if(Arrays.binarySearch(innerBox, getRow(i)) >= 0 && 
					Arrays.binarySearch(innerBox, getCol(i)) >= 0) {
				textFields[i].setStyle("-fx-background-color: gray; -fx-border-color: black;");
			}
		}
	}
	
	/**
     *when the game is over - shows a well done alert
     *@param textField - the TextField that has an error
     */
	private void gameOver() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Game Over");
		alert.setHeaderText("Well done");
		alert.setContentText("Well done");
		alert.showAndWait();
	}

	/**
     *adding a number to a cell - this method checks
     *the "legality" of the entered number
     *@param event - when enter is pressed
     */
	private void numAdd(KeyEvent event) {
		//if set button has been pressed
		if (tableSet) {
			//getting the TextField that the number was entered in
			TextField textField = (TextField) event.getSource();
			//if the number is accepted
			if (isLegal(textField)) {
				textField.setText(textField.getText());
				//if the cell has a white background and an approved
				//number has been entered - do nothing
				if(textField.getStyle() == "-fx-text-fill: blue;-fx-border-color: black;") {
				}
				//otherwise(if the cell background was gray), keep it, and
				//reduce one from the counter
				else {
					textField.setStyle("-fx-background-color: gray;-fx-text-fill: blue;-fx-border-color: black;");
					if(!textField.getText().isEmpty()) {
						counter--;
					}
				}
				//if this was the last cell - game over
				if(counter == GAME_OVER) {
					gameOver();
				}
			} 
			//if the number was not accepted
			else {
				//make an alert
				if (flag) {
					alert(textField);
				}
			}
		}
		//if the table hasn't been set yet
		else {
			TextField textField = (TextField) event.getSource();
			//if the input is legal
			if (isLegal(textField)) {
				//make the TextField disabled - because this is 
				//the stage in which the user sets the board
				textField.setText(textField.getText());
				textField.setDisable(true);
				//decrement counter - a number has been added
				if(!textField.getText().isEmpty()) {
					counter--;
				}
				if(counter == GAME_OVER) {
					gameOver();
				}
			} 
			else {
				if (flag) {
					alert(textField);
				}
			}
		}
	}
	
	/**
     *returns the row index of a given cell
     *@param index - the index of the TextField in the array
     *@return row index
     */
	private int getRow(int index) {
		return index / SIZE;
	}

	/**
     *returns the column index of a given cell
     *@param index - the index of the TextField in the array
     *@return column index
     */
	private int getCol(int index) {
		return index % SIZE;
	}

	/**
     *returns the index of a given TextField in the array
     *@param textField - a given TextField
     *@return index - index of the TextField, or -1 if there
     *is no such TextField
     */
	private int findIndex(TextField textField) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			if (textFields[i] == textField) {
				return i;
			}
		}
		return -1;
	}

	/**
     *checks if the entered value to a cell is legal
     *@param textField - the TextField the value was entered to
     *@return boolean - true if legal, or false otherwise
     */
	private boolean isLegal(TextField textField) {
		int index = findIndex(textField);
		//try - if the entered value is a number between 1-9
		try {
			// check rows
			for (int i = getRow(index) * SIZE; i < ((getRow(index) + 1) * SIZE) - 1; i++) {
				if (!textFields[i].getText().isEmpty()) {
					if (getNum(textFields[i]) == getNum(textField) && !textFields[i].equals((TextField) textField)) {
						return false;
					}
				}
			}
			// check columns
			for (int i = getCol(index); i < SIZE * SIZE; i += 9) {
				if (!textFields[i].getText().isEmpty()) {
					if (getNum(textFields[i]) == getNum(textField) && !textFields[i].equals((TextField) textField)) {
						return false;
					}
				}
			}
		} 
		//catch - if the value is not a number between 1-9
		catch (IllegalArgumentException e) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText("Please enter a number between 1-9");
			textField.clear();
			alert.showAndWait();
			flag = false;
			return false;
		}
		return true;
	}

	/**
     *checks if the entered value to a cell is between 1-9
     *@param textField - the TextField the value was entered to
     *@throws NumberFormatException - if the value is not a number
     *@return num - the entered number, or throws if not a number between 1-9
     */
	private int getNum(TextField textField) throws NumberFormatException {
		int num = Integer.parseInt(textField.getText());
		if (num >= LOWEST && num <= HIGHEST) {
			return num;
		} else {
			throw new NumberFormatException();
		}

	}

	@FXML
	/**
     *clears the cells 
     *@param event - when the clear button is pressed
     */
	void clearTable(ActionEvent event) {
		//new table - so it's not set
		tableSet = false;
		//counter is refreshed back to 81
		counter = SIZE*SIZE;
		//clear and enable all cells
		for (int i = 0; i < SIZE * SIZE; i++) {
			textFields[i].clear();
			textFields[i].setDisable(false);
			textFields[i].setStyle("-fx-border-color: black;");
		}
		//color them accordingly
		colorCells();
	}
	
	/**
     *makes the table - new cells 
     */
	private void newBlocks() {
		tableSet = false;
		counter = SIZE*SIZE;
		textFields = new TextField[SIZE * SIZE];
		for (int i = 0; i < SIZE * SIZE; i++) {
			textFields[i] = new TextField();
			textFields[i].setStyle("-fx-border-color: black;");
			textFields[i].setPrefSize(50, 50);
			grid.add(textFields[i], i % SIZE, i / SIZE);
			textFields[i].setAlignment(Pos.CENTER);
			//giving each cell the event upon entering a number
			textFields[i].setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				/**
			     *if enter was pressed - use numAdd
			     *@param event
			     */
				public void handle(KeyEvent event) {
					if (event.getCode() == (KeyCode.ENTER)) {
						flag = true;
						numAdd(event);
					}
				}
			});
		}
	}

	@FXML
	/**
     *sets the table when the user presses on set button
     *@param event - when the set button is pressed
     */
	void setTable(ActionEvent event) {
		//if the user hasn't pressed set yet, otherwise, nothing happens
		if (tableSet == false) {
			//make all cells that has been entered numbers to disabled
			for (TextField textField : textFields) {
				if (!textField.getText().isEmpty()) {
					textField.setDisable(true);
					if(textField.getStyle() == "-fx-background-color: gray; -fx-border-color: black;") {
						textField.setStyle("-fx-background-color: gray;-fx-text-fill: red;-fx-border-color: black;");
					}
					else {
						textField.setStyle("-fx-text-fill: red;-fx-border-color: black;");
					}
				}
			}
			tableSet = true;
		}
	}

}
