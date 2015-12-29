package spritere;

import gameobject.Character;
import gameobject.Player;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

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
    
    private SpritereData spritereData;
    
    public SPRITERE() {
    	spritereData = new SpritereData();
    	
    	//playerConfiguration
		isMoving = false;
		towards = 2;
		movingRapid = 1;
		moving_p = 0;
		
	}
    
	public void renderSprites(Graphics gra){
		paintPlayer(gra, 1);
		paintPlayer(gra, 2);
		paintPlayer(gra, 3);
		paintPlayer(gra, 4);		
	}
	
	public void paintPlayer(Graphics gra,int playerID){
		//spritereData.loadRoleImage(dom.getPlayer(playerID).getCharacter().getCharacterImg());
		isMoving = dom.getPlayer(playerID).getIsMoving();
		towards = dom.getPlayer(playerID).getDirection();
		playerLocation = dom.getPlayer(playerID).getPlayerLocation();
		positionX = (int) playerLocation.getX();
		positionY = (int) playerLocation.getY();
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
	
	public void paintItem(){
		
	}
	
	public void paintDumpling(){
		
	}
	
	public void setDOM(DOM dom){
		this.dom = dom;
	}
	
}