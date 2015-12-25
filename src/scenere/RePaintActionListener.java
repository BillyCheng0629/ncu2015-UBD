package scenere;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RePaintActionListener implements ActionListener {
	SceneCanvas bg;
	public RePaintActionListener(SceneCanvas bg) {
		this.bg = bg;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		bg.myRepaint();
	}
}
