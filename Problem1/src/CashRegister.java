// Homework 1: POST Program
// Course: CIS357
// Due date: 7/5/22
// Name: Katlyn Yoder
// GitHub: https://github.com/kmyoder1/cis357-hw1-yoder
// Instructor: Il-Hyung Cho
// Program description: A program that emulates a cash register at a grocery store.

import java.io.File; 
import java.util.Scanner;

/**
 * @author Katlyn Yoder
 * 
 * The CashRegister class contains three methods. The main method is where item data is read from a file and 
 * stored into itemArray and saleArray. It also reads item input from the user and stores that information 
 * into saleArray. It then reads back the item information in saleArray and computes the total and change due 
 * to the user. The sortSale method takes the saleArray information and sorts it in alphabetical order. The 
 * isNumeric method determines if the user input is of numerical value or not. 
 */
public class CashRegister {
	
	/**
	 * Main method where data is read from a file and input take from the user to construct a 
	 * cash register receipt from a grocery store. 
	 * 
	 * @param args
	 * @throws java.io.IOException
	 */
    public static void main(String[] args) throws java.io.IOException {
    	
    	/** Description of declared variables */
    	int count = 0; 
        int productCode = 0;
        int quantity = 0; 
        double itemTotal;
        double subtotal;
        double finalTotal; 
        double tenderAmount;
        double change; 
        double accumulativeTotal = 0;
        int[] numArray; 
        String temp;
        boolean flag = false;
        
        /*
         * Create itemArray and saleArray.
         * Read input from a file and store into itemArray and saleArray.
         */
        
    	// Create object array for items 
        Item[] itemArray = new Item[10];
        // Create object array to track sales
        Sale[] saleArray = new Sale[10];
        
		//Create a File instance
		File file = new File("C:\\Users\\Katlyn\\OneDrive\\Desktop\\Problem1.txt");
		//Create a Scanner for the file
		Scanner fileInput = new Scanner(file);
        
        // Read data from a file and store in array 
        while (fileInput.hasNext()) {
            temp = fileInput.nextLine();
            int itemCode = Integer.valueOf(temp);
            String itemName = fileInput.nextLine();
            temp = fileInput.nextLine();
            double unitPrice = Double.parseDouble(temp);
            
            // Create Item object 
            itemArray[count] = new Item(itemCode, itemName, unitPrice);
            // Create Sale object
            saleArray[count] = new Sale(itemName);
            // Increment count
            count++;            
        }
        
		// Close file
		fileInput.close();
		
		// Create new scanner object to read input from user
		Scanner input = new Scanner(System.in);
        
		/*
		 * Begin the POST system. Ask user for input and store information into saleArray. 
		 * Compute total and change. Give an itemized list of all item added to sale. 
		 * Handles improper input type without throwing an exception. 
		 */
		
		// Prompt user to begin a new sale 
        System.out.printf("Welcome to Yoder cash register system!\n");
        System.out.printf("\nBeginning a new sale (Y/N) ");
        String userOption = input.next().toUpperCase();
        
        // Loop while user chooses to begin a new sale
        while (userOption.equals("Y")) {
        	System.out.printf("--------------------");
        	// Loops until input is valid
        	// Valid input is a number 1 - 10
        	flag = false;
        	while (flag == false) {
        		// Prompt user to enter product code 
        		System.out.printf("\nEnter product code: ");
        		temp = input.next();
        		// Determine if input is valid
        		if (isNumeric(temp) == true) {
        			productCode = Integer.valueOf(temp);
        			flag = true;
        			if (productCode < -1 || productCode > 10) {
        				flag = false; 
        				// Display invalid message 
        				System.out.printf("!!! Invalid product code");
        			}
        		} else {
        			// Display invalid message 
        			System.out.printf("!!! Invalid product code");
        			flag = false;
        		}
        	}
    		// Loop until user enter -1 and wishes to end 
        	while (productCode != -1) {
        		// Display item name 
        		System.out.printf("%20s", "item name: ");
        		System.out.printf(itemArray[productCode -1].getItemName());
        		flag = false; 
        		// Loops until input is valid
        		// Valid input is a number 
            	while (flag == false) {
            		// Prompt user to enter quantity 
            		System.out.printf("%-21s", "\nEnter quantity:");
            		temp = input.next();
            		// Determine if input is valid 
            		if (isNumeric(temp) == true) {
            			quantity = Integer.valueOf(temp);
            			flag = true;
            		} else {
            			// Display invalid message 
            			System.out.printf("!!! Invalid product code\n");
            			flag = false;
            		}
            	}
            	// Store quantity in saleArray
        		saleArray[productCode - 1].setSaleQuantity(quantity);
        		// Calculate itemTotal and store it in saleArray
        		itemTotal = quantity * itemArray[productCode -1].getUnitPrice();
        		saleArray[productCode - 1].setSaleTotal(itemTotal);
        		// Display itemTotal
        		System.out.printf("%22s %5.2f", "item total: $ ", itemTotal);
        		flag = false; 
        		// Loops until input is valid
        		// Valid input is a number 
        		while (flag == false) {
            		// Prompt user to enter product code 
            		System.out.printf("\n\nEnter product code: ");
            		temp = input.next();
            		// Determine if input is valid
            		if (isNumeric(temp) == true) {
            			productCode = Integer.valueOf(temp);
            			flag = true;
            			if (productCode < -1 || productCode > 10) {
            				flag = false; 
            				// Display invalid message 
            				System.out.printf("!!! Invalid product code");
            			}
            		} else {
            			// Display invalid message 
            			System.out.printf("!!! Invalid product code");
            			flag = false;
            		}
            	}
        	}
        	
        	subtotal = 0;
        	System.out.printf("----------------------------");
        	System.out.printf("\nItems list:");
        	// Determine how many items were added to the saleArray
        	int j = 0;
        	for (int i = 0; i < 10; i++) {
        		if (saleArray[i].getSaleQuantity() > 0) {
        			j++;
        		}
        	}
        	numArray = new int[j];
        	j = 0;
        	// Store the itemCode for each item in saleArray into numArray
        	// numArray will be used for sorting saleArray
        	for (int i = 0; i < 10; i++) {
        		if (saleArray[i].getSaleQuantity() > 0) {
        			numArray[j] = i;
        			j++;
        		}
        	}
        	
        	// Display sorted array
        	int[] displayArray = sortSale(numArray, saleArray);
         	for (int i = 0; i < numArray.length; i++) {
    			System.out.printf("\n%5d %-13s %1s %6.2f", saleArray[displayArray[i]].getSaleQuantity(), 
    					saleArray[displayArray[i]].getSaleName(), "$", saleArray[displayArray[i]].getSaleTotal());
    			subtotal += saleArray[displayArray[i]].getSaleTotal();
    		}
        	
         	// Display the total with and without tax
        	System.out.printf("\nSubtotal %12s %6.2f", "$", subtotal);
        	finalTotal = subtotal * 1.06;
        	System.out.printf("\nTotal with Tax (6%%) $ %6.2f", finalTotal);
        	// Prompt the user to enter tender amount 
        	System.out.printf("\nTendered amount %5s %2s", "$", "");
        	tenderAmount = input.nextDouble();
        	// Determine if input is valid
        	// Valid if thenderAmount > finalTotal
        	if (tenderAmount - finalTotal < -0.01) {
        		while (tenderAmount - finalTotal < -0.01) {
        			// Prompt user to enter a different tender Amount
        			System.out.printf("\nThat is not enough money. Please try again.\n");
        			System.out.printf("\nTendered amount $ ");
                	tenderAmount = input.nextDouble();
        		}
        	}
        	// Calculate change and display results
        	change = tenderAmount - finalTotal;
        	System.out.printf("Change %14s %6.2f", "$", change);
        	System.out.printf("\n----------------------------");
        	System.out.printf("\n");
        	
        	// Add this transaction total to the running total 
        	accumulativeTotal += finalTotal;
        	
        	// Prompt user to begin new sale 
        	System.out.printf("\nBeginning a new sale (Y/N) ");
            userOption = input.next().toUpperCase();
            
            // Reset all sale quantities to zero for a new transaction 
            for (int i = 0; i < 10; i++) {
            	saleArray[i].setSaleQuantityToZero();
            }
            
        }
        
        // Display running total and thank you message
        System.out.printf("\nThe total sale for the day is  $ %.2f", accumulativeTotal);
        System.out.printf("\n\nThanks for using POST system. Goodbye.");

    }
    
    /**
     * Returns the order saleArray will be displayed in alphabetically. Uses a variation of selectionSort. 
     * 
     * @param	saleArray contains all the sale information for each item 
     * @param 	numArray contains the itemCode for each item in saleArray
     * @return	numArray the sorted array of the itemCode for saleArray
     */
    public static int[] sortSale(int[] numArray, Sale[] saleArray) {
    	for (int i = 0; i < numArray.length; i++) {
    		// Find the minimum 
    		String currentMin = saleArray[numArray[i]].getSaleName();
    		int currentMinIndex = i;
    		for (int j = i + 1; j < numArray.length; j++) {
	    		if (saleArray[numArray[j]].getSaleName().compareTo(currentMin) < 0) {
		    		currentMin = saleArray[numArray[j]].getSaleName();
		    		currentMinIndex = j;
	    		}
    		}
    		// Swap numArray[i] with numArray[currentMinIndex] if necessary
    		if (currentMinIndex != i) {
    			int temp = numArray[currentMinIndex];
    			numArray[currentMinIndex] = numArray[i];
    			numArray[i] = temp;
    		}
    	}    
    	return numArray;
    }
    
    /**
     * Returns true if the string is numerical and false if the string is not numerical.
     * 
     * @param 	str	the string read from user input
     * @return	true or false
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");  
    }
	
}