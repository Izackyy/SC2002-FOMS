package Services;

import java.util.ArrayList;

import Interfaces.IBranchService;
import Stores.MenuItem;
import Stores.MenuTextDB;


public class BranchService implements IBranchService{
	

	@Override
	public void processOrder() {
		// TODO select order to process, update status 
		
	}
	/*
	@Override
	public void displayNewOrder() {
		
		String filename = "orderline.txt" ;
		try {
			// read file containing Professor records.
			ArrayList al = OrderLineTextDB.readOrder(filename) ;
			for (int i = 0 ; i < al.size() ; i++) {
					MenuItem menuitem = (MenuItem)al.get(i);
					System.out.println("Name " + menuitem.getName() );
					System.out.println("Price " + menuitem.getPrice() );
			}
			
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		
	}*/

	@Override
	public void viewDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayNewOrder() {
		// TODO Auto-generated method stub
		
	}
	

}
