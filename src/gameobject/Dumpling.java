package gameobject;

import java.awt.Point;

import javax.swing.Timer;

public class Dumpling {
	private Timer bombTimer;
	//animation
	private int power;
	public Point location;
	
	private int ID;
	
	public Dumpling(){
	}
	
	@Override
	public String toString(){
		String t = "";
		t = "DUMPLING " + ID + " " + location.x + " " + location.y + " " + power;
		return t;
	}
	
	public void setPower(int power){
		this.power = power;
	}
	
	public int getPower(){  // to determine explosion range
		return power;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	
}
