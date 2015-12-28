package gameobject;

import java.awt.Point;

import javax.swing.Timer;

public class Dumpling {
	private Timer bombTimer;
	//animation
	private int power;
	public Point location;
	
	@Override
	public String toString(){
		String t = "";
		t = "DUMPLING " + " " + location.x + " " + location.y;
		return t;
	}
	
	public void setPower(int power){
		this.power = power;
	}
	
	public int getPower(){  // to determine explosion range
		return power;
	}
	
}
