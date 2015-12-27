package scenere;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import dom.DOM;

@SuppressWarnings("serial")
public class ScenePanel extends JPanel{
	private SceneData scenedata;
	private DOM dom;
	private Graphics g;
	
	public ScenePanel(){
		super();
		this.setIgnoreRepaint(true);
	}
	
	@Override
	public synchronized void paint(Graphics g){
		super.paint(g);
		this.g = g;
		g.setColor(Color.BLACK);
		g.setClip(0, 0, scenedata.getFrameWidth(), scenedata.getFrameHeight());
		scenedata.setClientView(scenedata.getVirtualCharacterPosition());
		drawBackground();
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
	}

	public SceneData getSceneData(){
		return scenedata;
	}

}