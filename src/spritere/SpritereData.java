package spritere;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SpritereData {
	private ImageIcon roleIcon;
	private Image dumplingImages[];
	private Image itemImages[];
	 
	public SpritereData() {
		dumplingImages = new Image[8];
		itemImages = new Image[2];
	}	 
	 
	public void loadRoleImage(int charaterID){
		String fileString = "./imgs/character/role/role"+Integer.toString(charaterID)+".png";
		roleIcon = new ImageIcon(fileString);
	}
	
	public ImageIcon getRoleIcon() {
		return this.roleIcon;
	}
	
	public void loadItemImages(){
		itemImages[0] = new ImageIcon("./imgs/items/itemAD.png").getImage();
		itemImages[1] = new ImageIcon("./imgs/items/itemPU.png").getImage();
	}
	
	public Image[] getItemImages() {
		return itemImages;
	}
	
	public void loadDumplingImages(){		
		dumplingImages[0] = new ImageIcon("./imgs/dumpling/dumpling.png").getImage();
		dumplingImages[1] = new ImageIcon("./imgs/dumpling/explosionCenter.png").getImage();
		dumplingImages[2] = new ImageIcon("./imgs/dumpling/explosionMid1.png").getImage();
		dumplingImages[3] = new ImageIcon("./imgs/dumpling/explosionMid2.png").getImage();
		dumplingImages[4] = new ImageIcon("./imgs/dumpling/explosioTail1.png").getImage();
		dumplingImages[5] = new ImageIcon("./imgs/dumpling/explosionTail2.png").getImage();
		dumplingImages[6] = new ImageIcon("./imgs/dumpling/explosionTail3.png").getImage();
		dumplingImages[7] = new ImageIcon("./imgs/dumpling/explosionTail4.png").getImage();
	}
	
	public Image[] getDumplingImages(){
		return dumplingImages;
	}

}