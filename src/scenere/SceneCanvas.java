package scenere;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dom.DOM;

@SuppressWarnings("serial")
public class SceneCanvas extends Canvas implements Runnable{
	private SceneData scenedata;
	private DOM dom;
	private Graphics g;
	private BufferStrategy strategy;
	
	public SceneCanvas(){
		super();
		this.setIgnoreRepaint(true);
	}
	
	public synchronized void myRepaint(){
		scenedata.setClientView(scenedata.getVirtualCharacterPosition());
		drawBackground();
		drawClientPlayer();
		drawOtherPlayers();
		drawItems();
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void drawBackground(){
		for(int i=0-scenedata.getPositionX(), k=0; i<scenedata.getMapWidth()-scenedata.getPositionX(); i+=100, k++){
			for(int j=0-scenedata.getPositionY(), l=0; j<scenedata.getMapHeight()-scenedata.getPositionY(); j+=100, l++){
				g.drawImage(scenedata.getBackImg(scenedata.getBackimg(k, l)), i, j, null);
			}
		}
	}
	
	private void drawClientPlayer() {
		Point playerPosition = scenedata.getVirtualCharacterPosition();
		int x, y;
		x = playerPosition.x;
		y = playerPosition.y;
		g.drawImage(scenedata.getImage(scenedata.getPlayer().getCharacter().getCharacterImg()), x-scenedata.getPositionX(), y-scenedata.getPositionY(), null);
	}
	
	private void drawOtherPlayers(){
		int thisClientPlayerID = dom.getClientPlayerID();
		for(int i=0;i<4;i++){
			if(i!=thisClientPlayerID){
				g.drawImage(scenedata.getImage(dom.getPlayer(i).getCharacter().getCharacterImg()),
						dom.getPlayer(i).getPlayerLocation().x,
						dom.getPlayer(i).getPlayerLocation().y,
						null);
			}
		}
	}
	
	private void drawItems() {
		dom.getItems().forEach((k,v)->g.drawImage(scenedata.getImage(v.getImage()), v.location.x, v.location.y, null));
	}
	
	public void setDOM(DOM dom){
		this.dom = dom;
	}
	
	public DOM getDOM(){
		return dom;
	}
	
	public void setSceneData(SceneData scenedata){
		
		this.scenedata = scenedata;
	}

	public SceneData getSceneData(){
		return scenedata;
	}
	
	@Override
	public void run() {
		JFrame frm = (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this);
		frm.setVisible(true);
		this.createBufferStrategy(2);
		strategy = this.getBufferStrategy();
		g = strategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.setClip(0, 0, scenedata.getFrameWidth(), scenedata.getFrameHeight());
	}
}