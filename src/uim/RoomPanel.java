package uim;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private ActionListener startListener;
	private ActionListener readyListener;
	private ActionListener exitListener;
	private JLabel playe1Name,playe2Name,playe3Name,playe4Name;
	private JLabel playeName[] = new JLabel[4];
	private JComboBox mapComboBox;
	private ImageIcon mapImage;
	private JLabel mapLabel;
	private JLabel ipAddress;
	private JPanel mapPanel;
	private JPanel playerInfo1,playerInfo2,playerInfo3,playerInfo4;
	private JPanel playerInfo[] = new JPanel[4];
	private JLabel player1Img,player2Img,player3Img,player4Img;
	private JLabel playerImg[] = new JLabel[4];
	private JLabel playerReadyState[] = new JLabel[4];
	private JLabel OwnerLabel;
	private ActionListener playerImgListener;
	private ActionListener mapChooseListener;
	private MainFrame frame;
	private JPanel gamePanel;
	private MouseListener changeCharacterListener;
	private int localCharacterNum = 0;
	public RoomPanel(MainFrame frame){
		super();
		this.frame=frame;
		frame.setSize(1010,620);
		gamePanel =new GamePanel(frame);
	
		setLayout(null);
		createActionListener();
		ipAddress = new JLabel("Dumpling Man");
		ipAddress.setFont(new Font("新細明體", Font.PLAIN, 24));
		ipAddress.setHorizontalAlignment(SwingConstants.CENTER);
		ipAddress.setBounds(31, 30, 217, 37);
		add(ipAddress);
		
		mapImage=new ImageIcon("imgs/mapbackgrounds/map0.png");
		mapLabel = new JLabel();
		mapLabel.setIcon(mapImage);
		mapLabel.setBounds(5, 5,150,150);
		
		
		mapComboBox = new JComboBox();
		
		String mapName[] = {"陽全酒家", "金魚家", "石頭", "沙漠"};
		mapComboBox.setModel(new DefaultComboBoxModel(mapName));
		
		
		mapComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					System.out.println("U are selecting map .");
					for(int i=0;i<mapName.length;i++) {
						if(mapComboBox.getSelectedItem()==mapName[i]){
							mapLabel.setIcon(new ImageIcon("imgs/mapbackgrounds/map"+i+".png"));
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
		mapComboBox.setBounds(542, 96, 157, 21);
		add(mapComboBox);
		
		//set mapPanel
		mapPanel = new JPanel();
		mapPanel.setBounds(542, 127, 157, 139);
		add(mapPanel);
		mapPanel.setLayout(null);
		mapPanel.add(mapLabel);
		
		
		
		for(int i=0;i<playerInfo.length;i++) {
			playerInfo[i] = new JPanel();
			playerInfo[i].setLayout(null);
			playerInfo[i].setBorder(BorderFactory.createRaisedBevelBorder());
			
			playerImg[i] = new JLabel("");
			playerImg[i].setIcon(new ImageIcon("imgs/character/face/face"+0+".png"));
			playerImg[i].setBounds(10, 39, 166, 133);
			
			playerInfo[i].add(playerImg[i]);
			
			playeName[i] = new JLabel();
			
			playeName[i].setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
			playeName[i].setBounds(10, 10, 166, 19);
			
			playerInfo[i].add(playeName[i]);
			
			playerReadyState[i] = new JLabel("");
			if(i == 0){
				playerReadyState[i].setText("Ready");
			}
			
			playerReadyState[i].setBounds(10,105, 120,120);
			playerInfo[i].add(playerReadyState[i]);
			
		
		}
		
		
		
		//set player place
		playerInfo[0].setBounds(31, 84, 186, 182);
		playerInfo[1].setBounds(268, 84, 186, 182);
		playerInfo[2].setBounds(31, 303, 186, 182);	
		playerInfo[3].setBounds(268, 303, 186, 182);
		
		for(int i=0;i<playerInfo.length;i++) {
			add(playerInfo[i]);
		}
		
		
		/*
		mapImage=new ImageIcon("imgs/mapbackgrounds/cake.png");
		mapLabel = new JLabel();
		mapLabel.setBounds(5, 5,150,150);
		
		mapComboBox = new JComboBox();
		mapLabel.setIcon(new ImageIcon("imgs/mapbackgrounds/cake.png"));
		mapComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u967D\u5168\u9152\u5BB6", "\u5DE5\u4E94", "\u91D1\u9B5A\u5BB6"}));
		
		
		
		
		mapComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					System.out.println("U are selecting map .");
					if(mapComboBox.getSelectedItem()=="\u967D\u5168\u9152\u5BB6"){
						
						mapLabel.setIcon(new ImageIcon("imgs/mapbackgrounds/cake.png"));
						
					}
					if(mapComboBox.getSelectedItem()=="\u5DE5\u4E94"){
						mapLabel.setIcon(new ImageIcon("imgs/mapbackgrounds/desert.png"));
						System.out.println("select aaaaaaaaa");
					}
					if(mapComboBox.getSelectedItem()=="\u91D1\u9B5A\u5BB6"){
						mapLabel.setIcon(new ImageIcon("imgs/mapbackgrounds/ice.png"));
					}
				}
			}
		});
		
		mapComboBox.setToolTipText("Map");
		mapComboBox.setBounds(542, 96, 157, 21);
		add(mapComboBox);
	
		mapPanel = new JPanel();
		mapPanel.setBounds(542, 127, 157, 139);
		add(mapPanel);
		mapPanel.setLayout(null);
//		add()
		
		
		
		
		
		
		mapPanel.add(mapLabel);
		
		
		
		playerInfo1 = new JPanel();
		playerInfo1.setBounds(31, 84, 186, 182);
		
		playerInfo1.setLayout(null);
		
		player1Img = new JLabel("");
		player1Img.setIcon(new ImageIcon("imgs/character/face/face0.png"));
		player1Img.setBounds(10, 39, 166, 133);
		playerInfo1.add(player1Img);
		
		playe1Name = new JLabel("\u897F\u8857\u901F\u5EA6\u738B");
		playe1Name.setFont(new Font("QQQ", Font.PLAIN, 18));
		playe1Name.setBounds(10, 10, 166, 19);
		playerInfo1.add(playe1Name);
		
		OwnerLabel = new JLabel("");
		OwnerLabel.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\star-icon (1).png"));
		OwnerLabel.setBounds(148, 0, 38, 30);
		playerInfo1.add(OwnerLabel);
		add(playerInfo1);
		
		playerInfo2 = new JPanel();
		playerInfo2.setLayout(null);
		playerInfo2.setBounds(268, 84, 186, 182);
		
		
		player1Img = new JLabel("");
		player1Img.setIcon(new ImageIcon("imgs/character/face/face0.png"));
		player1Img.setBounds(10, 39, 166, 133);
		playerInfo2.add(player1Img);
		
		playe2Name= new JLabel("\u5B85\u7DB8");
		playe2Name.setFont(new Font("·s²Ó©úÅé", Font.PLAIN, 18));
		playe2Name.setBounds(10, 10, 166, 19);
		playerInfo2.add(playe2Name);
		add(playerInfo2);
		
		playerInfo3 = new JPanel();
		playerInfo3.setLayout(null);
		playerInfo3.setBounds(31, 303, 186, 182);
		add(playerInfo3);
		
		player3Img = new JLabel("");
		player3Img.setIcon(new ImageIcon("imgs/character/face/face0.png"));
		player3Img.setBounds(10, 39, 166, 133);
		playerInfo3.add(player3Img);
		
		playe3Name = new JLabel("Yu-Bing");
		playe3Name.setFont(new Font("·s²Ó©úÅé", Font.PLAIN, 18));
		playe3Name.setBounds(10, 10, 166, 19);
		playerInfo3.add(playe3Name);
		
		playerInfo4 = new JPanel();
		playerInfo4.setLayout(null);
		playerInfo4.setBounds(268, 303, 186, 182);
		add(playerInfo4);
		
		player4Img = new JLabel("");
		player4Img.setIcon(new ImageIcon("imgs/character/face/face0.png"));
		player4Img.setBounds(10, 39, 166, 133);
		playerInfo4.add(player4Img);
		
		playe4Name = new JLabel("YaFishPhD");
		playe4Name.setFont(new Font("·s²Ó©úÅé", Font.PLAIN, 18));
		playe4Name.setBounds(10, 10, 166, 19);
		playerInfo4.add(playe4Name);
		*/
		
		if (frame.isHost){
			startButton = new JButton("Start");
			startButton.addActionListener( startListener);
			
			startButton.setBounds(542, 350, 157, 54);
			add(startButton);
		} else {
			readyButton = new JButton("Ready");
			readyButton.addActionListener( readyListener);
			
			readyButton.setBounds(542, 350, 157, 54);
			add(readyButton);
		}
		
		
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(exitListener);
		exitButton.setBounds(542, 431, 157, 54);
		add(exitButton);
		
		
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
		}
		int mapType = frame.dom.getMapType();
		mapImage = new ImageIcon("imgs/mapbackgrounds/map"+mapType+".png");
		mapLabel.setIcon(mapImage);
		
	}
	
	public void initLocal() {
		playerInfo[frame.player.getID()].addMouseListener(changeCharacterListener);
		playerInfo[frame.player.getID()].setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
	}
	
	
	private void createActionListener(){
		startListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (detectStart()){
					try {
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
		
		readyListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (readyButton.getText() == "Ready"){
					playerReadyState[frame.player.getID()].setText("Ready");
					try {
						frame.tcpcm.sendRoomAction("SETISREADY,1");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					readyButton.setText("Not Ready");
				} else {
					playerReadyState[frame.player.getID()].setText("");
					try {
						frame.tcpcm.sendRoomAction("SETISREADY,0");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					readyButton.setText("Ready");
				}
							

			}
		};
		
		exitListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		};
		
		changeCharacterListener = new MouseListener() {
		
		
		
		

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
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