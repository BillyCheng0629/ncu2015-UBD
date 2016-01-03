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
	private boolean gameState;
	private int itemlocation_x,itemlocation_y;
	private boolean locationcheck;
	public void run(){
		while(gameState){
		itemlocation_x=0;
		itemlocation_y=0;
		
		
		do{
			itemlocation_x = (int) (Math.random()*51);//random location
			itemlocation_y = (int) (Math.random()*21);
			locationcheck=true;
			for (Entry<Integer,Item> entry : items.entrySet()) {//check repeat item location 
				Item item = entry.getValue();
				Point previtemlocation=item.location;
				if(previtemlocation.x==itemlocation_x&&previtemlocation.y==itemlocation_y)
				    locationcheck=false;
			}
		}while((itemlocation_x%2==1&&itemlocation_x%2==1)||!locationcheck);
		Item newitem=new Item();
		
		
		newitem.location.x=itemlocation_x*100;  //set item location
		newitem.location.y=itemlocation_y*100;
		newitem.setType((int) (Math.random()*2));			//random item type
									
		items.put(itemID, newitem);						//add,set id
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
	public void setItems(HashMap<Integer, Item> items){
		itemID=0;
		this.items=items;
	}
	public void setGameStae(boolean gameState){
		this.gameState=gameState;
	}
}