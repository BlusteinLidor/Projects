import java.util.ArrayList;

/**
 * Maman13EX2
 * @author Lidor Blustein
 * id 314993460
 * 
 * This class represents an order in the menu, an order 
 * is made out of the items ordered
 *
 */

public class Order {
	//items available array
	private ArrayList<Item> items;
	//items ordered array
	private ArrayList<Item> itemsOrdered;
	//items ordered total cost
	private int totalCost;
	
	/**
     *empty order constructor - makes new arrays
     */
	public Order() {
		this.items = new ArrayList<Item>();
		this.itemsOrdered = new ArrayList<Item>();
	}
	
	/**
     *order constructor 
     *@param itemArr - sets the order's available items array
     **@param itemsOrder - sets the order's ordered items array
     */
	public Order(ArrayList<Item> itemArr, ArrayList<Item> itemsOrder) {
		this.items = itemArr;
		this.itemsOrdered = itemsOrder;
		this.totalCost = this.getTotalCost();
	}
	
	/**
     *gets the total cost of the items ordered
     *@return total cost
     */
	public int getTotalCost() {
		int tCost = 0;
		//goes over items ordered array and multiplies
		//an item cost by it's quantity
		for(Item item: itemsOrdered) {
			tCost += item.getCost()*item.getQuan();
		}
		return tCost;
	}

	/**
     *adds an item to the order's array
     *@param item 
     */
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	/**
     *adds an item to the order's item ordered array
     *@param item
     */
	public void addItemOrdered(Item item) {
		this.itemsOrdered.add(item);
	}
	
	/**
     *gets an item in the order's array by index
     *@param index - the item's index in the array
     *@return item 
     */
	public Item getItem(int index) {
		return this.items.get(index);
	}
	
	/**
     *gets the order's available item array
     *@return available items array 
     */
	public ArrayList<Item> getArr(){
		return this.items;
	}
	
	/**
     *gets the order's ordered item array
     *@return ordered items array 
     */
	public ArrayList<Item> getArrOrd(){
		return this.itemsOrdered;
	}
}
