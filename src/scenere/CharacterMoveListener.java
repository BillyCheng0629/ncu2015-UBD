package scenere;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gameobject.Player;
import tcpcm.TCPCM;

public class CharacterMoveListener extends KeyAdapter {
	private SceneData scenedata;
	private Player player;
	private TCPCM clientToServer;
	
	public CharacterMoveListener(SceneData scenedata, TCPCM tcpconnection){
		super();
		this.scenedata = scenedata;
		player = scenedata.getPlayer();
		clientToServer = tcpconnection;
	}
	
	public void keyPressed(KeyEvent e) {
		moveCharacter(e);
	}
	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case(KeyEvent.VK_LEFT):
		case(KeyEvent.VK_RIGHT):
		case(KeyEvent.VK_UP):
		case(KeyEvent.VK_DOWN):
			clientToServer.inputAction(0);
			break;
		default:
			break;
		}
	}
	
	private void moveCharacter(KeyEvent e) {
		switch(e.getKeyCode()){
		case(KeyEvent.VK_LEFT):
			if(player.getPlayerLocation().x>0)
				clientToServer.inputAction(3);
			break;
		case(KeyEvent.VK_RIGHT):
			if(player.getPlayerLocation().x<scenedata.getMapWidth()-100)
				clientToServer.inputAction(2);
			break;
		case(KeyEvent.VK_UP):
			if(player.getPlayerLocation().y>0)
				clientToServer.inputAction(1);
			break;
		case(KeyEvent.VK_DOWN):
			if(player.getPlayerLocation().y<scenedata.getMapHeight()-100)
				clientToServer.inputAction(4);
			break;
		case(KeyEvent.VK_SPACE):
			clientToServer.inputAction(5);
			break;
		default:
			break;
		}
	}
}
