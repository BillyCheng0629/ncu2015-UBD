package gameobject;

import java.awt.Point;

public class Player {
	private final int MAXDUMPLINGCOUNT = 10;
	private final int MAXPOWER = 10;
	private final int MAXSPEED = 10;
	
	private boolean alive;
	private int direction; // North = 1, East = 2, West = 3, South = 4
	private String name;
	private Character character;
	public Point location; // location.x  location.y
	
	private int ID;
	private int maxDumplingCount;
	private int currentDumplingCount;
	private int power;
	private int speed;
	
	public Player(String name){
		this.name = name;
	}
	
	public void setID(int id){
		this.ID = id;
	}
	
	public int getID(){
		return ID;
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getNmae(){
		return name;
	}
	
	public Character getCharacter(){
		return character;
	}
	
	public void setCharacter(Character character){
		this.character = character;
	}
	
	public void setMaxDumplingCount(int maxDumplingCount){
		this.maxDumplingCount = maxDumplingCount;
	}
	
	public int getMaxDumplingCount(){
		return maxDumplingCount;
	}
	
	public void setCurrentDumplingCount(int currentDumplingCount){
		this.currentDumplingCount = currentDumplingCount;
	}
	
	public int getCurrentDumplingCount(){
		return currentDumplingCount;
	}
	
	public void setPower(int power){
		this.power = power;
	}
	
	public int getPower(){
		return power;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setLocation(Point location){
		this.location = location;
	}
	
	public Point getPlayerLocation(){
		return location;
	}
}
