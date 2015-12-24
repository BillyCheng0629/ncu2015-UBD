package dom;

import java.util.HashMap;
import java.util.Vector;

import gameobject.Item;
import gameobject.Player;

public class DOM {
	private Player player[];
	private HashMap<Integer, Item> items;
	private int mapType;
	private int gameTime;
	
	public DOM(){
		player = new Player[4];
		items = new HashMap<Integer, Item>();
	}
	
	public void setPlayer(int playerID, Player player){
		this.player[playerID] = player;
	}
	
	public Player getPlayer(int playerID){
		return player[playerID];
	}
	
	public void setMapType(int mapType){
		this.mapType = mapType;
	}
	
	public int getMapType(){
		return mapType;
	}
	
	public void setGameTime(int gameTime){
		this.gameTime = gameTime;
	}
	
	public int getGameTime(){
		return this.gameTime;
	}
	
	public void addItem(int itemID, Item item){
		items.put(itemID, item);
	}
	
	public void removeItem(int itemID){
		items.remove(itemID);
	}
	
	public Item getItem(int itemID){
		return items.get(itemID);
	}
	
	public Vector<String> getAllDOM(){
		Vector<String> temp = new Vector<String>();
		for(Player t : player)
			temp.add(t.toString());
		items.forEach((k,v)->temp.add(v.toString()));
		temp.add("gameTime,"+gameTime);
		return temp;
	}
	
}
