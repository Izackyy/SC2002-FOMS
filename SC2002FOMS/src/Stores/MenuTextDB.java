package Stores;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Enums.Availability;
import Enums.OrderStatus;

/**
 * MenuTextDB is a utility class that reads and writes MenuItem data to and from
 * a text file.
 */
public class MenuTextDB {
	public static final String SEPARATOR = "|";

    // an example of reading

	/**
	 * Reads the MenuItem data from the specified file and returns a list of MenuItem
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static ArrayList readMenuItem(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store MenuItem data into a menu

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				String  name = star.nextToken().trim();	// first token
				double  price = Double.parseDouble(star.nextToken().trim());	// second token
				String  branch = star.nextToken().trim(); // third token
				String  category = star.nextToken().trim(); // fourth token // to change to enum
				String description = star.nextToken().trim();
				Availability availability = Enums.Availability.valueOf(star.nextToken().trim());
				// create MenuItem object from file data
				MenuItem menuitem = new MenuItem(name, price, branch, category, description, availability);
				// add to Professors list
				alr.add(menuitem);
			}
        
			return alr ;
	}

  // an example of saving

  /**
   * Writes the MenuItem data to the specified file.
   * @param filename
   * @param al
   * @throws IOException
   */
public static void saveMenuItem(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store MenuItem data

        for (int i = 0 ; i < al.size() ; i++) {
				MenuItem menuitem = (MenuItem)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(menuitem.getName().trim());
				st.append(SEPARATOR);
				st.append(menuitem.getPrice()); //this is a double
				st.append(SEPARATOR);
				st.append(menuitem.getBranch().trim()); //this is a double
				st.append(SEPARATOR);
				st.append(menuitem.getCategory());
				st.append(SEPARATOR);
				st.append(menuitem.getDescription());
				st.append(SEPARATOR);
				st.append(menuitem.getAvailability());
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}
	
	/**
	 * Adds a new menu item to the menu.
	 * @param filename The name of the file to write to.
	 * @param menuitem The menu item to add.
	 * @throws IOException If an error occurs during file operations.
	 */

public static void addMenuItem(String filename, MenuItem menuitem) throws IOException {
    List al = readMenuItem(filename);
    al.add(menuitem);
    saveMenuItem(filename, al);
}

/**
 * Removes a menu item from the menu.
 * @param filename The name of the file to write to.
 * @param menuitem The menu item to remove.
 * @throws IOException If an error occurs during file operations.
 */

public static void removeMenuItem(String filename, MenuItem menuitem) throws IOException {
    List al = readMenuItem(filename);
    al.remove(menuitem);
    saveMenuItem(filename, al);
}
/**
 * Updates a menu item in the menu.
 * @param filename The name of the file to write to.
 * @param oldItem The old menu item to update.
 * @param newItem The new menu item to update.
 * @throws IOException If an error occurs during file operations.
 */

public static void updateMenuItem(String filename, MenuItem oldItem, MenuItem newItem) throws IOException {
    List al = readMenuItem(filename);
    if (al.contains(oldItem)) {
        int index = al.indexOf(oldItem);
        al.set(index, newItem);
        saveMenuItem(filename, al);
    } else {
        System.out.println("Item not found.");
    }
}


  /** Write fixed content to the given file. */
  public static void write(String fileName, List data) throws IOException  
  {
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

