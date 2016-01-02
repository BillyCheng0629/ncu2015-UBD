package uim;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import cdc.CDC;
import dom.DOM;
import gameobject.Player;
import scenere.CharacterMoveListener;
import scenere.KeyActionPerformer;
import scenere.RePaintActionListener;
import scenere.SceneData;
import scenere.ScenePanel;
import tcpcm.TCPCM;
import tcpsm.TCPSM;
import udpbc.UDPBC;
import udpus.UDPUS;

public class MainFrame extends JFrame {
	
	private MainPanel mainPanel;
	public RoomPanel roomPanel;
	private Timer timer;
	private Timer keyTimer;
	boolean isHost = false;
	
	public Player player = new Player();
	
	TCPSM tcpsm;
	TCPCM tcpcm;
	CDC cdc;
	UDPBC udpbc;
	public UDPUS udpus;
	public DOM dom; 
	
		
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame=new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the frame.
	 */
	public MainFrame() {
		
		mainPanel = new MainPanel(this);
		
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
	}
	
	public void getIntoGame(){
		Container cp = this.getContentPane();
		cp.removeAll();
		cp.setLayout(null);
		ScenePanel scenePanel = new ScenePanel();
		scenePanel.setBounds(0, 0, 800, 600);
		scenePanel.setDOM(this.dom);
		SceneData sceneData = new SceneData(scenePanel);
		sceneData.setPlayer(this.dom.getPlayer(this.dom.getClientPlayerID()));
		scenePanel.setSceneData(sceneData);
		CharacterMoveListener keyListener = new CharacterMoveListener();
		scenePanel.addKeyListener(keyListener);
		KeyActionPerformer performer = new KeyActionPerformer(sceneData, keyListener, tcpcm);
		scenePanel.setKeyActionPerformer(performer);
		cp.add(scenePanel);
		cp.repaint();
		
		RePaintActionListener repaint = new RePaintActionListener(scenePanel);
		timer = new Timer(50, repaint);
		timer.start();
		
		keyTimer = new Timer(50, key ->{
			scenePanel.moveCharacter();
			scenePanel.placeBomb();
		});
		keyTimer.start();
	}
	
}
