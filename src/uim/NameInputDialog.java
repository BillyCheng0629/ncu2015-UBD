package uim;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gameobject.Player;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class NameInputDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ActionListener okListener;
	private ActionListener exitListener;
	private Player player;
	private JPanel createRoom;
	
	private JTextField nameInputField;
	private MainFrame frame;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public NameInputDialog(MainFrame frame) {
		super();
		this.frame=frame;
		createRoom =new CreateRoomPanel(frame);
		
		setBounds(100, 100, 350, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JLabel lblNewLabel = new JLabel("Input your name");
			lblNewLabel.setBounds(82, 31, 159, 28);
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
			contentPanel.add(lblNewLabel);
			createActionListener();
		
			nameInputField = new JTextField();
			nameInputField.setBounds(82, 69, 159, 21);
			
			contentPanel.add(nameInputField);
			nameInputField.setColumns(10);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(okListener);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(exitListener);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			
		
	}
	private void createActionListener(){
		
		okListener =new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.player=new Player(nameInputField.getText());
				System.out.println(frame.player.getName());
				
				
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(createRoom);
				frame.getContentPane().repaint();
				NameInputDialog.this.dispose();
			}
		};
		exitListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NameInputDialog.this.dispose();
			}
		};
	}

}
