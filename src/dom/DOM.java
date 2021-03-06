
package dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import gameobject.Dumpling;
import gameobject.Item;
import gameobject.Player;

public class DOM {
	private ArrayList<Integer> removeItems;
	private Player player[];
	private HashMap<Integer, Item> items;
	private HashMap<Integer, Dumpling> dumplings; 
	private int mapType;
	private String gameTime;
	private int clientPlayerID;
	
	public DOM(){
		player = new Player[4];
		items = new HashMap<Integer, Item>();
		dumplings = new HashMap<Integer, Dumpling> ();
	}

	public void updatePlayer(Player player){
		//System.out.println("dom update player"+player.getID());
		this.player[player.getID()] = player;
	}
	
	public void removePlayer(int playerID) {
		player[playerID] = null;
	}
	
	public Player getPlayer(int playerID){
		return player[playerID];
	}
	
	public Player[] getPlayers(){
		return player;
	}
	
	public void setMapType(int mapType){
		this.mapType = mapType;
	}
	
	public int getMapType(){
		return mapType;
	}
	
	public void setGameTime(String gameTime){
		this.gameTime = gameTime;
	}
	
	public String getGameTime(){
		return this.gameTime;
	}
	
	public void addItem(Item item){
		if (!(items.containsKey(item.getID())))
			items.put(item.getID(), item);
	}
	
	public void removeItem(int itemID){
		items.remove(itemID);
	}
	
	public Item getItem(int itemID){
		return items.get(itemID);
	}
	
	public HashMap<Integer, Item> getItems(){
		return items;
	}
	
	public void addDumpling(Dumpling dumpling){
		if (!(dumplings.containsKey(dumpling.getID())))
			dumplings.put(dumpling.getID(), dumpling);
	}
	
	public void removeDumpling(int dumplingID){
		dumplings.remove(dumplingID);
	}
	
	public Dumpling getDumpling(int dumplingID){
		return dumplings.get(dumplingID);
	}
	
	public HashMap<Integer, Dumpling> getDumplings(){
		return dumplings;
	}
	
	public void setClientPlayerID(int playerID){
		this.clientPlayerID = playerID;
	}
	
	public int getClientPlayerID(){
		return clientPlayerID;
	}
	
	public Vector<String> getAllDOM(){
		Vector<String> temp = new Vector<String>();
		for(Player t : player)
			temp.add(t.toString());
		items.forEach((k,v)->temp.add(v.toString()));
		temp.add("gameTime,"+gameTime);
		return temp;
	}
	public void cleanState(){
		removeItems = new ArrayList<>();
		for(Object key:dumplings.keySet()){
			removeItems.add((Integer)key);
		}
		for(int t:removeItems){
			dumplings.remove(t);
			
		}
		
		for(Player p : player) {
			if (p != null)
				p.setAlive(true);
		}
		
		removeItems.clear();
		for(Object key:items.keySet()){
			removeItems.add((Integer)key);
		}
		for(int t:removeItems){
			items.remove(t);
		}
	}
	
}

