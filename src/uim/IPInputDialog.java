package uim;

import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class IPInputDialog extends JDialog{
	private ActionListener connectListener;
	private ActionListener cancelListener;
	private JPanel roomPanel;
	JFrame frm = (JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, this);
}
