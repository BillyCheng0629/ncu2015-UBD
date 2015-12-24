package cdc;

import java.util.Vector;

import gameobject.Item;
import gameobject.Player;

public class CDC {
	Player[] player = new Player[4];
	Vector<Item> items;
	UpdateThread updateThread;
	
	public void addPlayer(Player player, int playerID){
		this.player[playerID] = player;
	}
	
	public Player getPlayer(int playerID){
		return this.player[playerID];
	}
	
	public int getPlayerX(int playerID){
		return player[playerID].getPlayerLocation().x;
	}
	
	public int getPlayerY(int playerID){
		return player[playerID].getPlayerLocation().y;
	}
	
	public int getPlayerSpeed(int playerID){
		return player[playerID].getSpeed();
	}
	
	public int getPlayerPower(int playerID){
		return player[playerID].getPower();
	}
	
	public int getPlayerMaxDumplingCount(int playerID){
		return player[playerID].getCurrentDumplingCount();
	}
	
	public void setPlayerMaxDumplingCount(int playerID, int count){
		player[playerID].setCurrentDumplingCount(count);
	}
	
	public void removePlayer(int playerID){
		this.player[playerID] = null;
	}
	
	public void addItem(Item item){
		items.add(item);
	}
	
	public Vector<Item> getItems(){
		return items;
	}
	
	public void removeItem(int itemID){
		items.remove(itemID);
	}
	
	public void updateDirection(int playerID, int direction){
		player[playerID].setDirection(direction);
	}
	
	public Vector<String> getUpdateInfo(){
		Vector<String> infos = null; 
		return infos;
	}
	
	public void startUpdatingThread(){
		
	}
	/* player {
	 *   type: player,
	 *   playerId : 1~4,
	 *   x : 50,
	 *   y : 100,
	 *   direction : 1~4,
	 *   speed : 1~10,
	 *   power : 1~10,
	 *   maxDumplingCount : 1~10,
	 *   alive : 1 or 0
	 * }
	 *
	 * Item {
	 *   type : item,
	 *   itemType : 1,
	 *   x : 30,
	 *   y : 50
	 *   
	 * }
	 */
	// type(player),playerID,x,y,speed,power,maxDumplingCount
	// type(item),itemType.x,y
}
