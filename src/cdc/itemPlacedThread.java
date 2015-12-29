package cdc;

import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;


import java.util.Iterator;
import java.util.Map.Entry;

import gameobject.*;

public class itemPlacedThread extends Thread{
	private HashMap<Integer, Item> items;
	private int itemID;
	
	public itemPlacedThread(HashMap<Integer, Item> items){
		this.items=items;//get hashmap
		itemID=0;
	}
	
	public void run(){
		int itemlocation_x=0,itemlocation_y=0;
		boolean locationcheck;
		do{
			itemlocation_x = (int) (Math.random()*51);//random location
			itemlocation_y = (int) (Math.random()*21);
			locationcheck=true;
			for (Entry<Integer,Item> entry : items.entrySet()) {//check repeat item location 
				Item item = entry.getValue();
				Point previtemlocation=item.getLocation();
				if(previtemlocation.x==itemlocation_x&&previtemlocation.y==itemlocation_y)
				    locationcheck=false;
			}
		}while((itemlocation_x%2==1&&itemlocation_x%2==1)||!locationcheck);
		Item newitem=new Item();
		
		
		newitem.setLocation(itemlocation_x,itemlocation_y);	//set item location
		newitem.setType((int) (Math.random()*2));			//random item type
		newitem.setID(itemID);								//set item ID	
		items.put(itemID, newitem);							//add
		itemID++;											//set next itemID
		
		//sleep
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

