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
				if(dumplings.get(key).getCount()==0){
					deleteQueue.add(dumplings.get(key));
					checkBombEffecet(dumplings.get(key));
					dumplings.remove(key);
				}
				else{
					dumplings.get(key).setCount(dumplings.get(key).getCount()-50);
				}
			}
			
			
			if(moveCount==0){
			for(int i=0;i<4;i++){
				if(player[i].getIsMoving()){
					switch (player[i].getDirection()) {
					case 1:
						player[i].location.y+=1;
						checkItem(player[i]);
						break;
					case 2:
						player[i].location.x+=1;
						checkItem(player[i]);
						break;
					case 3:
						player[i].location.y-=1;
						checkItem(player[i]);
						break;
					case 4:
						player[i].location.x-=1;
						checkItem(player[i]);
						break;
					default:
						break;
					}
				}
				moveCount=4;
			}
			}
			
			moveCount--;
			time-=50;
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
		for(int i=0;i<4;i++){
			myInt = (player[i].getAlive()) ? 1 : 0;
			k+=myInt;
		}
		if(k>1){
		gameState=true;
		return gameState;
				}
		else{
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
