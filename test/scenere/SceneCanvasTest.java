package scenere;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dom.DOM;
import gameobject.Character;
import gameobject.Item;
import gameobject.Player;

public class SceneCanvasTest {
	JFrame frm;
	ScenePanel sceneCanvas;
	Container cp;
	SceneData scenedata;
	DOM dom;
	CDCstub cdc;
	TCPCMstub tcpcm;
	TCPSMstub tcpsm;
	UDPBCstub udpbc;
	UDPUSstub udpus;
	Player player[];
	Item items[];
	CharacterMoveListener KeyListener;
	KeyActionPerformer performer;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		setFrame();
		setPlayer();
		setDOM();
		sceneCanvas = new ScenePanel();
		sceneCanvas.setBounds(0, 0, 800, 600);
		sceneCanvas.setDOM(dom);
		setSceneData();
		sceneCanvas.setSceneData(scenedata);
		setStubServer();
		KeyListener = new CharacterMoveListener();
		sceneCanvas.addKeyListener(KeyListener);
		//performer = new KeyActionPerformer(scenedata, KeyListener, tcpcm);
		sceneCanvas.setKeyActionPerformer(performer);
		cp.add(sceneCanvas, BorderLayout.CENTER);
	}
	
	private void setFrame(){
		frm = new JFrame();
		frm.setUndecorated(true);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frm.setBounds(100, 100, 1000, 600);
		cp = frm.getContentPane();
		frm.setVisible(true);
	}
	
	private void setPlayer(){
		player = new Player[4];
		for(int i=0;i<4;i++){
			player[i] = new Player(String.valueOf(i));
			player[i].setID(i);
			Character a = new Character();
			//a.setCharacterImg(ImageName.Character);
			player[i].setCharacter(a);
		}
		
		player[0].location.x=0;
		player[0].location.y=0;

		player[1].location.x=4900;
		player[1].location.y=0;

		player[2].location.x=0;
		player[2].location.y=1800;

		player[3].location.x=4900;
		player[3].location.y=1800;
	}
	
	private void setDOM(){
		dom = new DOM();
		for(int i=0;i<4;i++){
			dom.updatePlayer(player[i]);
		}
		dom.setClientPlayerID(0);
	}
	
	private void setSceneData(){
		scenedata = new SceneData(sceneCanvas);
		scenedata.setPlayer(dom.getPlayer(dom.getClientPlayerID()));
		items = new Item[20];
		for(int i=0;i<20;i++){
			items[i] = new Item();
			items[i].setID(i);
			items[i].setType(ImageName.BombItem);
			items[i].location = new Point();
			items[i].location.x = (((int)(Math.random()*700000))%4900);
			items[i].location.y = (((int)(Math.random()*1700000))%1900);
			dom.addItem(items[i]);
		}
	}
	
	private void setStubServer(){
		cdc = new CDCstub();
		tcpcm = new TCPCMstub(frm);
		tcpsm = new TCPSMstub();
		udpbc = new UDPBCstub();
		udpus = new UDPUSstub();
		
		cdc.setPlayerstub(player);
		cdc.setItems(items);
		
		tcpcm.setDOM(dom);
		tcpcm.setTCPSM(tcpsm);
		
		tcpsm.setCDC(cdc);
		
		udpbc.setCDC(cdc);
		udpbc.setUDPUS(udpus);
		
		udpus.setDOM(dom);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMyRepaint() {
		emulateKeyPressed();
		checkObjectItem();
	}

	private void emulateKeyPressed() {
		tcpcm.updatePlayerLocation(new Point(2500,1000));
		udpbc.broadcastMessage();
		scenedata.setClientView(scenedata.getVirtualCharacterPosition());
		assertEquals(2100, scenedata.getPositionX());
		assertEquals(2900, scenedata.getPositionX()+scenedata.getPanelWidth());
		assertEquals(700, scenedata.getPositionY());
		assertEquals(1300, scenedata.getPositionY()+scenedata.getPanelHeight());
		
		tcpcm.updatePlayerLocation(new Point(5000,2000));
		udpbc.broadcastMessage();
		scenedata.setClientView(scenedata.getVirtualCharacterPosition());
		assertEquals(4300, scenedata.getPositionX());
		assertEquals(5100, scenedata.getPositionX()+scenedata.getPanelWidth());
		assertEquals(1500, scenedata.getPositionY());
		assertEquals(2100, scenedata.getPositionY()+scenedata.getPanelHeight());
		
		tcpcm.updatePlayerLocation(new Point(5000,0));
		udpbc.broadcastMessage();
		scenedata.setClientView(scenedata.getVirtualCharacterPosition());
		assertEquals(4300, scenedata.getPositionX());
		assertEquals(5100, scenedata.getPositionX()+scenedata.getPanelWidth());
		assertEquals(0, scenedata.getPositionY());
		assertEquals(600, scenedata.getPositionY()+scenedata.getPanelHeight());
		
		tcpcm.updatePlayerLocation(new Point(0,2000));
		udpbc.broadcastMessage();
		scenedata.setClientView(scenedata.getVirtualCharacterPosition());
		assertEquals(0, scenedata.getPositionX());
		assertEquals(800, scenedata.getPositionX()+scenedata.getPanelWidth());
		assertEquals(1500, scenedata.getPositionY());
		assertEquals(2100, scenedata.getPositionY()+scenedata.getPanelHeight());
		
		tcpcm.updatePlayerLocation(new Point(0,0));
		udpbc.broadcastMessage();
		scenedata.setClientView(scenedata.getVirtualCharacterPosition());
		assertEquals(0, scenedata.getPositionX());
		assertEquals(800, scenedata.getPositionX()+scenedata.getPanelWidth());
		assertEquals(0, scenedata.getPositionY());
		assertEquals(600, scenedata.getPositionY()+scenedata.getPanelHeight());
	}
	
	private void checkObjectItem(){
		dom.getItems().forEach((k,v)->assertNotNull(scenedata.getImage(v.getType())));
		//for(int i=0;i<4;i++)
			//assertNotNull(scenedata.getImage(dom.getPlayer(i).getCharacter().getCharacterImg()));
	}
	
}
