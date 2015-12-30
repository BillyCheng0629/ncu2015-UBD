package uim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import gameobject.Dumpling;
import gameobject.Item;
import gameobject.Player;

public class GamePanel extends JPanel{
	private KeyListener keyListener;
	private Player player[];
	private ArrayList<Dumpling> dumpling;
	private Timer gameTimer;
	private ArrayList<Item> item;
	private MainFrame frame;
	private int time;
	
	public GamePanel(MainFrame frame) {
		super();
		this.frame=frame;
		frame.setSize(1010, 620);
		this.setSize(1010, 620);
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
