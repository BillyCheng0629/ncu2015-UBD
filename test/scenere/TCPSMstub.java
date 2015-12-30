package scenere;

import java.awt.Point;

public class TCPSMstub{
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
