package cdc;

import java.util.HashMap;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument.Iterator;

import gameobject.*;

public class UpdateThread extends Thread{
	private boolean isContinue = true;
	private HashMap<Integer, Dumpling>  dumplings;
	private HashMap<Integer, Item> items;
	private Queue<Object> deleteQueue;
	private Player player[];
	private int moveCount=4;
	private int time;
	private boolean gameState;
	public void run() {
		while(gameState()){
			for(Object key:dumplings.keySet()){
				if(dumplings.get(key).getCount()==0){ //while count is count to 0 
					deleteQueue.add(dumplings.get(key)); //add to delete queue send to udp 
					checkBombEffecet(dumplings.get(key));//check bomb's effect
					dumplings.remove(key); //remove it
				}
				else{
					dumplings.get(key).setCount(dumplings.get(key).getCount()-50); //if count != 0 decrease count
				}
			}
			
			
			if(moveCount==0){ //just a counter
			for(int i=0;i<4;i++){
				if(player[i].getIsMoving()){ //while player is moving 
					switch (player[i].getDirection()) {
					case 1:
						player[i].location.y+=1; //move N
						checkItem(player[i]);//check there is or not an item
						break;
					case 2:
						player[i].location.x+=1;//move E
						checkItem(player[i]);
						break;
					case 3:
						player[i].location.y-=1;//move S
						checkItem(player[i]);
						break;
					case 4:
						player[i].location.x-=1;//move w
						checkItem(player[i]);
						break;
					default:
						break;
					}
				}
				moveCount=4;//just a counter
			}
			}
			
			moveCount--;//just a counter
			time-=50;// time counter
			try{
				UpdateThread.sleep(50);
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void setDumplings(HashMap<Integer, Dumpling> dumplings){
		this.dumplings=dumplings;
	}
	public void setPlayer(Player player[]){
		this.player=player;
	}
	public void setTime(int time){
		this.time=time;
	}
	public void setItems(HashMap<Integer, Item> items){
		this.items=items;
	}
	public void setGameState(boolean gameState){
		this.gameState=gameState;
	}
	public boolean gameState(){
		
		int k=0;
		int myInt;
		for(int i=0;i<4;i++){ //check alive amoumt
			myInt = (player[i].getAlive()) ? 1 : 0;
			k+=myInt;
		}
		if(k>1){ //if survied >1 game keepgoing
		gameState=true; 
		return gameState;
				}
		else{//if not game is end
		gameState=false;
		return gameState;
		}
	}
	public void checkBombEffecet(Dumpling dumpling){
		for(int i=0;i<4;i++){
			if(player[i].location.x==dumpling.location.x&&player[i].location.y<(dumpling.location.y+dumpling.getPower())&&player[i].location.y>(dumpling.location.y-dumpling.getPower())){
				player[i].setAlive(false);    
			}
			else if(player[i].location.y==dumpling.location.y&&player[i].location.x<(dumpling.location.x+dumpling.getPower())&&player[i].location.x>(dumpling.location.x-dumpling.getPower())){
				player[i].setAlive(false);
			}
		}
		for(Object key:items.keySet()){
			if(items.get(key).location.x==dumpling.location.x&&items.get(key).location.y<(dumpling.location.y+dumpling.getPower())&&items.get(key).location.y>(dumpling.location.y-dumpling.getPower())){
				items.remove(key);
			}
			else if(items.get(key).location.y==dumpling.location.y&&items.get(key).location.x<(dumpling.location.x+dumpling.getPower())&&items.get(key).location.x>(dumpling.location.y-dumpling.getPower())){
				items.remove(key);
			}
		}
	}
	public void setDeleteQueue(Queue<Object> deleteQueue){
		this.deleteQueue=deleteQueue;
	}
	public void checkItem(Player player){
		for(Object key:items.keySet()){
			if(items.get(key).location==player.location){
				switch (items.get(key).getType()) {
				case 0:
					player.setMaxDumplingCount(player.getMaxDumplingCount()+1);
					break;
				case 1:
					player.setPower(player.getPower()+1);
					break;
				default:
					break;
				}
			}
		}
	}
}
