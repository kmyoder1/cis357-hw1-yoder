/**
 * @author Katlyn Yoder 
 * 
 * The Item class contains information and methods to store and extract item information. 
 * All data is read from a file in the main class and stored in the Item constructor. 
 */
public class Item {
	
	/**
	 * itemCode stores the item code
	 * itemName stores the item name 
	 * unitPrice stores the price for one item
	 */
	private int itemCode;
	private String itemName;
	private double unitPrice;
	
	/**
	 * Constructor for Item 
	 * 
	 * @param itemCode the code for the item
	 * @param itemName the name of the item 
	 * @param unitPrice the price for one item
	 */
	public Item(int itemCode, String itemName, double unitPrice) {
		this.itemCode = itemCode; 
		this.itemName = itemName; 
		this.unitPrice = unitPrice; 
	}
	
	/**
	 * Returns the item code of an item 
	 *
	 * @return the item code
	 */
	public int getItemCode() {
		return itemCode;
	}
	
	/**
	 * Returns the name of an item 
	 *
	 * @return the item name
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Returns the price of an item 
	 *
	 * @return the item price
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

}
