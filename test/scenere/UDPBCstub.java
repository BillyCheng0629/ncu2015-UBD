package scenere;

import udpbc.UDPBC;

public class UDPBCstub extends UDPBC {
	private CDCstub cdc;
	private UDPUSstub udpus;
	public UDPBCstub(){
		super();
	}
	
	public void setCDC(CDCstub cdc){
		this.cdc = cdc;
	}
	
	public void setUDPUS(UDPUSstub udpus){
		this.udpus = udpus;
	}
	
	public void broadcastMessage(){
		for(int i=0; i<4; i++)
			udpus.updatePlayerLocation(i, cdc.getPlayerstub(i).location);
	}
}
