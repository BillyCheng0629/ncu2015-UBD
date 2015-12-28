package spritere;

import static org.junit.Assert.*;
import gameobject.Character;
import gameobject.Player;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dom.DOM;
import scenere.ImageName;

public class SpritereTest {
	JFrame frame;
	Player player[];
	DOM dom;
	@Before
	public void setUp() throws Exception {
		setFrame();
		setPlayer();
		setDOM();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	private void setFrame(){
		frame = new JFrame();
		frame.setSize(200, 200);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	private void setPlayer(){
		player = new Player[4];
		for(int i=0;i<4;i++){
			player[i] = new Player(String.valueOf(i));
			player[i].setID(i);
			Character a = new Character();
			a.setCharacterImg(ImageName.Character);
			player[i].setCharacter(a);
		}
	}
	
	private void setDOM(){
		dom = new DOM();
		for(int i=0;i<4;i++){
			dom.updatePlayer(player[i]);
		}
		dom.setClientPlayerID(0);
	}

}
