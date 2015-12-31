package spritere;

import gameobject.Player;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import scenere.SceneData;
import dom.DOM;

public class SPRITERE{
	private DOM dom;
	//for keylistener
    private boolean isMoving;
    //decide the direction:1 for North, 2 for East, 3 for South, 4 for West
    private int towards;
    private Point playerLocation;
    private int positionX;
    private int positionY;
    private double movingRapid;
    //time of keyboard pressing
    private int moving_p;
    private int deadCounter;
    
    //for items
    private Image itemImages[];
    
    //for dumplings
    private Image dumplingImages[];
    private int stateCounter;
    
    private SpritereData spritereData;
    private SceneData sceneData;
    
    public SPRITERE(SceneData sceneData) {
    	spritereData = new SpritereData();
    	
    	//playerConfiguration
		isMoving = false;
		towards = 2;
		movingRapid = 1;
		moving_p = 0;
		deadCounter = 0;
		//item
		spritereData.loadItemImages();
		itemImages = spritereData.getItemImages();
		//dumplings
		spritereData.loadDumplingImages();
		dumplingImages = spritereData.getDumplingImages();
		this.sceneData = sceneData;
	}
    
	public void renderSprites(Graphics gra, DOM dom){
		setDOM(dom);
		//paint player
		for (Player player : dom.getPlayers()) {
			if(player!=null){			
				if(player.getAlive()){
					paintPlayer(gra, player.getID());
				}
				else if(player.getAlive()){
					paintDeadPlayer(gra, player.getID());
				}
			}
		}
		//paint item
		for (int i = 0; i < dom.getItems().size(); i++) {
			paintItem(gra, dom.getItems().get(i).getID());
		}
		//paint dumpling
		for (int i = 0; i < dom.getDumplings().size(); i++) {
			paintDumpling(gra, dom.getDumplings().get(i).getID());
			//update dumpling
			if(stateCounter==70){
				dom.removeDumpling(dom.getDumplings().get(i).getID());
			}
			else {
				dom.getDumpling(dom.getDumplings().get(i).getID()).setState(stateCounter);
			}	
		}
	}
	
	public void paintPlayer(Graphics gra,int playerID){
		spritereData.loadRoleImage(dom.getPlayer(playerID).getCharacter().getCharacterNum());
		isMoving = dom.getPlayer(playerID).getIsMoving();
		towards = dom.getPlayer(playerID).getDirection();
		playerLocation = dom.getPlayer(playerID).getPlayerLocation();
		positionX = playerLocation.x-sceneData.getPositionX();
		positionY = playerLocation.y-sceneData.getPositionY();
		if(isMoving){ moving_p++; }
		if(moving_p>40){ moving_p = 0; }
		
		Image tempImage = spritereData.getRoleIcon().getImage();
    	if(!isMoving){
    		switch (towards) {
			case 1://North
				gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*3,64*2,64*4, null);break;
			case 2://East
				gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*2,64*2,64*3, null);break;
			case 3://South
				gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*0,64*2,64*1, null);break;
			case 4://West
				gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*1,64*2,64*2, null);break;
			default:break;
			}    		
    	}
    	else{
    		switch (towards) {
			case 1://North
				if(moving_p<10*movingRapid){ gra.drawImage(spritereData.getRoleIcon().getImage(), positionX, positionY, positionX+100, positionY+100,  64*0,64*3,64*1,64*4, null);}//dx1,dy1,dx2,dy2 ,sx1,sy1,sx2,sy2
				else if(moving_p<20*movingRapid ||moving_p>=30*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*1,64*3,64*2,64*4, null);}
				else if(moving_p<30*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*2,64*3,64*3,64*4, null);}	
				break;
				
			case 2://East
				if(moving_p<10*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*0,64*2,64*1,64*3, null);}
				else if(moving_p<20*movingRapid ||moving_p>=30*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*1,64*2,64*2,64*3, null);}
				else if(moving_p<30*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*2,64*2,64*3,64*3, null);}			
				break;
			
			case 3://South
				if(moving_p<10*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*0,64*0,64*1,64*1, null);}
				else if(moving_p<20*movingRapid ||moving_p>=30*movingRapid ){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*1,64*0,64*2,64*1, null);}
				else if(moving_p<30*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*2,64*0,64*3,64*1, null);}			
				break;
			
			case 4://West
				if(moving_p<10*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*0,64*1,64*1,64*2, null);}
				else if(moving_p<20*movingRapid ||moving_p>=30*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*1,64*1,64*2,64*2, null);}
				else if(moving_p<30*movingRapid){ gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64*2,64*1,64*3,64*2, null);}			
				break;
				
			default:break;
			}
    	}	
	}
	
	public void paintDeadPlayer(Graphics gra,int playerID) {
		spritereData.loadRoleImage(dom.getPlayer(playerID).getCharacter().getCharacterNum());
		isMoving = dom.getPlayer(playerID).getIsMoving();
		towards = dom.getPlayer(playerID).getDirection();
		playerLocation = dom.getPlayer(playerID).getPlayerLocation();
		positionX = playerLocation.x-sceneData.getPositionX();
		positionY = playerLocation.y-sceneData.getPositionY();
		Image tempImage = spritereData.getRoleIcon().getImage();
		deadCounter++;
		if(deadCounter>50){deadCounter=50;}
		switch (towards) {
		case 1://North
			if(deadCounter<20 && deadCounter>=10){gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*3,64*2,64*4, null);}
			if(deadCounter<40 && deadCounter>=30){gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*3,64*2,64*4, null);}
			break;
		case 2://East
			if(deadCounter<20 && deadCounter>=10){gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*2,64*2,64*3, null);}
			if(deadCounter<40 && deadCounter>=30){gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*2,64*2,64*3, null);}
			break;
		case 3://South
			if(deadCounter<20 && deadCounter>=10){gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*0,64*2,64*1, null);}
			if(deadCounter<40 && deadCounter>=30){gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*0,64*2,64*1, null);}
			break;
		case 4://West
			if(deadCounter<20 && deadCounter>=10){gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*1,64*2,64*2, null);}
			if(deadCounter<20 && deadCounter>=10){gra.drawImage(tempImage, positionX, positionY, positionX+100, positionY+100,  64,64*1,64*2,64*2, null);}
			break;
		default:break;
		}  
	}
	
	public void paintItem(Graphics gra, int itemID){
		int posX = dom.getItem(itemID).location.x - sceneData.getPositionX();
		int posY = dom.getItem(itemID).location.y - sceneData.getPositionY();
		gra.drawImage(itemImages[dom.getItem(itemID).getType()], posX, posY, posX+100, posY+100, 0,0,100,100, null); 
	}
	
	public void paintDumpling(Graphics gra, int dumplingID){	
		int posX = dom.getDumpling(dumplingID).location.x - sceneData.getPositionX();
		int posY = dom.getDumpling(dumplingID).location.y - sceneData.getPositionY();
		int power = dom.getDumpling(dumplingID).getPower();
		stateCounter = dom.getDumpling(dumplingID).getState();
		stateCounter++;
			
		if(stateCounter<10)		{ gra.drawImage(dumplingImages[0], posX+5, posY+5, posX+95, posY+95, 0,0,100,100, null); } //small
		else if(stateCounter<20){ gra.drawImage(dumplingImages[0], posX, posY, posX+100, posY+100, 0, 0,100,100, null); } //big
		else if(stateCounter<30){ gra.drawImage(dumplingImages[0], posX+5, posY+5, posX+95, posY+95, 0,0,100,100, null); } //small
		else if(stateCounter<40){ gra.drawImage(dumplingImages[0], posX, posY, posX+100, posY+100, 0,0,100,100, null); } //big
		else if(stateCounter<50){ gra.drawImage(dumplingImages[0], posX+5, posY+5, posX+95, posY+95, 0,0,100,100, null); } //small
		else if(stateCounter<60){ gra.drawImage(dumplingImages[0], posX, posY, posX+100, posY+100, 0,0,100,100, null); } //big
		//explosion happen
		else if(stateCounter<70){ 
			//Center
			gra.drawImage(dumplingImages[1],posX, posY, posX+100, posY+100, 0, 0, 100, 100, null);
			//barrier judgment
			int north = (dom.getDumpling(dumplingID).location.y/100)+1;
			int east = (dom.getDumpling(dumplingID).location.x/100)+1;
			int south = (dom.getDumpling(dumplingID).location.y/100)-1;
			int west = (dom.getDumpling(dumplingID).location.x/100-1);
			if( (north%2) == 1 && (south%2) == 1){
				//Middle
				for (int i = 1; i < power; i++) {
					gra.drawImage(dumplingImages[3], posX+(100*i), posY, posX+(100*i)+100, posY+100, 0, 0, 100, 100, null); 
					gra.drawImage(dumplingImages[3], posX-(100*i), posY, posX-(100*i)+100, posY+100, 0, 0, 100, 100, null); 
				}				
				//tail
				gra.drawImage(dumplingImages[5], posX+(100*power), posY, posX+(100*power)+100, posY+100, 0, 0, 100, 100, null);
				gra.drawImage(dumplingImages[7], posX-(100*power), posY, posX-(100*power)+100, posY+100, 0, 0, 100, 100, null); 
			}
			
			else if( (east%2) == 1 && (west%2)==1){
				//Middle
				for (int i = 1; i < power; i++) {
					gra.drawImage(dumplingImages[2], posX, posY+(100*i), posX+100, posY+(100*i)+100, 0, 0, 100, 100, null); 
					gra.drawImage(dumplingImages[2], posX, posY-(100*i), posX+100, posY-(100*i)+100, 0, 0, 100, 100, null);
				}				
				//tail
				gra.drawImage(dumplingImages[4], posX, posY+(100*power), posX+100, posY+(100*power)+100, 0, 0, 100, 100, null); 
				gra.drawImage(dumplingImages[6], posX, posY-(100*power), posX+100, posY-(100*power)+100, 0, 0, 100, 100, null); 
			}
			
			else{
				//Middle
				for (int i = 1; i < power; i++) {
					gra.drawImage(dumplingImages[2], posX, posY+(100*i), posX+100, posY+(100*i)+100, 0, 0, 100, 100, null); 
					gra.drawImage(dumplingImages[2], posX, posY-(100*i), posX+100, posY-(100*i)+100, 0, 0, 100, 100, null);
					gra.drawImage(dumplingImages[3], posX+(100*i), posY, posX+(100*i)+100, posY+100, 0, 0, 100, 100, null); 
					gra.drawImage(dumplingImages[3], posX-(100*i), posY, posX-(100*i)+100, posY+100, 0, 0, 100, 100, null); 
				}
				
				//tail
				gra.drawImage(dumplingImages[4], posX, posY+(100*power), posX+100, posY+(100*power)+100, 0, 0, 100, 100, null); 
				gra.drawImage(dumplingImages[5], posX+(100*power), posY, posX+(100*power)+100, posY+100, 0, 0, 100, 100, null);
				gra.drawImage(dumplingImages[6], posX, posY-(100*power), posX+100, posY-(100*power)+100, 0, 0, 100, 100, null); 
				gra.drawImage(dumplingImages[7], posX-(100*power), posY, posX-(100*power)+100, posY+100, 0, 0, 100, 100, null); 
			}
		}		
	}
	

	public void setDOM(DOM dom){
		this.dom = dom;
	}
	
	
	
}
