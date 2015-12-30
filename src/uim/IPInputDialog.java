package uim;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
	private ActionListener connectListener;
	private ActionListener cancelListener;
	private JPanel roomPanel;
	private MainFrame frame;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setFont(new Font("�s�ө���", Font.PLAIN, 50));
			textField.setBounds(0, 105, 434, 58);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		JLabel lblInputIp = new JLabel("Input IP");
		lblInputIp.setFont(new Font("�s�ө���", Font.PLAIN, 50));
		lblInputIp.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputIp.setBounds(92, 37, 233, 58);
		contentPanel.add(lblInputIp);
		createActionListener();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Connect");
				okButton.setActionCommand("OK");
				okButton.addActionListener(connectListener);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(cancelListener);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void createActionListener(){
		connectListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				
				
				
				
				
				//set dom
				frame.dom = new DOM();
				
				//connect to server
				frame.tcpcm = new TCPCM(frame);
				frame.tcpcm.connectServer(textField.getText());
				frame.tcpcm.startRecieveMessage();
				
				try {
					int characterType = 0;
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
		cancelListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IPInputDialog.this.dispose();
			}
		};
	}
}
