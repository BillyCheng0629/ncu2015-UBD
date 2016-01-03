package uim;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gameobject.Player;

public class ResultDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private MainFrame frame;
	private JPanel buttonPane;
	private MouseListener mouseOKBtListener;
	private JLabel resultLabel;
	private JButton okButton;
	
	public ResultDialog(MainFrame frame) {
		super();
		this.frame = frame;
		
		setBounds(200, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		int playerID = frame.player.getID();
		
		if (frame.dom.getPlayer(playerID).getAlive()) {
			resultLabel = new JLabel("You Win!!");
		} else {
			resultLabel = new JLabel("You Lose..");
		}
		
		
		
		resultLabel.setVisible(false);
		resultLabel.setBorder(null);
		resultLabel.setBounds(100, 0, 600, 200);
		resultLabel.setFont(new Font("Microsoft JhengHei", Font.BOLD, 90));	
		//contentPanel.add(resultLabel);
		
		createMouseListener();
		
		buttonPane = new JPanel();
		
		okButton = new JButton("OK");
		okButton.setBounds(200, 0, 200, 60);
		okButton.setBorder(null);
		okButton.setActionCommand("OK");
		okButton.addMouseListener(mouseOKBtListener);
		okButton.setFont(new Font("Microsoft JhengHei", Font.BOLD, 35));
		buttonPane.add(okButton);
		
		getRootPane().setDefaultButton(okButton);
		
		buttonPane.setLayout(null);
		buttonPane.setBounds(0, 200, 600, 60);
		buttonPane.setOpaque(false);//set to be not seen
		contentPanel.add(buttonPane);
		contentPanel.add(resultLabel);
		resultLabel.setVisible(true);
		okButton.setVisible(true);
		contentPanel.setVisible(true);
		
	}
	
	private void createMouseListener(){
		mouseOKBtListener = new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 //okButton.setIcon(new ImageIcon("./imgs/button/btOK2.png"));
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 //okButton.setIcon(new ImageIcon("./imgs/button/btOK.png"));
			 }
			 @Override
			 public void mouseClicked(MouseEvent e){
				 if(frame.isHost){
					 frame.udpbc.closeUPDBC();
				 }
					// TODO Auto-generated method stub
					/*frame.player=new Player(nameInputField.getText());
					System.out.println(frame.player.getName());

					frame.getContentPane().removeAll();
					frame.getContentPane().add(createRoom);
					frame.getContentPane().repaint();
					NameInputDialog.this.dispose();*/
			 }
		};
		
	}
}
