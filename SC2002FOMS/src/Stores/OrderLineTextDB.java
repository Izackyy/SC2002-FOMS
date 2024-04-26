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

public class OrderLineTextDB {
	
	public static final String SEPARATOR = "|";

    // an example of reading

	/**
	 * Reads the OrderLine data from the specified file and returns a list of OrderLine
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static ArrayList readOrderLine(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store Order data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				int  orderID = Integer.parseInt(star.nextToken().trim());// first token
				String  name = star.nextToken().trim(); // second token
				int  quantity = Integer.parseInt(star.nextToken().trim()); // third token
				String  customisation = star.nextToken().trim(); 
				// create Order object from file data
				OrderLine orderLine = new OrderLine(orderID,name, quantity, customisation);
				// add to Order list
				alr.add(orderLine);
			}
        
			return alr ;
	}

  // an example of saving

  /**
   * Writes the OrderLine data to the specified file.
   * @param filename
   * @param al
   * @throws IOException
   */
public static void saveOrderLine(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store MenuItem data

        for (int i = 0 ; i < al.size() ; i++) {
				OrderLine orderLine = (OrderLine)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(orderLine.getOrderID());
				st.append(SEPARATOR);
				st.append(orderLine.getName().trim());
				st.append(SEPARATOR);
				st.append(orderLine.getQuantity()); //this is a double
				st.append(SEPARATOR);
				st.append(orderLine.getCustomisation().trim());
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}

// an example of adding

/**
 * Adds an OrderLine to the specified file.
 * @param filename
 * @param orderLine
 * @throws IOException
 */
public static void addOrder(String filename, OrderLine orderLine) throws IOException {
    List al = readOrderLine(filename);
    al.add(orderLine);
    saveOrderLine(filename, al);
}

//assume we dont have to remove orderlines,, can keep track of total menuitems sold
	
  /** Write fixed content to the given file. */
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
  // an example of reading

  /**
   * Reads the contents of the given file.
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
