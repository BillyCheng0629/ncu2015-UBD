package uim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import gameobject.Dumpling;
import gameobject.Item;
import gameobject.Player;

public class GamePanel {
	private KeyListener keyListener;
	private Player player[];
	private ArrayList<Dumpling> dumpling;
	private Timer gameTimer;
	private ArrayList<Item> item;
	
	public GamePanel(){
		
	}
	
	public void initGame(){
		
	}
	
	//game timer to make game object perform some action
	class gameTimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private Timer itemSpawnTimer;
	class itemSpawnTimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
