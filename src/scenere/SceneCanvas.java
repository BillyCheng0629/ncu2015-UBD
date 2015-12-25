package scenere;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SceneCanvas extends Canvas implements Runnable{
	private SceneData scenedata;
	private Graphics g;
	
	public SceneCanvas(){
		super();
		this.setIgnoreRepaint(true);
	}
	
	public synchronized void myRepaint(){
		BufferStrategy strategy = this.getBufferStrategy();
		g = strategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.setClip(0, 0, scenedata.getFrameWidth(), scenedata.getFrameHeight());
		drawBackground();
		drawClientPlayer();
		drawOtherPlayers();
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
		
	}
	
	private void drawOtherPlayers(){
		Point playerPosition = scenedata.getVirtualCharacterPosition();
		int x, y;
		x = playerPosition.x;
		y = playerPosition.y;
		g.drawImage(scenedata.getCharacter(), x-scenedata.getPositionX(), y-scenedata.getPositionY(), null);
	}
	
	public void drawItems() {
		
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
	}
}