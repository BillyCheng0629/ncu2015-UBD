package uim;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import gameobject.Player;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class NameInputDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private MouseListener mouseEnterOKBtListener;
	private MouseListener mouseEnterCancelBtListener;

	private JPanel createRoom;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblNewLabel;
	
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
		
		setBounds(200, 100, 600, 280);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			lblNewLabel = new JLabel();
			lblNewLabel.setVisible(false);
			lblNewLabel.setIcon(new ImageIcon("./imgs/panelbackground/bgInputName.png"));
			lblNewLabel.setBounds(0, 0, 600, 280);
			//lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
			nameInputField = new JTextField();
			nameInputField.setBounds(143, 87, 310, 42);
			nameInputField.setBorder(null);
			nameInputField.setFont(new Font("Microsoft JhengHei", Font.BOLD, 35));
			nameInputField.setHorizontalAlignment(JTextField.CENTER);
			
			contentPanel.add(nameInputField);
			nameInputField.setColumns(10);
		
			createMouseListener();
			buttonPane = new JPanel();
				okButton = new JButton();
				okButton.setBorder(null);
				okButton.setIcon(new ImageIcon("./imgs/button/btOK.png"));
				okButton.setActionCommand("OK");
				okButton.addMouseListener(mouseEnterOKBtListener);
				
				getRootPane().setDefaultButton(okButton);
			
				cancelButton = new JButton();
				cancelButton.setBorder(null);
				cancelButton.setIcon(new ImageIcon("./imgs/button/btExit.png"));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(mouseEnterCancelBtListener);
				
				buttonPane.add(okButton);
				buttonPane.add(cancelButton);
				
			buttonPane.setLayout(new FlowLayout());
			buttonPane.setBounds(0, 150, 600, 60);
			buttonPane.setOpaque(false);//set to be not seen
			contentPanel.add(buttonPane);
			contentPanel.add(lblNewLabel);
			lblNewLabel.setVisible(true);
			okButton.setVisible(true);
			cancelButton.setVisible(true);
			contentPanel.setVisible(true);
	}
	
	private void createMouseListener(){
		mouseEnterOKBtListener = new MouseAdapter() {
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
					frame.player=new Player(nameInputField.getText());
					System.out.println(frame.player.getName());

					frame.getContentPane().removeAll();
					frame.getContentPane().add(createRoom);
					frame.getContentPane().repaint();
					NameInputDialog.this.dispose();
			 }
		};
		
		mouseEnterCancelBtListener = new MouseAdapter() {
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
				 NameInputDialog.this.dispose();
			 }
		};	
	}
}
