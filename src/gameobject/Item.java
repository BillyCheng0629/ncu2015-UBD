package gameobject;

import java.awt.Image;
import java.awt.Point;

public class Item {
	private Image itemImage;
	private int type;
	public Point location;
	private int id;
	
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
	
	public int getID(){
		return id;
	}
	
	public void setID(int id){
		this.id = id;
	}
}