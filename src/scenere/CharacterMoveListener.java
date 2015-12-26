package scenere;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
}
