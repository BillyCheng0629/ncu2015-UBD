package gameobject;

import java.awt.Point;

import javax.swing.Timer;

public class Dumpling {
	private int stateCounter;
	//animation
	private int power;
	public Point location;
	
	public Dumpling() {
		stateCounter = 3;
	}
	
	public void setPower(int power){
		this.power = power;
	}
	
	public int getPower(){  // to determine explosion range
		return power;
	}
	
	public void setState(int stateCounter) {
		this.stateCounter = stateCounter;
	}
	
	public int getState() {
		return stateCounter;
	}
}
