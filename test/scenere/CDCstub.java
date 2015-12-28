package scenere;

import java.awt.Point;

import cdc.CDC;
import gameobject.Item;
import gameobject.Player;

public class CDCstub extends CDC {
	private Player players[];
	private Item[] items;
	public CDCstub(){
		super();
	}
	
	public void setPlayerstub(Player[] player){
		this.players = player;
	}
	
	public Player getPlayerstub(int playerID){
		return players[playerID];
	}
	
	public void setItems(Item[] items){
		this.items = items;
	}
	
	public void updatePlayerLocation(int playerID, Point location){
		players[playerID].setPlayerLocation(location);
	}
	
	public Item[] getItemsStub(){
		return items;
	}
	
}
