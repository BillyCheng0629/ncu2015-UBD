package scenere;

import java.awt.Point;

import javax.swing.JFrame;

import dom.DOM;
import tcpcm.TCPCM;

public class TCPCMstub  {
	TCPSMstub tcpsm;
	DOM dom;
	public TCPCMstub(JFrame frm){
		//super(frm);
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
