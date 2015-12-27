package scenere;

import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import gameobject.Player;

public class SceneData {
	private int mapBit[][];
	private int mapType=3;
	private int mapWidth = 5000;
	private int mapHeight = 2000;
	private Image backimg[];
	private Image dumpling;
	private Image bombitem;
	private Image character;
	private Player clientPlayer;
	private int topBound;
	private int bottomBound;
	private int leftBound;
	private int rightBound;
	private int positionx = 0;
	private int positiony = 0;
	private JFrame frm;
	private String mapFilePath[] = {"./mapFile/mapFileIce","./mapFile/mapFileCake","./mapFile/mapFileDesert","./mapFile/mapFileLego"};
	
	public SceneData(JFrame frm){
		mapBit = new int[50][20];
		backimg = new Image[5];
		this.frm = frm;
		setRoundBound();
		LoadMap(mapFilePath[mapType]);
		LoadImg();
	}
	
	public SceneData(int mapType, JFrame frm){
		mapBit = new int[50][20];
		backimg = new Image[5];
		this.frm = frm;
		setRoundBound();
		LoadMap(mapFilePath[mapType]);
		LoadImg();
	}
	
	private void LoadImg(){
		try{
			backimg[0] = ImageIO.read(new File("./imgs/mapbackgrounds/ice.png"));
			backimg[1] = ImageIO.read(new File("./imgs/mapbackgrounds/cake.png"));
			backimg[2] = ImageIO.read(new File("./imgs/mapbackgrounds/desert.png"));
			backimg[3] = ImageIO.read(new File("./imgs/mapbackgrounds/lego.png"));
			backimg[4] = ImageIO.read(new File("./imgs/mapbackgrounds/rock.png"));
			dumpling = ImageIO.read(new File("./imgs/items/dumpling.png"));
			bombitem = ImageIO.read(new File("./imgs/items/bombitem.png"));
			character = ImageIO.read(new File("./imgs/character/053.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void setRoundBound(){
		setTopBound(100);
		setBottomBound(frm.getHeight()-100);
		setLeftBound(100);
		setRightBound(frm.getWidth()-100);
	}
	
	private void LoadMap(String mapFilePath){
		FileReader input;
		BufferedReader reader;
		String temp;
		try {
			input = new FileReader(mapFilePath);
			reader = new BufferedReader(input);
			for(int j=0;j<20;j++){
				temp = reader.readLine();
				if(temp==null)
					break;
				else{
					for(int i=0;i<50;i++){
						mapBit[i][j] = Character.getNumericValue(temp.charAt(i));
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not exist in "+mapFilePath+" please check the file. Would set to basic map");
			e.printStackTrace();
			setBasicMap();
		} catch (IOException e) {
			System.out.println("please check mapFile is correct structure, it should be (50-column*20-raw) Strings with number0~4. Would set to basic map");
			e.printStackTrace();
			setBasicMap();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("please check mapFile is correct structure, it should be (50-column*20-raw) Strings with number0~4. Would set to basic map");
			e.printStackTrace();
			setBasicMap();
		}
	}
	
	private void setBasicMap(){
		for(int i=0;i<50;i++)
			for(int j=0;j<20;j++){
				if(i%2==1 && j%2==1)
					mapBit[i][j]=4;
				else
					mapBit[i][j]=3;
			}
	}
	
	public Point getVirtualCharacterPosition(){
		return clientPlayer.getPlayerLocation();
	}
	
	public void setClientView(Point point){
		assert point.getX()<=mapWidth-100 && point.getY()<=mapHeight-100;
		if(clientPlayer.getPlayerLocation().x>500 && clientPlayer.getPlayerLocation().x<4500)
			this.setPositionX(clientPlayer.getPlayerLocation().x-500);
		else if(clientPlayer.getPlayerLocation().x<=500)
			this.setPositionX(0);
		else
			this.setPositionX(4000);
		
		if(clientPlayer.getPlayerLocation().y>300 && clientPlayer.getPlayerLocation().y<1700)
			this.setPositionY(clientPlayer.getPlayerLocation().y-300);
		else if(clientPlayer.getPlayerLocation().y<=300)
			this.setPositionY(0);
		else
			this.setPositionY(1400);
	}
	
	public void setPlayer(Player player){
		this.clientPlayer = player;
	}
	
	public Player getPlayer(){
		return clientPlayer;
	}
	
	public void setMapType(int mapType){
		assert mapType>=0 && mapType<4 : "mapType error";
		LoadMap(mapFilePath[mapType]);
	}
	
	public void setTopBound(int upperY){
		this.topBound = upperY;
	}
	
	public int getTopBound(){
		return topBound;
	}

	public void setBottomBound(int bottomY){
		this.bottomBound = bottomY;
	}
	
	public int getBottomBound(){
		return bottomBound;
	}

	public void setLeftBound(int leftX){
		this.leftBound = leftX;
	}
	
	public int getLeftBound(){
		return leftBound;
	}

	public void setRightBound(int rightX){
		this.rightBound = rightX;
	}
	
	public int getRightBound(){
		return rightBound;
	}
	
	public Image getBackImg(int img){
		assert img>=0 && img<=5 : "img isn't exist";
		return backimg[img];
	}
	
	public int getMapWidth(){
		return mapWidth;
	}
	
	public int getMapHeight(){
		return mapHeight;
	}
	
	public int getBackimg(int x, int y){
		return mapBit[x][y];
	}
	
	public void setPositionX(int x){
		this.positionx = x;
	}
	
	public int getPositionX(){
		return positionx;
	}
	
	public void setPositionY(int y){
		this.positiony = y;
	}
	
	public int getPositionY(){
		return positiony;
	}
	
	public int getFrameWidth(){
		return frm.getWidth();
	}
	
	public int getFrameHeight(){
		return frm.getHeight();
	}

	public JFrame getJFrame(){
		return frm;
	}
	
	public Image getImage(int imageType){
		switch(imageType){
			case(0):return dumpling;
			case(1):return bombitem;
			case(2):return character;
		}
		return null;
	}
	
}