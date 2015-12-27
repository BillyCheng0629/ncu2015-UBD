package gameobject;

public class Character {
	private int characterImg;
	//animation
	private String name;
	
	public void setCharacterImg(int characterImg){
		this.characterImg = characterImg;
	}
	
	public int getCharacterImg(){
		return characterImg;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
