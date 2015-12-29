package cdc;

import java.util.HashMap;
import java.util.Queue;
import java.util.Vector;

import javax.management.Query;

import gameobject.Dumpling;
import gameobject.Item;
import gameobject.Player;

public class CDC {
	Player[] player = new Player[4];
	HashMap<Integer,Item> items;
	Vector<Dumpling> dumplings;
	Item item;
	Dumpling dumpling;
	UpdateThread updateThread;
	Queue<Object> deleteQueue;
	int time =90;

	public void addPlayer(Player player, int playerID){
		this.player[playerID] = player;
	}
	public void initGame(){
	
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
		item.location.setLocation(0, 0);
		//items.put(key, value)
	}
	
	public HashMap<Integer,Item> getItems(){
		return items;
	}
	
	public void removeItem(int itemID){
		items.remove(itemID);
	}
	public boolean gameState(){
		boolean flag;
		int k=0;
		int myInt;
		for(int i=0;i<4;i++){
			myInt = (player[i].getAlive()) ? 1 : 0;
			k+=myInt;
		}
		if(k>1){
		return true;
				}
		else{
		return false;
		}
	}
	public void placedDumpling(int playerID){
		player[playerID].setCurrentDumplingCount(player[playerID].getCurrentDumplingCount()+1);
		dumpling.location=player[playerID].getPlayerLocation();
		dumplings.add(dumpling);
	}

	public void updateDirection(int playerID, int direction){
		player[playerID].setDirection(direction);
	}
	
	public Vector getUpdateInfo(){
		Vector infos = null; 
		for(int i=0;i<4;i++){
			infos.add("Player ");
			infos.add(player[i]);
		}
		for(int i=0;i<items.size();i++){
			infos.add("Item ");
			infos.add(items);
		}
		infos.add("Time "+time);
		return infos;
	}
	class timeCountThread extends Thread{
		
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
