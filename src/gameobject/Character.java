package gameobject;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Character {
	private int characterNum;
	//animation
	private String name;
	
	public void setCharacterNum(int characterNum){
		this.characterNum = characterNum;
	}
	
	public int getCharacterNum(){
		return characterNum;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
