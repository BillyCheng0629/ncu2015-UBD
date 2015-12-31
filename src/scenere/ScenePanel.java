package scenere;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import dom.DOM;
import gameobject.Character;
import gameobject.Player;
import spritere.SPRITERE;

@SuppressWarnings("serial")
public class ScenePanel extends JPanel{
	private SceneData scenedata;
	private DOM dom;
	private SPRITERE spritere;
	private Graphics g;
	private KeyActionPerformer performer;
	
	public ScenePanel(){
		super();
		this.setIgnoreRepaint(true);
	}
	
	@Override
	public synchronized void paint(Graphics g){
		super.paint(g);
		this.g = g;
		//testDom();
		g.setColor(Color.BLACK);
		g.setClip(0, 0, scenedata.getPanelWidth(), scenedata.getPanelHeight());
		scenedata.setClientView(scenedata.getVirtualCharacterPosition());
		drawBackground();
		spritere.renderSprites(g, this.dom);
	}
	
	private void testDom(){
		dom = new DOM();
		Player player = new Player();
		player.setID(0);
		player.location = new Point(((int)(Math.random()*10000))%800, ((int)(Math.random()*10000))%600);
		Character test = new Character();
		test.setCharacterNum(1);
		player.setCharacter(test);
		player.setAlive(true);
		player.setIsMoving(true);
		player.setDirection(3);
		dom.updatePlayer(player);
	}
	
	private void drawBackground(){
		for(int i=0-scenedata.getPositionX(), k=0; i<scenedata.getMapWidth()-scenedata.getPositionX(); i+=100, k++){
			for(int j=0-scenedata.getPositionY(), l=0; j<scenedata.getMapHeight()-scenedata.getPositionY(); j+=100, l++){
				g.drawImage(scenedata.getBackImg(scenedata.getBackimg(k, l)), i, j, null);
			}
		}
	}
	
	public void setDOM(DOM dom){
		this.dom = dom;
	}
	
	public DOM getDOM(){
		return dom;
	}
	
	public void setSceneData(SceneData scenedata){
		this.scenedata = scenedata;
		spritere = new SPRITERE(this.scenedata);
	}

	public void setKeyActionPerformer(KeyActionPerformer performer){
		this.performer = performer;
	}
	
	public SceneData getSceneData(){
		return scenedata;
	}
	
	public void moveCharacter() {
		performer.moveCharacter();
	}
	
	public void placeBomb(){
		performer.placeBomb();
	}
}