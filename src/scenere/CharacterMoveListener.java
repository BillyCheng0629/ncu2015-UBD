package scenere;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CharacterMoveListener extends KeyAdapter {
	public boolean right=false, left=false, up=false, down=false, space=false;
	
	public CharacterMoveListener(){
		super();
	}
	
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		switch(e.getKeyCode()){
		case(KeyEvent.VK_RIGHT):
			right = true;
			break;
		case(KeyEvent.VK_LEFT):
			left = true;
			break;
		case(KeyEvent.VK_UP):
			up = true;
			break;
		case(KeyEvent.VK_DOWN):
			down = true;
			break;
		case(KeyEvent.VK_SPACE):
			space = true;
			break;
		default:
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		switch(e.getKeyCode()){
		case(KeyEvent.VK_RIGHT):
			right = false;
			break;
		case(KeyEvent.VK_LEFT):
			left = false;
			break;
		case(KeyEvent.VK_UP):
			up = false;
			break;
		case(KeyEvent.VK_DOWN):
			down = false;
			break;
		case(KeyEvent.VK_SPACE):
			space = false;
			break;
		default:
			break;
		}
	}
}
