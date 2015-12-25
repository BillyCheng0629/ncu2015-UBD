package gameobject;

import java.awt.Point;

public class Player {
	private final int MAXDUMPLINGCOUNT = 10;
	private final int MAXPOWER = 10;
	
	private boolean alive;
	private int direction; // North = 1, East = 2, West = 3, South = 4
	private String name;
	private Character character;
	public Point location; // location.x  location.y
	
	private int ID;
	private int maxCurrentDumplingCount;
	private int currentDumplingCount;
	private int power;
	
	@Override
	public String toString(){
		String t = "";
		t += "alive:" + alive + ",";
		t += "direction:" + direction + ",";
		t += "name:" + name + ",";
		t += "character:" + character.toString() + ",";
		t += "location:" + location.toString() + ",";
		t += "ID:" + ID + ",";
		t += "maxCurrentDumplingCount:" + maxCurrentDumplingCount + ",";
		t += "currentDumplingCount:" + currentDumplingCount + ",";
		t += "power:" + power;
		return t;
	}

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
