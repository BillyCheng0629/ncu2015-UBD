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
	private int px=0,py=0,dx=0,dy=0;
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
			
			
			
			for(int i=0;i<4;i++){
					if(player[i]!=null&&player[i].getIsMoving()){ //while player is moving 
					py=(int)player[i].location.y/100;
					px=(int)player[i].location.x/100;
					switch (player[i].getDirection()) {
					
					case 1:
						if(checkObs(px,py-1)&&py-1>=0){
						player[i].location.y-=100; //move N
						checkItem(player[i]);//check there is or not an item
						}
						break;
					case 2:
						if(checkObs(px+1,py)&&px+1<=50){
						
							player[i].location.x+=100;//move E
							checkItem(player[i]);
						}
						
						break;
					case 3:
						if(checkObs(px,py+1)&&py+1<=20){
					
						player[i].location.y+=100;//move S
						checkItem(player[i]);
						}
						break;
					case 4:
						if(checkObs(px-1,py)&&px-1>=0){
						
						player[i].location.x-=100;//move w
						checkItem(player[i]);
						}
						break;
					default:
						break;
					}
				}
				
			}
			
			
			//just a counter
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
			if(player[i]!=null){
				myInt = (player[i].getAlive()) ? 1 : 0;
				k+=myInt;
			}
			
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
		dx=(int)dumpling.location.x/100;
		dy=(int)dumpling.location.x/100;
		for(int i=0;i<4;i++){
			
			
			if(player[i]!=null){
				py=(int)player[i].location.y/100;
				px=(int)player[i].location.x/100;
				if(px==dx&&py<dy+dumpling.getPower()&&py>dy-dumpling.getPower()){
					player[i].setAlive(false);
				}
				else if(py==dy&&px<dx+dumpling.getPower()&&px>dx-dumpling.getPower()){
					player[i].setAlive(false);
				}
			}
		}
		for(Object key:items.keySet()){
			if(((int)items.get(key).location.x/100)==((int)dumpling.location.x/100)&&((int)items.get(key).location.y/100)<(((int)dumpling.location.y/100)+dumpling.getPower())&&((int)items.get(key).location.y/100)>
			(((int)dumpling.location.y/100)-dumpling.getPower())){
				items.remove(key);
			}
			else if(((int)items.get(key).location.y/100)==((int)dumpling.location.y/100)&&((int)items.get(key).location.x/100)<(((int)dumpling.location.x/100)+dumpling.getPower())&&((int)items.get(key).location.x/100)>
			(((int)dumpling.location.x/100)-dumpling.getPower())){
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
	public boolean checkObs(int x,int y){
		if(x%2==1&&y%2==1){
			return false;
		}
		else {
			return true;
		}
	}

}