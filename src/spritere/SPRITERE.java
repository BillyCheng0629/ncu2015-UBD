package spritere;

import gameobject.Character;
import gameobject.Player;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

import dom.DOM;

public class SPRITERE{
	private DOM dom;
	//for keylistener
    private boolean isMoving;
    //decide the direction:1 for North, 2 for East, 3 for South, 4 for West
    private int towards;
    private double movingRapid;
    //time of keyboard pressing
    private int moving_p;  
    
    private SpritereData spritereData;
    
    public SPRITERE() {
		isMoving = false;
		towards = 2;
		movingRapid = 1;
		moving_p = 0;
	}
    
	public void renderSprites(Graphics gra, int playerID){
		isMoving = dom.getPlayer(playerID).getIsMoving();
		towards = dom.getPlayer(playerID).getDirection();
		if(isMoving){ moving_p++; }
		if(moving_p>40){ moving_p = 0; }
		paintPlayer(gra, playerID);
	}
	
	public void paintPlayer(Graphics gra, int playerID){
		spritereData = new SpritereData(dom.getPlayer(playerID).getCharacter().getCharacterImg());
		Image tempImage = spritereData.getRoleIcon().getImage();
    	if(!isMoving){
    		switch (towards) {
			case 1://up
				gra.drawImage(tempImage, 64, 64,64*2,64*2,  64,64*3,64*2,64*4, null);break;
			case 2://right
				gra.drawImage(tempImage, 64, 64,64*2,64*2,  64,64*2,64*2,64*3, null);break;
			case 3://down
				gra.drawImage(tempImage, 64, 64,64*2,64*2,  64,64*0,64*2,64*1, null);break;
			case 4://left
				gra.drawImage(tempImage, 64, 64,64*2,64*2,  64,64*1,64*2,64*2, null);break;
			default:break;
			}    		
    	}
    	else{
    		switch (towards) {
			case 1://North
				if(moving_p<10*movingRapid){ gra.drawImage(spritereData.getRoleIcon().getImage(), 64, 64,64*2,64*2,  64*0,64*3,64*1,64*4, null);}//dx1,dy1,dx2,dy2 ,sx1,sy1,sx2,sy2
				else if(moving_p<20*movingRapid ||moving_p>=30*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*1,64*3,64*2,64*4, null);}
				else if(moving_p<30*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*2,64*3,64*3,64*4, null);}	
				break;
				
			case 2://East
				if(moving_p<10*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*0,64*2,64*1,64*3, null);}
				else if(moving_p<20*movingRapid ||moving_p>=30*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*1,64*2,64*2,64*3, null);}
				else if(moving_p<30*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*2,64*2,64*3,64*3, null);}			
				break;
			
			case 3://South
				if(moving_p<10*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*0,64*0,64*1,64*1, null);}
				else if(moving_p<20*movingRapid ||moving_p>=30*movingRapid ){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*1,64*0,64*2,64*1, null);}
				else if(moving_p<30*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*2,64*0,64*3,64*1, null);}			
				break;
			
			case 4://West
				if(moving_p<10*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*0,64*1,64*1,64*2, null);}
				else if(moving_p<20*movingRapid ||moving_p>=30*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*1,64*1,64*2,64*2, null);}
				else if(moving_p<30*movingRapid){ gra.drawImage(tempImage, 64, 64,64*2,64*2,  64*2,64*1,64*3,64*2, null);}			
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