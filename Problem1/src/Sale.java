/**
 * @author Katlyn Yoder 
 * 
 * The Sale class contains information and methods needed to keep track of all items added to the sale. 
 * There are several getter and setter methods to aid in storing and extracting of information for the sale.
 * This class aids in developing the item list and totals for each item.  
 */
public class Sale {
	
	/**
	 * Sale quantity for item
	 * Sale name for item 
	 * Sale total for item
	 */
	private int saleQuantity;
	private String saleName;
	private double saleTotal;
	
	/**
	 * Constructor for Sale
	 * Sets item quantity and total to zero. Sets name by input. 
	 */
	public Sale(String name) {
		this.saleQuantity = 0;
		this.saleName = name;
		this.saleTotal = 0;
	}
	
	/**
	 * Returns the quantity of an item that was added to the sale
	 *
	 * @return the quantity of an item
	 */
	public int getSaleQuantity() {
		return saleQuantity;
	}
	
	/**
	 * Returns the name of an item that was added to the sale
	 *
	 * @return the name of an item
	 */
	public String getSaleName() {
		return saleName;
	}
	
	/**
	 * Returns the sale total of an item that was added to the sale
	 *
	 * @return the total price of the item
	 */
	public double getSaleTotal() {
		return saleTotal;
	}
	
	/**
	 * Sets the item quantity for the sale 
	 * 
	 * @param itemQuantity
	 */
	public void setSaleQuantity(int itemQuantity) {
		this.saleQuantity += itemQuantity;
	}
	
	/**
	 * Sets the item total for the sale 
	 * 
	 * @param itemTotal
	 */
	public void setSaleTotal(double itemTotal) {
		this.saleTotal += itemTotal; 
	}
	
	/**
	 * Sets the sale quantity equal to zero
	 */
	public void setSaleQuantityToZero() {
		this.saleQuantity = 0;
	}

}
