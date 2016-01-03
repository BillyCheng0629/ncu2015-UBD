package cdc;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

import javax.management.Query;

import gameobject.Dumpling;
import gameobject.Item;
import gameobject.Player;

public class CDC {
	Player[] player = new Player[4];
	HashMap<Integer,Item> items = new HashMap<Integer, Item>();
	HashMap<Integer,Dumpling> dumplings = new HashMap<Integer, Dumpling>();
	Item item;
	Dumpling dumpling;
	UpdateThread updateThread;
	itemPlacedThread itemPlacedThread;
	Queue<Object> deleteQueue = new LinkedList<Object>();
	int dumplingCount=0;
	int time =90000;
	boolean gameState;
	int mapType;
	int tempx,tempy;

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
	public Queue<Object> getDeleteQueue(){
		return deleteQueue;
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
		dumpling=new Dumpling();
		//Random random=new Random();
		player[playerID].setCurrentDumplingCount(player[playerID].getCurrentDumplingCount()+1);
		dumpling.setPower(player[playerID].getPower());

		dumpling.location=new Point(player[playerID].location.x, player[playerID].location.y);
		dumpling.setID(dumplingCount);
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
	
	public Vector<Object> getUpdateInfo(){
		Vector<Object> infos = new Vector<Object>(); 
		for(int i=0;i<4;i++){
			//infos.add("Player ");
			infos.add(player[i]);
		}
		for(Object key:items.keySet()){
			//infos.add("Item ");
			infos.add(items.get(key));
		}
		for(Object key:dumplings.keySet()){
			infos.add(dumplings.get(key));
		}
		infos.add("TIME "+time);
		return infos;
	}
	class timeCountThread extends Thread{
		
	}
	public void cleanState(){
		for(int i=0;i<4;i++){
			if(player[i]!=null){
				player[i].setAlive(true);
				player[i].setCurrentDumplingCount(0);
				player[i].setIsMoving(false);
				player[i].setIsReady(false);
				player[i].setDirection(3);
				player[i].setPower(1);
				player[i].setMaxDumplingCount(1);
			}
		}
		
		for(Player p : player){
			if(p!=null){
				switch(p.getID()){
				case(0):
					p.setPlayerLocation(new Point(0,0));
					break;
				case(1):
					p.setPlayerLocation(new Point(5000, 0));
					break;
				case(2):
					p.setPlayerLocation(new Point(0, 2000));
					break;
				case(3):
					p.setPlayerLocation(new Point(5000, 2000));
					break;
				default:
					break;
				}
			}
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
		updateThread.setDumplingCount(dumplingCount);
		updateThread.start();
	}
	public void startItemPlacedThread(){
		itemPlacedThread = new itemPlacedThread();
		itemPlacedThread.setItems(items);
		itemPlacedThread.start();
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