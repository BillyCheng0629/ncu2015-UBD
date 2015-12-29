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
			textField.setFont(new Font("新細明體", Font.PLAIN, 50));
			textField.setBounds(0, 105, 434, 58);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		JLabel lblInputIp = new JLabel("Input IP");
		lblInputIp.setFont(new Font("新細明體", Font.PLAIN, 50));
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
				//connect server
				
				frame.tcpClient = new TCPCM(frame);
				frame.tcpClient.connectServer(textField.getText());
				
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(roomPanel);
				frame.getContentPane().repaint();
				IPInputDialog.this.dispose();
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
