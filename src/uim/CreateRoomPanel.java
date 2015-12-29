package uim;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreateRoomPanel extends JPanel {
	private JButton createRoom;
	private JButton joinRoom;
	private ActionListener createRoomListener;
	private ActionListener joinRoomListener;
	private JDialog ipInputDialog;
	private RoomPanel roomPanel;
	private MainPanel mainPanel;
	private JFrame frame;
	/**
	 * Create the panel.
	 */
	public CreateRoomPanel(MainFrame frame) {
		super();
		this.frame=frame;
		
		this.setSize(800,600);
		ipInputDialog = new IPInputDialog(frame);
		roomPanel=new RoomPanel(frame);
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
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(roomPanel);
				roomPanel.setBounds(20, 5, 780, 595);
				frame.getContentPane().add(roomPanel);
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
