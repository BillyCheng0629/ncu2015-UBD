package cdc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument.Iterator;

import gameobject.*;

public class UpdateThread extends Thread{
	private boolean isContinue = true;
	private HashMap<Integer, Dumpling>  dumplings;
	private HashMap<Integer, Item> items;
	private Queue<Object> deleteQueue;
	private Player player[];
	private int px=0,py=0,dx=0,dy=0,iy=0,ix=0;
	private int time;
	private boolean gameState;
	private int count=4;
	private int dumplingCount;
	private ArrayList<Integer> removeItems;
	private CDC cdc;
	public void run() {
		removeItems = new ArrayList<>();
		
		while(gameState){
			  //java.util.Iterator<Integer> iterate = dumplings.keySet().iterator();
			
			for(Object key:dumplings.keySet()){
				if(dumplings.get(key).getCount()==0){ //while count is count to 0 
					deleteQueue.add(dumplings.get(key)); //add to delete queue send to udp 
					checkBombEffecet(dumplings.get(key));//check bomb's effect
					removeItems.add((Integer)key);
					//dumplings.remove(key); //remove it
				}
				else{
					dumplings.get(key).setCount(dumplings.get(key).getCount()-50); //if count != 0 decrease count
				}
			}
			
			for(int t:removeItems){
				dumplings.remove(t);
				dumplingCount--;
			}
			
			removeItems.clear();
			
			for(int i=0;i<4;i++){
				if(player[i]!=null&&player[i].getIsMoving()){ //while player is moving 
				py=(int)player[i].location.y/100;
				px=(int)player[i].location.x/100;
				switch (player[i].getDirection()) {
				
				case 1:
					if(checkObs(player[i]) && player[i].location.y>0){
					player[i].location.y-=25; //move N
					checkItem(player[i]);//check there is or not an item
					}
					break; 
				case 2:
					if(checkObs(player[i]) && player[i].location.x<5000){
						player[i].location.x+=25;//move E
						checkItem(player[i]);
					}
					break;
				case 3:
					if(checkObs(player[i]) && player[i].location.y<2000){
					player[i].location.y+=25;//move S
					checkItem(player[i]);
					}
					break;
				case 4:
					if(checkObs(player[i]) && player[i].location.x>0){
					player[i].location.x-=25;//move w
					checkItem(player[i]);
					}
					break;
				default:
					break;
				}
			}
				gameState();
				cdc.setGameState(gameState);
		}
			
			
			//just a counter
			if(cdc.time>=50){
			cdc.time-=50;// time counter
			}
			
			count--;
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
	public void setDumplingCount(int dumplingCount){
		this.dumplingCount=dumplingCount;
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
		dy=(int)dumpling.location.y/100;
		for(int i=0;i<4;i++){
			
			
			if(player[i]!=null){
				py=(int)player[i].location.y/100;
				px=(int)player[i].location.x/100;
				if(dx%2==0){
					if(dy%2==0){
						if(dx==px&&py<dy+dumpling.getPower()&&py>dy-dumpling.getPower()){
							player[i].setAlive(false);
						}
						else if(dy==py&&px<dx+dumpling.getPower()&&px>dx-dumpling.getPower()){
							player[i].setAlive(false);
						}
					}
					else {
						if(dx==px&&py<dy+dumpling.getPower()&&py>dy-dumpling.getPower()){
							player[i].setAlive(false);
						}
						else if(dy==py&&dx==px){
							player[i].setAlive(false);
						}
					}
				}
				else{
					if(dy%2==0){
						if(dy==py&&px<dx+dumpling.getPower()&&px>dx-dumpling.getPower()){
							player[i].setAlive(false);
						}
						else if(dy==py&&dx==px){
							player[i].setAlive(false);
						}
					}

				}
			}
		}
		for(Object key:items.keySet()){
			iy=(int)items.get(key).location.y/100;
			ix=(int)items.get(key).location.x/100;
			if(dx%2==0){
				if(dy%2==0){
					if(dx==ix&&iy<dy+dumpling.getPower()&&iy>dy-dumpling.getPower()){
						removeItems.add((Integer) key);
					}
					else if(dy==iy&&ix<dx+dumpling.getPower()&&ix>dx-dumpling.getPower()){
						removeItems.add((Integer) key);
					}
				}
				else {
					if(dx==ix&&iy<dy+dumpling.getPower()&&iy>dy-dumpling.getPower()){
						removeItems.add((Integer) key);
					}
					else if(dy==iy&&dx==ix){
						removeItems.add((Integer) key);
					}
				}
			}
			else{
				if(dy%2==0){
					if(dy==iy&&ix<dx+dumpling.getPower()&&ix>dx-dumpling.getPower()){
						removeItems.add((Integer) key);
					}
					else if(dy==iy&&dx==ix){
						removeItems.add((Integer) key);
					}
				}

			}
		}
		for(int t:removeItems){
			items.remove(t);
			
		}
		
		removeItems.clear();
		
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
	public boolean checkObs(Player player){
		int x,y;
		int xRemain,yRemain;
		x=player.location.x/100;
		y=player.location.y/100;
		xRemain = player.location.x%100;
		yRemain = player.location.y%100;
		switch(player.getDirection()){
		case(1)://���
		case(3)://���
			if(x%2==1 || xRemain>0)
				return false;
			break;
		case(2)://���
		case(4)://���
			if(y%2==1 || yRemain>0)
				return false;
			break;
		}
		return true;
	}
	public void setCDC(CDC cdc){
		this.cdc=cdc;
	}

}