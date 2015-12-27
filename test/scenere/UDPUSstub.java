package scenere;

import java.awt.Point;

import dom.DOM;
import udpus.UDPUS;

public class UDPUSstub extends UDPUS {
	DOM dom;
	public UDPUSstub(){
		
	}
	
	public void setDOM(DOM dom){
		this.dom = dom;
	}
	
	public void updatePlayerLocation(int playerID, Point location){
		dom.getPlayer(playerID).setPlayerLocation(location);
	}
	
}
