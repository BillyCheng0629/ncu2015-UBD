package gameobject;

import java.awt.Image;
import java.awt.Point;

public class Item {
	protected int type;
	public Point location;
	protected int ID;
	
	public Item(){
	}
	
	@Override
	public String toString(){
		String t = "";
		t = "ITEM " + ID + " " + type + " "+location.x+" " + location.y;
		return t;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
}