package scenere;

import java.awt.Point;

import tcpsm.TCPSM;

public class TCPSMstub extends TCPSM {
	private CDCstub cdc;
	public TCPSMstub(){
		super();
		cdc = new CDCstub();
	}
	
	public void setCDC(CDCstub cdc){
		this.cdc = cdc;
	}
	
	public void updatePlayerAction(int playerID, Point location) {
		cdc.updatePlayerLocation(playerID, location);
	}
}
