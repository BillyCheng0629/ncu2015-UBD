package uim;

import gameobject.Player;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dom.DOM;
import tcpcm.TCPCM;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class IPInputDialog extends JDialog {
	private MouseListener okBtMouseListener;
	private MouseListener cancelBtMouseListener;
	private JPanel roomPanel;
	private MainFrame frame;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel bgLabel;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public IPInputDialog(MainFrame frame) {
		super();
		this.frame=frame;
		roomPanel=new RoomPanel(frame);
		setBounds(200, 100, 600, 280);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			bgLabel = new JLabel();
			bgLabel.setVisible(false);
			bgLabel.setIcon(new ImageIcon("./imgs/panelbackground/bgInputIP.png"));
			bgLabel.setBounds(0,0,600,280);
			
			textField = new JTextField();
			textField.setBounds(150,101,301,35);
			textField.setBorder(null);
			textField.setFont(new Font("Microsoft JhengHei", Font.BOLD, 35));
			textField.setHorizontalAlignment(JTextField.CENTER);
			
			textField.setColumns(10);

			createMouseListener();
		
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout());
			buttonPane.setBounds(0, 150, 600, 60);
			buttonPane.setOpaque(false);//set to be not seen
		
				okButton = new JButton();
				okButton.setBorder(null);
				okButton.setIcon(new ImageIcon("./imgs/button/btOK.png"));
				okButton.setActionCommand("OK");
				okButton.addMouseListener(okBtMouseListener);
				getRootPane().setDefaultButton(okButton);
	
				cancelButton = new JButton();
				cancelButton.setBorder(null);
				cancelButton.setIcon(new ImageIcon("./imgs/button/btExit.png"));
				cancelButton.addMouseListener(cancelBtMouseListener);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(okButton);
				buttonPane.add(cancelButton);
			contentPanel.add(buttonPane, BorderLayout.SOUTH);
			contentPanel.add(bgLabel, BorderLayout.NORTH);
			contentPanel.add(textField);
			bgLabel.setVisible(true);
			okButton.setVisible(true);
			cancelButton.setVisible(true);		
			contentPanel.setVisible(true);
	}
	private void createMouseListener(){
		okBtMouseListener = new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 okButton.setIcon(new ImageIcon("./imgs/button/btOK2.png"));
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 okButton.setIcon(new ImageIcon("./imgs/button/btOK.png"));
			 }
			 @Override
			 public void mouseClicked(MouseEvent e){
					// TODO Auto-generated method stub	
					//set dom
					frame.dom = new DOM();
					
					//connect to server
					frame.tcpcm = new TCPCM(frame);
					frame.tcpcm.connectServer(textField.getText());
					frame.tcpcm.startRecieveMessage();
					
					try {
						int characterType = 1;
						int isReady = 0;
						frame.tcpcm.sendRoomAction("ADDPLAYER,"+frame.player.getName()+","+characterType+","+isReady);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("add player error");
						e1.printStackTrace();
					}
					
					IPInputDialog.this.dispose();
				
					frame.getContentPane().removeAll();
					frame.getContentPane().add(frame.roomPanel);
					frame.roomPanel.setBounds(20, 5, 780, 595);
					frame.getContentPane().add(frame.roomPanel);
					frame.getContentPane().repaint();	
			 }
		};
		
		cancelBtMouseListener = new MouseAdapter() {
			 @Override
			 public void mouseEntered(MouseEvent e) {
				 cancelButton.setIcon(new ImageIcon("./imgs/button/btExit2.png"));
			 }
			 @Override
			 public void mouseExited(MouseEvent e) {
				 cancelButton.setIcon(new ImageIcon("./imgs/button/btExit.png"));
			 }
			 @Override
			 public void mouseClicked(MouseEvent e){
				 IPInputDialog.this.dispose();
			 }
		};	
	}
}
