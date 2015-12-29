package scenere;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RePaintActionListener implements ActionListener {
	ScenePanel bg;
	public RePaintActionListener(ScenePanel bg) {
		this.bg = bg;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		bg.repaint();
		bg.moveCharacter();
		bg.placeBomb();
	}
}
