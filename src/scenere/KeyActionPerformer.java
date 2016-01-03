package scenere;

import gameobject.Player;
import tcpcm.TCPCM;

public class KeyActionPerformer {
	
	private SceneData scenedata;
	private Player player;
	private CharacterMoveListener KeyListener;
	private TCPCM clientToServer;
	
	public KeyActionPerformer(SceneData scenedata, CharacterMoveListener KeyListener, TCPCM clientToServer) {
		this.scenedata = scenedata;
		this.player = scenedata.getPlayer();
		this.KeyListener = KeyListener;
		this.clientToServer = clientToServer;
	}
	
	public void moveCharacter() {
		if(KeyListener.left){
			if(player.location.x>0)
				clientToServer.inputAction(4);
		}
		else if(KeyListener.right){
			if(player.getPlayerLocation().x<scenedata.getMapWidth()-100)
				clientToServer.inputAction(2);
		}
		else if(KeyListener.up){
			if(player.getPlayerLocation().y>0)
				clientToServer.inputAction(1);
		}
		else if(KeyListener.down){
			if(player.getPlayerLocation().y<scenedata.getMapHeight()-100)
				clientToServer.inputAction(3);
		}
		else{
			clientToServer.inputAction(0);
		}
	}
	
	public void placeBomb(){
		if(KeyListener.space){
			KeyListener.space = false;
			clientToServer.inputAction(5);
		}
	}
}
