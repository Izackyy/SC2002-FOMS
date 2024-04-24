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

public class OrderTextDB {
	
	public static final String SEPARATOR = "|";

    // an example of reading
	public static ArrayList readOrder(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store Order data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				int  orderID = Integer.parseInt(star.nextToken().trim());	// first token
				OrderStatus OrderStatus = Enums.OrderStatus.valueOf(star.nextToken().trim());	// second token
				String  branch = star.nextToken().trim(); // third token
				// create Order object from file data
				Order order = new Order(orderID, branch);
				// add to Order list
				alr.add(order);
			}
        
			return alr ;
	}

  // an example of saving
public static void saveOrder(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store MenuItem data

        for (int i = 0 ; i < al.size() ; i++) {
				Order order = (Order)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(order.getOrderID());
				st.append(SEPARATOR);
				st.append(order.getOrderStatus()); //this is a double
				st.append(SEPARATOR);
				st.append(order.getBranch().trim()); //this is a double
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}

public static void addOrder(String filename, Order order) throws IOException {
    List al = readOrder(filename);
    al.add(order);
    saveOrder(filename, al);
}

public static void removeOrder(String filename, Order order) throws IOException {
    List al = readOrder(filename);
    al.remove(order);
    saveOrder(filename, al);
}

public static void updateOrder(String filename, Order oldOrder, Order newOrder) throws IOException {
    List al = readOrder(filename);
    if (al.contains(oldOrder)) {
        int index = al.indexOf(oldOrder);
        al.set(index, newOrder);
        saveOrder(filename, al);
    } else {
        System.out.println("Item not found.");
    }
}


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
