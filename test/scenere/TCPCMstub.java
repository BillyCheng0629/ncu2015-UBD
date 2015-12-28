package scenere;

import java.awt.Point;

import dom.DOM;
import tcpcm.TCPCM;

public class TCPCMstub extends TCPCM {
	TCPSMstub tcpsm;
	DOM dom;
	public TCPCMstub(){
		super();
	}
	
	public void setDOM(DOM dom){
		this.dom = dom;
	}
	
	public void setTCPSM(TCPSMstub tcpsm){
		this.tcpsm = tcpsm;
	}
	
	public void updatePlayerLocation(Point location){
		tcpsm.updatePlayerAction(dom.getClientPlayerID(), location);
	}
	
}
