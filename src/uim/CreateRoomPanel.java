package uim;

import gameobject.Player;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cdc.CDC;
import dom.DOM;
import tcpcm.TCPCM;
import tcpsm.TCPSM;

public class CreateRoomPanel extends JPanel {
	private JButton createRoom;
	private JButton joinRoom;
	private MouseListener createRoomMouseListener;
	private MouseListener joinRoomMouseListener;
	private JDialog ipInputDialog;
	private MainFrame frame;
	private JLabel bgLabel;
	/**
	 * Create the panel.
	 */
	public CreateRoomPanel(MainFrame frame) {
		super();
		this.frame=frame;
		
		this.setSize(1010,620);
		ipInputDialog = new IPInputDialog(frame);
		frame.roomPanel=new RoomPanel(frame);
		
		setLayout(null);
		
		bgLabel = new JLabel();
		bgLabel.setIcon(new ImageIcon("./imgs/panelbackground/bgCreateRoomPanel.png"));
		
		bgLabel.setBounds(0, 0, 1010, 620);
	
		
		createMouseListener();
		createRoom = new JButton();
		createRoom.setBorder(null);
		createRoom.setIcon(new ImageIcon("./imgs/button/btCreate.png"));
		createRoom.setBounds(180, 406, 200, 50);
		createRoom.addMouseListener(createRoomMouseListener);
		add(createRoom);
		
		joinRoom = new JButton();
		joinRoom.setBorder(null);
		joinRoom.setIcon(new ImageIcon("./imgs/button/btJoin.png"));
		joinRoom.setBounds(180, 466, 200, 50);
		joinRoom.addMouseListener(joinRoomMouseListener);
		add(joinRoom);	
		
		add(bgLabel);
		bgLabel.setVisible(true);
		createRoom.setVisible(true);
		joinRoom.setVisible(true);
	}
	
	private void createMouseListener(){
		createRoomMouseListener = new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 createRoom.setIcon(new ImageIcon("./imgs/button/btCreate2.png"));
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 createRoom.setIcon(new ImageIcon("./imgs/button/btCreate.png"));
			 }
			 @Override
			 public void mouseClicked(MouseEvent e){
					// TODO Auto-generated method stub
					frame.isHost = true;
					frame.player.setIsReady(true);
					frame.roomPanel=new RoomPanel(frame);
					//set cdc
					frame.cdc = new CDC();
					
					//set dom
					frame.dom = new DOM();
					
					//start tcp server
					frame.tcpsm = new TCPSM();
					frame.tcpsm.setCDC(frame.cdc);
					frame.tcpsm.initTCPserver();
					
					//connect to server
					frame.tcpcm = new TCPCM(frame);
					frame.tcpcm.connectServer("127.0.0.1");

					frame.tcpcm.startRecieveMessage();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						int characterType = 0;
						int isReady = 1;
						frame.tcpcm.sendRoomAction("ADDPLAYER,"+frame.player.getName()+","+characterType+","+isReady);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("add player error");
						e1.printStackTrace();
					}
									
					frame.getContentPane().removeAll();
					frame.getContentPane().add(frame.roomPanel);
					frame.roomPanel.setBounds(20, 5, 780, 595);
					frame.getContentPane().add(frame.roomPanel);
					frame.getContentPane().repaint();
					//frame.getContentPane().repaint();
			 }
		};
		
		joinRoomMouseListener = new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 joinRoom.setIcon(new ImageIcon("./imgs/button/btJoin2.png"));
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 joinRoom.setIcon(new ImageIcon("./imgs/button/btJoin.png"));
			 }
			 @Override
			 public void mouseClicked(MouseEvent e){
				 ipInputDialog.setVisible(true);
			 }
		};	
	}
}
