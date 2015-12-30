package cdc;

import java.awt.Point;
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
	HashMap<Integer,Dumpling> dumplings;
	Item item;
	Dumpling dumpling;
	UpdateThread updateThread;
	itemPlacedThread itemPlacedThread;
	Queue<Object> deleteQueue;
	int dumplingCount=0;
	int time =90000;
	boolean gameState;
	int mapType;

	public void addPlayer(Player player, int playerID){
		this.player[playerID] = player;
	}
	public void initGame(){
		cleanState();
		startItemPlacedThread();
		startUpdatingThread();
		
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
	public boolean getGameState(){
		return gameState;
	}
	
	public void setMapType(int mapType){
		this.mapType = mapType;
	}
	
	public int getMapType(){
		return mapType;
	}
	
	public HashMap<Integer,Item> getItems(){
		return items;
	}
	
	public void removeItem(int itemID){
		items.remove(itemID);
	}
	
	public void placedDumpling(int playerID){
		player[playerID].setCurrentDumplingCount(player[playerID].getCurrentDumplingCount()+1);
		dumpling.location=player[playerID].getPlayerLocation();
		dumplings.put(dumplingCount, dumpling);
		dumplingCount++;
	}

	public void updateDirection(int playerID, int direction){
		if(direction==0){
		player[playerID].setIsMoving(false);
		}
		else{
		player[playerID].setIsMoving(true);
		player[playerID].setDirection(direction);
		}
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
	public void cleanState(){
		for(int i=0;i<4;i++){
			player[i].setAlive(true);
			player[i].setCurrentDumplingCount(0);
			player[i].setIsMoving(false);
			player[i].setIsReady(false);
			player[i].setDirection(0);
			player[i].setPlayerLocation(new Point(i*6, i*10));
			player[i].setPower(1);
			player[i].setMaxDumplingCount(1);
		}
		for(Object key:dumplings.keySet()){
			dumplings.remove(key);
		}
		for(Object key:items.keySet()){
			items.remove(key);
		}
	}
	public void startUpdatingThread(){
		updateThread=new UpdateThread();
		updateThread.setDumplings(dumplings);
		updateThread.setPlayer(player);
		updateThread.setItems(items);
		updateThread.setDeleteQueue(deleteQueue);
		updateThread.setTime(time);
		updateThread.setGameState(gameState);
		updateThread.run();
	}
	public void startItemPlacedThread(){
		itemPlacedThread.setItems(items);
		itemPlacedThread.run();
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