import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

/**
 * Maman13EX2
 * @author Lidor Blustein
 * id 314993460
 * 
 * This class represents an item in the menu
 *
 */

public class Item {
	//item name
	private String name;
	//item type
	private String type;
	//item cost
	private int cost;
	//item quantity the user chosen
	private int quantity;
	//the checkbox that's connected to the item
	private CheckBox chBox;
	//the combobox that's connected to the item
	private ComboBox coBox;
	//the combobox's available values
	private final int[] CO_BOX_QUAN = {1,2,3,4,5};
	
	/**
     *item empty constructor
     */
	public Item() {
		//makes a new checkbox with no text
		this.chBox = new CheckBox("");
		//makes a new combobox
		this.coBox = new ComboBox();
		//sets the combobox to have values
		makeCoBox(this.coBox);
		//sets the combobox default value to 1
		coBox.setValue(1);
	}
	
	/**
     *item constructor 
     *@param name - sets the item's name
     **@param type - sets the item's type
     **@param cost - sets the item's cost
     */
	public Item(String name, String type, String cost) {
		this.name = name;
		this.type = type;
		//converts the cost string to Integer(auto converted to int)
		this.cost = Integer.parseInt(cost);
		this.quantity = 0;
		//makes a new checkbox with the text name + cost
		this.chBox = new CheckBox(this.name + " " + this.cost);
		this.coBox = new ComboBox();
		makeCoBox(this.coBox);
		coBox.setValue(1);
	}
	
	/**
     *gets the item's cost
     *@return cost - item's cost
     */
	public int getCost() {
		return this.cost;
	}
	
	/**
     *sets the combobox to have values
     *@param coBox - the combobox to add the values to
     */
	private void makeCoBox(ComboBox coBox) {
		for(int element : CO_BOX_QUAN) {
			coBox.getItems().add(element);
		}
	}
	
	/**
     *gets the item's quantity
     *@return quantity - item's quantity
     */
	public int getQuan() {
		return this.quantity;
	}
	
	/**
     *gets the item's name
     *@return name - item's name
     */
	public String getName() {
		return this.name;
	}
	
	/**
     *gets the item's type
     *@return type - item's type
     */
	public String getType() {
		return this.type;
	}
	
	/**
     *gets the item's ComboBox
     *@return ComboBox - item's ComboBox
     */
	public ComboBox getCoBox() {
		return this.coBox;
	}
	
	/**
     *gets the item's CheckBox
     *@return CheckBox - item's CheckBox
     */
	public CheckBox getChBox() {
		return this.chBox;
	}
	
	/**
     *sets the item's combobox's default value to 1
     *and entering the optional values to it
     */
	public void setCoBox() {
		this.coBox = new ComboBox();
		for(int element : CO_BOX_QUAN) {
			this.coBox.getItems().add(element);
		}
		coBox.setValue(1);
	}
	
	/**
     *sets the item's checkbox's text
     */
	public void setChBox() {
		this.chBox = new CheckBox(this.name + " " + this.cost);
	}
	
	/**
     *sets the item's name by the given string
     *@param name - the name given
     */
	public void setName(String name) {
		this.name = name;
		//change the checkbox's text too
		this.chBox.setText(name);
	}
	
	/**
     *sets the item's type by the given string
     *@param type - the type given
     */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
     *sets the item's cost by the given string
     *@param cost - the cost given
     */
	public void setCost(String cost) {
		this.cost = Integer.parseInt(cost);
	}
	
	/**
     *sets the item's quantity by the given string
     *@param quantity - the quantity given
     */
	public void setQuan(int quantity) {
		this.quantity = quantity;
	}
}
