package Stores;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;	
import java.util.Scanner;
import java.util.StringTokenizer;

import Enums.OrderStatus;
/*
 * @author Aaron Mari Santos Solis, Toh Jun Sheng, Dana Yak, Isaac Wong Jia Kai, Jamie Tan Pei Wen
 * @version 1.0
 * @since 2024-04-01
 */
/**
 * OrderLineTextDB is a utility class that reads and writes OrderLine data to and from
 * a text file.
 */

public class OrderTextDB {
	
	public static final String SEPARATOR = "|";

    // an example of reading
    /**
     * Reads the Order data from the specified file and returns a list of Order
     * @param filename
     * @return
     * @throws IOException
     */
	public static ArrayList readOrder(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store Order data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				int  orderID = Integer.parseInt(star.nextToken().trim());	// first token
				String  branch = star.nextToken().trim(); // third token
				OrderStatus orderStatus = Enums.OrderStatus.valueOf(star.nextToken().trim());	// second token
				// create Order object from file data
				Order order = new Order(orderID, branch, orderStatus);
				// add to Order list
				alr.add(order);
			}
        
			return alr ;
	}

  // an example of saving

  /**
   * Writes the Order data to the specified file.
   * @param filename
   * @param al
   * @throws IOException
   */
public static void saveOrder(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store MenuItem data

        for (int i = 0 ; i < al.size() ; i++) {
				Order order = (Order)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(order.getOrderID());
				st.append(SEPARATOR);
				st.append(order.getBranch().trim()); //this is a double
				st.append(SEPARATOR);
				st.append(order.getOrderStatus()); //this is a double
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}
  // an example of adding
  /**
   * Adds an Order to the specified file.
   * @param filename
   * @param order
   * @throws IOException
   */

public static void addOrder(String filename, Order order) throws IOException {
    List al = readOrder(filename);
    al.add(order);
    saveOrder(filename, al);
}
/**
 * Removes an Order from the specified file.
 * @param filename
 * @param order
 * @throws IOException
 */

public static void removeOrder(String filename, Order order) throws IOException {
    List al = readOrder(filename);
    al.remove(order);
    saveOrder(filename, al);
}
/**
 * Updates an Order in the specified file.
 * @param filename
 * @param oldOrder
 * @param newOrder
 * @throws IOException
 */

public static void updateOrder(String filename, Order oldOrder, Order newOrder) throws IOException {
    List al = readOrder(filename);
    if (al.contains(oldOrder)) {
        int index = al.indexOf(oldOrder);
        al.set(index, newOrder);
        saveOrder(filename, al);
    } else {
        System.out.println("Order not found.");
    }
}

  /** Write fixed content to the given file. */
  /**
   * Writes the given data to the specified file.
   * @param fileName
   * @param data
   * @throws IOException
   */
  public static void write(String fileName, List data) throws IOException  {
    PrintWriter out = new PrintWriter(new FileWriter(fileName));

    try {
		for (int i =0; i < data.size() ; i++) {
      		out.println((String)data.get(i));
		}
    }
    finally {
      out.close();
    }
  }

  /** Read the contents of the given file. */
  /**
   * Reads the data from the specified file and returns a list of data.
   * @param fileName
   * @return
   * @throws IOException
   */
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()){
        data.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return data;
  }

}
