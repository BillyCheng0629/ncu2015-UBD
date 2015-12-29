package gameobject;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Character {
	private int character;
	//animation
	private String name;
	
	public void setCharacterImg(int characterNum){
		this.character = characterNum;
	}
	
	public int getCharacterImg(){
		return character;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
