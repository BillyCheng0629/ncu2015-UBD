package gameobject;

import java.awt.Point;

public class Player {
	public static final int MAXDUMPLINGCOUNT = 10;
	public static final int MAXPOWER = 10;
	
	private boolean isMoving;
	private boolean alive;
	private int direction; // Stop = 0, North = 1, East = 2, South = 3, West = 4
	private String name;
	private Character character;
	public Point location; // location.x  location.y
	
	private int ID;
	private int maxCurrentDumplingCount;
	private int currentDumplingCount;
	private int power;
	
	public Player(){
	
	}
	
	public Player(String name){
		this.name = name;
		alive = true;
		isMoving = false;
		direction = 3;
		location = new Point(0,0);
		maxCurrentDumplingCount = 1;
		currentDumplingCount = 0;
		power = 1;
	}
	
	@Override
	public String toString(){
		String t = "";
		t = "PLAYER " + ID + " " + name + " " + character + " " + alive + " "
				+ location.x + " " + location.y + " "
				+ isMoving + " " + direction + " " + power + " " 
				+ maxCurrentDumplingCount + " " + currentDumplingCount;
		return t;
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
	
	public void setIsMoving(boolean isMoving){
		this.isMoving = isMoving;
	}
	
	public boolean getIsMoving(){
		return isMoving;
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
		this.maxCurrentDumplingCount = maxDumplingCount;
	}
	
	public int getMaxDumplingCount(){
		return maxCurrentDumplingCount;
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
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public Point getPlayerLocation(){
		return location;
	}
	
	public void setPlayerLocation(Point location){
		this.location = location;
	}
}
