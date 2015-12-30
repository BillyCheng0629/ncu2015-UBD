package uim;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class RoomPanel extends JPanel{
	private JButton startButton;
	private JButton exitButton;
	private ActionListener startListener;
	private ActionListener exitListener;
	private JLabel playe1Name,playe2Name,playe3Name,playe4Name;
	private JComboBox mapComboBox;
	private Image mapImage;
	private JLabel ipAddress;
	private JPanel playerInfo[];
	private Image playerImg[];
	private JLabel playerName[];
	private ActionListener playerImgListener;
	private ActionListener mapChooseListener;
	private JFrame frame;

	public RoomPanel(MainFrame frame){
		super();
		this.frame=frame;
		frame.setSize(800,600);
		
		
		setLayout(null);
		createActionListener();
		ipAddress = new JLabel("Room:140.115.123.123");
		ipAddress.setFont(new Font("新細明體", Font.PLAIN, 24));
		ipAddress.setHorizontalAlignment(SwingConstants.CENTER);
		ipAddress.setBounds(31, 30, 217, 37);
		add(ipAddress);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\map.png"));
		
		mapComboBox = new JComboBox();
		mapComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u967D\u5168\u9152\u5BB6", "\u5DE5\u4E94", "\u91D1\u9B5A\u5BB6"}));
		mapComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					System.out.println("U are selecting map .");
					if(mapComboBox.getSelectedItem()=="\u967D\u5168\u9152\u5BB6"){
						lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\map.png"));
						
					}
					if(mapComboBox.getSelectedItem()=="\u5DE5\u4E94"){
						lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\map2.jpg"));
					}
					if(mapComboBox.getSelectedItem()=="\u91D1\u9B5A\u5BB6"){
						lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\hqdefault.jpg"));
					}
				}
			}
		});
		mapComboBox.setToolTipText("Map");
		mapComboBox.setBounds(542, 96, 157, 21);
		add(mapComboBox);
		
		JPanel panel = new JPanel();
		panel.setBounds(542, 127, 157, 139);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(31, 84, 186, 182);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\dodo12.jpg"));
		lblNewLabel_1.setBounds(10, 39, 166, 133);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u897F\u8857\u901F\u5EA6\u738B");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 10, 166, 19);
		panel_1.add(lblNewLabel_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\star-icon (1).png"));
		label_3.setBounds(148, 0, 38, 30);
		panel_1.add(label_3);
		createActionListener();
		JButton btnNewButton = new JButton("Start/Ready");
		btnNewButton.addActionListener( startListener);
		
		btnNewButton.setBounds(542, 350, 157, 54);
		add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(exitListener);
		btnExit.setBounds(542, 431, 157, 54);
		add(btnExit);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(268, 84, 186, 182);
		add(panel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\046.jpg"));
		label.setBounds(10, 39, 166, 133);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("\u5B85\u7DB8");
		label_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		label_1.setBounds(10, 10, 166, 19);
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(31, 303, 186, 182);
		add(panel_3);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\051.jpg"));
		label_2.setBounds(10, 39, 166, 133);
		panel_3.add(label_2);
		
		JLabel lblYubing = new JLabel("Yu-Bing");
		lblYubing.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblYubing.setBounds(10, 10, 166, 19);
		panel_3.add(lblYubing);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(268, 303, 186, 182);
		 add(panel_4);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("C:\\Users\\aker\\workspace\\PrototypeA\\045.jpg"));
		label_4.setBounds(10, 39, 166, 133);
		panel_4.add(label_4);
		
		JLabel lblYafishphd = new JLabel("YaFishPhD");
		lblYafishphd.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblYafishphd.setBounds(10, 10, 166, 19);
		panel_4.add(lblYafishphd);
	}
	private void createActionListener(){
		startListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				

			}
		};
		exitListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		};
	}

}