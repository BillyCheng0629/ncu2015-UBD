package gameobject;

import java.awt.Point;

import javax.swing.Timer;

public class Dumpling {
	
	//animation
	private int count=3000;
	private int power;
	public Point location;
	
	public void setPower(int power){
		this.power = power;
	}
	
	public int getPower(){  // to determine explosion range
		return power;
	}
	public int getCount(){
		return count;
	}
	public void setCount(int count){
		this.count=count;
	}
	public void setLocation(Point location){
		this.location=location;
	}
	
}
