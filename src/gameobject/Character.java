package gameobject;

import java.awt.Image;

public class Character {
	private Image characterImg;
	//animation
	private String name;
	
	public void setCharacterImg(Image characterImg){
		this.characterImg = characterImg;
	}
	
	public Image getCharacterImg(){
		return characterImg;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
