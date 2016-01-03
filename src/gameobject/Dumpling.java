package gameobject;

import java.awt.Point;

import javax.swing.Timer;

public class Dumpling {
	private int stateCounter;
	private int count=3000;
	//animation
	private int power;
	public Point location;
	private int ID;
	
	public Dumpling() {		
		stateCounter = 0;
		location=new Point(-1, -1);
		power=0;
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
	
	public void setState(int stateCounter) {
		this.stateCounter = stateCounter;
	}
	
	public int getState() {
		return stateCounter;
	}
	public int getCount(){
		return count;
	}
	public void setCount(int count){
		this.count=count;
	}
}
