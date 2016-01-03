package uim;


import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dom.DOM;
import gameobject.Player;;

public class StatePanel extends JPanel {

	DOM dom;
	Player player[];
	JLabel playerName[];
	JLabel playerIcon[];
	JLabel playerState[];
	JLabel timer;
	
	public StatePanel(){
		super();
		setPanel();
	}
	
	private void setPanel(){
		setBounds(800, 0, 200, 600);
		setLayout(new FlowLayout());
	}
	
	private void setPlayerNameLabel(){
		playerName = new JLabel[4];
		for(int i=0;i<4;i++){
			if(player[i]!=null)
				playerName[i] = new JLabel(player[i].getName(), JLabel.CENTER);
			else
				playerName[i] = new JLabel();
		}
	}
	
	private void setPlayerIcon(){
		playerIcon = new JLabel[4];
		for(int i=0;i<4;i++){
			if(player[i]!=null)
				playerIcon[i] = new JLabel(new ImageIcon("./imgs/character/role/role"+player[i].getCharacter().getCharacterNum()+".png"), JLabel.CENTER);
			else
				playerIcon[i] = new JLabel();
		}
	}
	
	private void setPlayerState(){
		playerState = new JLabel[4];
		for(int i=0;i<4;i++){
			if(player[i]!=null)
				playerState[i] = new JLabel((player[i].getAlive()?"Live":"Dead"), JLabel.CENTER);
			else
				playerState[i] = new JLabel();
		}
	}
	
	private void addPlayerStateToPanel() {
		for(int i=0;i<4;i++){
			add(playerName[i]);
			add(playerIcon[i]);
			add(playerState[i]);
		}
		add(timer);
	}
	
	public void setDOM(DOM dom){
		this.dom = dom;
		player = dom.getPlayers();
		setPlayerNameLabel();
		setPlayerIcon();
		setPlayerState();
		timer = new JLabel(dom.getGameTime(), JLabel.CENTER);
		addPlayerStateToPanel();
	}
}
