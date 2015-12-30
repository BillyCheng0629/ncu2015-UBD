package gameobject;

import java.awt.Image;
import java.awt.Point;

public class Item {
	private Image itemImage;
	private int type;//0=increase dumplings amount;1=increase dumplings power
	public Point location;
	
	public Image getImage(){
		return itemImage;
	}
	
	public void setImage(Image itemImage){
		this.itemImage = itemImage;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}
}
