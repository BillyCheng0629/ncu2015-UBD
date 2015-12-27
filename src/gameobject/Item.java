package gameobject;

import java.awt.Point;

public class Item {
	protected int itemImage;
	private int type;
	public Point location;
	protected int id;
	
	public Item(){
		location = new Point(0, 0);
	}
	
	public int getImage(){
		return itemImage;
	}
	
	public void setImage(int itemImage){
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