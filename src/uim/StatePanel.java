package uim;


import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dom.DOM;
import gameobject.Player;;

public class StatePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DOM dom;
	Player player[];
	JLabel playerName[];
	JLabel playerIcon[];
	JLabel playerState[];
	JLabel timer;
	Font normalFont;
	
	public StatePanel(){
		super();
		setPanel();
	}
	
	private void setPanel(){
		setBounds(800, 0, 200, 600);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		normalFont = new Font("Consolas", Font.PLAIN, 18);
	}
	
	private void setPlayerNameLabel(){
		playerName = new JLabel[4];
		for(int i=0;i<4;i++){
			if(player[i]!=null){
				playerName[i] = new JLabel(player[i].getName(), JLabel.CENTER);
				playerName[i].setFont(normalFont);
				playerName[i].setAlignmentX(CENTER_ALIGNMENT);
			}
			else{
				playerName[i] = new JLabel();
				playerName[i].setAlignmentX(CENTER_ALIGNMENT);
			}
		}
	}
	
	private void setPlayerIcon(){
		playerIcon = new JLabel[4];
		for(int i=0;i<4;i++){
			if(player[i]!=null){
				playerIcon[i] = new JLabel(new ImageIcon("./imgs/character/face/face"+player[i].getCharacter().getCharacterNum()+".png"), JLabel.CENTER);
				playerIcon[i].setAlignmentX(CENTER_ALIGNMENT);
			}
			else{
				playerIcon[i] = new JLabel();
				playerIcon[i].setAlignmentX(CENTER_ALIGNMENT);
			}
		}
	}
	
	private void setPlayerState(){
		playerState = new JLabel[4];
		for(int i=0;i<4;i++){
			if(player[i]!=null){
				playerState[i] = new JLabel((player[i].getAlive()?"Live":"Dead"), JLabel.CENTER);
				playerState[i].setFont(normalFont);
				playerState[i].setAlignmentX(CENTER_ALIGNMENT);
			}
			else{
				playerState[i] = new JLabel();
			}
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
		timer.setAlignmentX(CENTER_ALIGNMENT);
		timer.setFont(new Font("Consolas", Font.BOLD, 34));
		addPlayerStateToPanel();
	}
	
	public void updateState(){
		for(int i=0;i<4;i++){
			if(player[i]!=null){
				playerState[i].setText((player[i].getAlive()?"Live":"Dead"));
				if(!player[i].getAlive()){
					playerState[i].setForeground(Color.RED);
				}
			}
		}
		timer.setText(dom.getGameTime());
	}
	
}
