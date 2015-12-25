package scenere;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import gameobject.Player;

public class CharacterMoveListener extends KeyAdapter {
	private SceneData scenedata;
	private Player player;
	
	public CharacterMoveListener(SceneData scenedata){
		super();
		this.scenedata = scenedata;
		player = scenedata.getPlayer();
	}
	
	public void keyPressed(KeyEvent e) {
		moveCharacter(e);
		moveMap(e);
	}
	
	private void moveCharacter(KeyEvent e) {
		switch(e.getKeyCode()){
		case(37):
			if(player.getPlayerLocation().x>0)
				player.getPlayerLocation().x=(player.getPlayerLocation().x-25);
			break;
		case(39):
			if(player.getPlayerLocation().x<scenedata.getMapWidth()-100)
				player.getPlayerLocation().x=(player.getPlayerLocation().x+25);
			break;
		case(38):
			if(player.getPlayerLocation().y>0)
				player.getPlayerLocation().y=(player.getPlayerLocation().y-25);
			break;
		case(40):
			if(player.getPlayerLocation().y<scenedata.getMapHeight()-100)
				player.getPlayerLocation().y=(player.getPlayerLocation().y+25);
			break;
		default:
			break;
		}
	}
	
	private void moveMap(KeyEvent e){
		switch(e.getKeyCode()){
		case(37):
			if(scenedata.getPositionX()>0){
				if(scenedata.getLeftBound()>player.getPlayerLocation().x){
					scenedata.setPositionX(scenedata.getPositionX()-25);
					if(scenedata.getLeftBound()>100){
						scenedata.setLeftBound(scenedata.getLeftBound()-25);
						scenedata.setRightBound(scenedata.getRightBound()-25);
					}
				}
			}
			break;
		case(39):
			if(scenedata.getPositionX()<scenedata.getMapWidth()-scenedata.getFrameWidth()){
				if(scenedata.getRightBound()<(player.getPlayerLocation().x+100)){
					scenedata.setPositionX(scenedata.getPositionX()+25);
					if(scenedata.getRightBound()<scenedata.getMapWidth()-100){
						scenedata.setRightBound(scenedata.getRightBound()+25);
						scenedata.setLeftBound(scenedata.getLeftBound()+25);;
					}
				}
			}
			break;
		case(38):
			if(scenedata.getPositionY()>0){
				if(scenedata.getTopBound()>player.getPlayerLocation().y){
					scenedata.setPositionY(scenedata.getPositionY()-25);
					if(scenedata.getTopBound()>100){
						scenedata.setTopBound(scenedata.getTopBound()-25);
						scenedata.setBottomBound(scenedata.getBottomBound()-25);
					}
				}
			}
			break;
		case(40):
			if(scenedata.getPositionY()<scenedata.getMapHeight()-scenedata.getFrameHeight()){
				if(scenedata.getBottomBound()<(player.getPlayerLocation().y+100)){
					scenedata.setPositionY(scenedata.getPositionY()+25);
					if(scenedata.getBottomBound()<scenedata.getMapHeight()-100){
						scenedata.setBottomBound(scenedata.getBottomBound()+25);
						scenedata.setTopBound(scenedata.getTopBound()+25);;
					}
				}
			}
			break;
		case(49):
		case(50):
		case(51):
		case(52):
			scenedata.setMapType(e.getKeyCode()-49);
			break;
		case(KeyEvent.VK_Q):
			scenedata.getJFrame().dispatchEvent(new WindowEvent(scenedata.getJFrame(), WindowEvent.WINDOW_CLOSING));
			break;
		default:
			break;
		}
		//System.out.println(e.getKeyCode()+"X:"+positionx+"Y:"+positiony);
	}
	
}
