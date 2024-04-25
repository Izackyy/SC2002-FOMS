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

public class MenuTextDB {
	public static final String SEPARATOR = "|";

    // an example of reading
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

public static void addMenuItem(String filename, MenuItem menuitem) throws IOException {
    List al = readMenuItem(filename);
    al.add(menuitem);
    saveMenuItem(filename, al);
}

public static void removeMenuItem(String filename, MenuItem menuitem) throws IOException {
    List al = readMenuItem(filename);
    al.remove(menuitem);
    saveMenuItem(filename, al);
}

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
  
  /*public void printMenuItem() // should be able to take in branch parameter and filter on the items from the branch
  {
	  String filename = "menu.txt" ;
		try {
			// read file containing Professor records.
			ArrayList al = MenuTextDB.readMenuItem(filename) ;
			for (int i = 0 ; i < al.size() ; i++) {
					MenuItem menuitem = (MenuItem)al.get(i);
					System.out.println("Name " + menuitem.getName() );
					System.out.println("Price " + menuitem.getPrice() );
			}
			
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
  	}*/
 
}

