package uim;

import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoomPanel {
	private JButton startButton;
	private JButton exitButton;
	private ActionListener startListener;
	private ActionListener exitListener;
	private JComboBox mapComboBox;
	private Image mapImage;
	private JLabel ipAddress;
	private JPanel playerInfo[];
	private Image playerImg[];
	private JLabel playerName[];
	private ActionListener playerImgListener;
	private ActionListener mapChooseListener;
}
