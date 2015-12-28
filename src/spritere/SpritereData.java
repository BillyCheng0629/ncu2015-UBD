package spritere;

import javax.swing.ImageIcon;
import gameobject.Character;

public class SpritereData {
	private ImageIcon roleIcon;
	private ImageIcon dumplingIcon;
	private ImageIcon firePowerItemIcon;
	private ImageIcon bombItemIcon;
	private ImageIcon explosionIcon;
	 
	public SpritereData(int charaterID) {
		loadRoleImage(charaterID);
	}	 
	 
	public void loadRoleImage(int charaterID){
		String fileString = "./imgs/character/role/role"+Integer.toString(charaterID)+".png";
		roleIcon = new ImageIcon(fileString);
	}
	
	public ImageIcon getRoleIcon() {
		return this.roleIcon;
	}
	
	public void loadDumplingImage(){
		
	}

}
