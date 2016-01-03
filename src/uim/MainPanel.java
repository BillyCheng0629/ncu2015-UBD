package uim;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel {
	private JLabel gameTitle;
	private JButton startButton;
	private JButton exitButton;
	private ActionListener startActionListener;
	private ActionListener exitActionListener;
	private MouseAdapter mouseEnterStartBtListener;
	private MouseAdapter mouseEnterExitBtListener;
	private JDialog nameInput;
	private JFrame frame;
	
		
	
	/**
	 * Create the panel.
	 */
	public MainPanel(MainFrame frame) {
			super();
			this.frame=frame;
			this.setSize(1010,620);
			setLayout(null);
			nameInput=new NameInputDialog(frame);
			gameTitle = new JLabel();
			gameTitle.setIcon(new ImageIcon("./imgs/panelbackground/bgMainPanel.gif"));
			gameTitle.setBounds(-7, -12, 1010, 620);
			
			createMouseListener();
			startButton = new JButton();
			startButton.setIcon(new ImageIcon("./imgs/button/btStart.png"));
			startButton.setBorder(null);
			//startButton.addActionListener(startActionListener);
			startButton.addMouseListener(mouseEnterStartBtListener);
			startButton.setBounds(400, 300, 200, 50);
			add(startButton);
			
			exitButton = new JButton();
			exitButton.setIcon(new ImageIcon("./imgs/button/btExit.png"));
			exitButton.setBorder(null);
			//exitButton.addActionListener(exitActionListener);
			exitButton.addMouseListener(mouseEnterExitBtListener);;
			exitButton.setBounds(400, 380, 200, 50);
			add(exitButton);
			
			add(gameTitle);			
	}
	private void createMouseListener(){
		mouseEnterStartBtListener = new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 startButton.setIcon(new ImageIcon("./imgs/button/btStart2.png"));
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 startButton.setIcon(new ImageIcon("./imgs/button/btStart.png"));
			 }
			 @Override
			 public void mouseClicked(MouseEvent e){
				 nameInput.setVisible(true);
			 }
		};
		
		mouseEnterExitBtListener = new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 exitButton.setIcon(new ImageIcon("./imgs/button/btExit2.png"));
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 exitButton.setIcon(new ImageIcon("./imgs/button/btExit.png"));
			 }
			 @Override
			 public void mouseClicked(MouseEvent e){
				 frame.dispose();
			 }
		};	
	}
}
