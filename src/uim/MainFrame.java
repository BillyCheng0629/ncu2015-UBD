package uim;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cdc.CDC;
import gameobject.Player;
import tcpcm.TCPCM;
import tcpsm.TCPSM;
import udpbc.UDPBC;
import udpus.UDPUS;


public class MainFrame extends JFrame {
	
	private MainPanel mainPanel;
	private Player player;
	protected TCPSM tcpServer;
	protected TCPCM tcpClient;
	protected CDC cdc;
	protected UDPBC udpbc;
	protected UDPUS udpus;
	
		
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame=new MainFrame();
					frame.setVisible(true);
					frame.setResizable(false);
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
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
}
