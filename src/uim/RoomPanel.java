package uim;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gameobject.Player;
import udpbc.UDPBC;



public class RoomPanel extends JPanel{
	private JButton startButton;
	private JButton readyButton;
	private JButton exitButton;
	private boolean isReady;
	private MouseListener startBtListener;
	private MouseListener readyBtListener;
	private MouseListener exitBtListener;
	private JLabel playeName[] = new JLabel[4];
	private JComboBox mapComboBox;
	private ImageIcon mapImage;
	private JLabel mapLabel;
	private JLabel playerLabel;
	private JPanel mapPanel;
	private JPanel playerInfo[] = new JPanel[4];
	private JLabel playerImg[] = new JLabel[4];
	private JLabel playerReadyState[] = new JLabel[4];
	private JLabel OwnerLabel;
	private ActionListener playerImgListener;
	private ActionListener mapChooseListener;
	private MainFrame frame;
	private JPanel gamePanel;
	private MouseListener changeCharacterListener;
	private int localCharacterNum = 0;
	private JLabel bgLabel,g8face;
	
	public RoomPanel(MainFrame frame){
		super();
		this.frame=frame;
		frame.setSize(1010,620);
		gamePanel =new GamePanel(frame);
	
		setLayout(null);
		createActionListener();
		
		bgLabel = new JLabel();
		bgLabel.setIcon(new ImageIcon("./imgs/panelbackground/bgRoomPanel.gif"));
		bgLabel.setBounds(0,0,1010,620);
		
		mapImage=new ImageIcon("imgs/panelbackground/cbMap0.jpg");
		mapLabel = new JLabel();
		mapLabel.setIcon(mapImage);
		mapLabel.setBounds(5, 5,330,210);
				
		mapComboBox = new JComboBox();
		
		String mapName[] = {"Ice", "Cake", "Desert", "Lego"};
		mapComboBox.setModel(new DefaultComboBoxModel(mapName));
		
		mapComboBox.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					System.out.println("U are selecting map .");
					for(int i=0;i<mapName.length;i++) {
						if(mapComboBox.getSelectedItem()==mapName[i]){
							mapLabel.setIcon(new ImageIcon("imgs/panelbackground/cbMap"+i+".png"));
							try {
								frame.tcpcm.sendRoomAction("SETMAP," + i);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		
		// set map ComboBox
		mapComboBox.setToolTipText("Map");
		mapComboBox.setFont(new Font("Microsoft JhengHei", Font.BOLD, 25));
		mapComboBox.setBounds(542, 96, 330, 50);
		
		
		//set mapPanel
		mapPanel = new JPanel();
		mapPanel.setBounds(542, 160, 350, 220);
		mapPanel.setBackground(Color.BLACK);
		mapPanel.setLayout(new FlowLayout());
		mapPanel.add(mapLabel);
	
		for(int i=0;i<playerInfo.length;i++) {
			playerInfo[i] = new JPanel();
			playerInfo[i].setLayout(null);
			playerInfo[i].setBorder(BorderFactory.createRaisedBevelBorder());
			
			playerImg[i] = new JLabel("");
			playerImg[i].setIcon(new ImageIcon("imgs/character/face/face"+0+".png"));
			playerImg[i].setBounds(45, 40, 100, 100);
			
			playerInfo[i].add(playerImg[i]);
			
			playeName[i] = new JLabel();
			playeName[i].setBounds(10, 5, 180, 30);
			playeName[i].setFont(new Font("Microsoft JhengHei", Font.BOLD, 25));
			playeName[i].setHorizontalAlignment(JLabel.CENTER);

			
			playerInfo[i].add(playeName[i]);
			
			playerReadyState[i] = new JLabel("");
			if(i == 0){
				playerReadyState[i].setText("Ready");
			}
			
			playerReadyState[i].setBounds(10,140, 180, 40);
			playerReadyState[i].setFont(new Font("Microsoft JhengHei", Font.BOLD, 30));
			playerReadyState[i].setHorizontalAlignment(JLabel.CENTER);
			playerInfo[i].add(playerReadyState[i]);
			
			playerLabel = new JLabel();
			playerLabel.setIcon(new ImageIcon("./imgs/panelbackground/bgPlayer.png"));
			playerLabel.setBounds(5,5,190,190);
			playerInfo[i].add(playerLabel);
			playerInfo[i].setOpaque(false);
			
		}
		
		//set player place
		playerInfo[0].setBounds(50, 150, 200, 200);
		playerInfo[1].setBounds(270, 150, 200, 200);
		playerInfo[2].setBounds(50, 360, 200, 200);	
		playerInfo[3].setBounds(270, 360, 200, 200);
		
		for(int i=0;i<playerInfo.length;i++) {
			add(playerInfo[i]);
		}

		isReady = false;
		
		if (frame.isHost){
			startButton = new JButton();
			startButton.setIcon(new ImageIcon("./imgs/button/btRoomStart.png"));
			startButton.addMouseListener(startBtListener);
			startButton.setBounds(542, 420, 200, 50);		
			add(startButton);
		} else {
			readyButton = new JButton("Ready");
			readyButton.setIcon(new ImageIcon("./imgs/button/btRoomReady.png"));
			readyButton.addMouseListener( readyBtListener);			
			readyButton.setBounds(542, 420, 200, 50);
			add(readyButton);
		}
		
		
		
		exitButton = new JButton();
		exitButton.setIcon(new ImageIcon("./imgs/button/btRoomExit.png"));
		exitButton.addMouseListener(exitBtListener);
		exitButton.setBounds(542, 500, 200, 50);
		exitButton.setVisible(true);
		add(exitButton);
		
		g8face = new JLabel();
		g8face.setIcon(new ImageIcon("./imgs/panelbackground/g8face.gif"));
		g8face.setBounds(780, 390, 180, 180);
		
		add(mapComboBox);
		add(mapPanel);
		add(g8face);
		bgLabel.setVisible(true);
		add(bgLabel);
		
	}
	
	public void updateRoomInfo() {
		
		for(int i=0;i<playerInfo.length;i++) {
			Player player = frame.dom.getPlayer(i);
			if(player!=null) {
				playeName[i].setText(player.getName());
				System.out.println("set player "+i);
				
				if (player.getIsReady()){
					playerReadyState[i].setText("Ready");
				} else {
					playerReadyState[i].setText("");
				}
				playerImg[i].setIcon(new ImageIcon("imgs/character/face/face"+player.getCharacter().getCharacterNum()+".png"));
			}
			else {
				playeName[i].setText("");
				playerReadyState[i].setText("");
				playerImg[i].setIcon(new ImageIcon());
			}
		}
		int mapType = frame.dom.getMapType();
		mapImage = new ImageIcon("imgs/panelbackground/cbMap"+mapType+".jpg");
		mapLabel.setIcon(mapImage);
		mapComboBox.setSelectedIndex(mapType);
		
	}
	
	public void initLocal() {
		playerInfo[frame.player.getID()].addMouseListener(changeCharacterListener);
		playerInfo[frame.player.getID()].setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2, true));
	}	
	
	private void createActionListener(){
		startBtListener = new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(new ImageIcon("./imgs/button/btRoomStart.png"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(new ImageIcon("./imgs/button/btRoomStart2.png"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (detectStart()){
					try {
						Thread.sleep(3000);
						frame.cdc.initGame();
						frame.tcpcm.sendRoomAction("START");
						frame.udpbc = new UDPBC(frame.tcpsm, frame.cdc);
						frame.udpbc.startUDPBroadcast();
					} catch (SocketException e1) {
						e1.printStackTrace();
					} catch(IOException e1){
						e1.printStackTrace();
					} catch(Exception e1){
						e1.printStackTrace();
					}
				}			
			}
		};
		
		readyBtListener = new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (isReady==false){
					readyButton.setIcon(new ImageIcon("./imgs/button/btRoomReady.png"));
				}
				else {
					readyButton.setIcon(new ImageIcon("./imgs/button/btRoomNotReady.png"));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isReady==false){
					readyButton.setIcon(new ImageIcon("./imgs/button/btRoomReady2.png"));
				}
				else {
					readyButton.setIcon(new ImageIcon("./imgs/button/btRoomNotReady2.png"));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isReady==false){
					playerReadyState[frame.player.getID()].setText("Ready");
					try {
						frame.tcpcm.sendRoomAction("SETISREADY,1");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					isReady = true;
					readyButton.setIcon(new ImageIcon("./imgs/button/btRoomNotReady.png"));
				} else {
					playerReadyState[frame.player.getID()].setText("");
					try {
						frame.tcpcm.sendRoomAction("SETISREADY,0");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					isReady = false;
					readyButton.setIcon(new ImageIcon("./imgs/button/btRoomReady.png"));
				}
			}
		};
		
		exitBtListener = new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(new ImageIcon("./imgs/button/btRoomExit.png"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(new ImageIcon("./imgs/button/btRoomExit2.png"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		};
		
		changeCharacterListener = new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("roomPanel changeCharacterListener");
				localCharacterNum++;
				if (localCharacterNum>8) localCharacterNum=0;
				playerImg[frame.player.getID()].setIcon(new ImageIcon("imgs/character/face/face"+localCharacterNum+".png"));
				try {
					frame.tcpcm.sendRoomAction("SETCHARACTER,"+localCharacterNum);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		
	}
	
	public boolean detectStart(){
		boolean isStart = true; // player's readyState
		boolean result = true; // game can start or not
		int count = 0; // to count the person of the room
		
		for(int i = 0 ; i < playerInfo.length ; i++) {
			Player player = frame.dom.getPlayer(i);
			if(player != null) {
				player.setAlive(true);
				count++;
				isStart = (isStart && player.getIsReady());
			}
		}
		if (count >= 2) { 
			result = result && isStart;
		} else {
			result = false; 
		}
		return result;
	}

}
