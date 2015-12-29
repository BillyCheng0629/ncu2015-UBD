package gameobject;

import java.awt.Point;

import javax.swing.Timer;

public class Dumpling {
	private Timer bombTimer;
	//animation
	private int power;
	public Point location;
	
	public void setPower(int power){
		this.power = power;
	}
	
	public int getPower(){  // to determine explosion range
		return power;
	}
	public void setLocation(Point location){
		this.location=location;
	}
	
}
