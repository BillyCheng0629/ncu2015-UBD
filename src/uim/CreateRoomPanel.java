package uim;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cdc.CDC;
import dom.DOM;
import tcpcm.TCPCM;
import tcpsm.TCPSM;

public class CreateRoomPanel extends JPanel {
	private JButton createRoom;
	private JButton joinRoom;
	private ActionListener createRoomListener;
	private ActionListener joinRoomListener;
	private JDialog ipInputDialog;
	//private RoomPanel roomPanel;
	private MainPanel mainPanel;
	private MainFrame frame;
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
		createActionListener();
		createRoom = new JButton("create room");
		createRoom.setBounds(289, 166, 178, 53);
		createRoom.addActionListener(createRoomListener);
		add(createRoom);
		
		joinRoom = new JButton("join room");
		joinRoom.setBounds(289, 287, 178, 53);
		joinRoom.addActionListener(joinRoomListener);
		add(joinRoom);

	}
	private void createActionListener(){
		createRoomListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
					frame.tcpcm.sendRoomAction("ADDPLAYER,"+frame.player.getName());
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
		joinRoomListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				ipInputDialog.setVisible(true);
				
			}
		};
	}
}
