package uim;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cdc.CDC;
import dom.DOM;
import gameobject.Player;
import tcpcm.TCPCM;
import tcpsm.TCPSM;

public class MainFrame extends JFrame {
	
	private MainPanel mainPanel;
	public RoomPanel roomPanel;
	
	Player player;
	
	TCPSM tcpsm;
	TCPCM tcpcm;
	CDC cdc;
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
	

}
