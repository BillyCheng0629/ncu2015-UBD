package uim;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
public class MainPanel extends JPanel {
	private JLabel gameTitle;
	private JButton startButton;
	private JButton exitButton;
	private ActionListener startActionListener;
	private ActionListener exitActionListener;
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
			gameTitle = new JLabel("Dumpling Man");
			gameTitle.setFont(new Font("Times New Roman", Font.PLAIN, 39));
			gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
			gameTitle.setBounds(218, 124, 369, 120);
			
			add(gameTitle);
			createActionListener();
			JButton startButton = new JButton("startButton");
			startButton.addActionListener(startActionListener);
			startButton.setBounds(674, 357, 202, 66);
			add(startButton);
			
			JButton exitButton = new JButton("exitButton");
			exitButton.addActionListener(exitActionListener);
			exitButton.setBounds(674, 464, 202, 66);
			add(exitButton);
			
			
			
	}
	private void createActionListener(){
		startActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				nameInput.setVisible(true);
				//frame.removeAll();
				
			}
		};
		exitActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				
			}
		};
	}
}
