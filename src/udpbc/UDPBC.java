package udpbc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import cdc.CDC;
import dom.DOM;
import gameobject.Dumpling;
import tcpsm.TCPSM;

public class UDPBC {
	private int BUFFERSIZE;
	private DatagramSocket clientSocket;
	private byte[] sendData;
	private Vector<String> IPTable;
	private Queue<Object> deleteQueue;
	private Vector<Object> itemVector;
	private String itemInfo;
	private DatagramPacket sendPacket;
	
	private CDC cdc;
	private TCPSM tcpsm;
	public UDPBC(TCPSM _TCPSM, CDC _CDC) throws SocketException{
		this.clientSocket = new DatagramSocket();
		this.BUFFERSIZE = 256;
		this.sendData = null;
		this.IPTable = null;
		this.itemVector = null;
		this.itemInfo = "";
		this.sendPacket = null;
		this.itemVector = new Vector<Object>();
		
		this.deleteQueue = new LinkedList();
		this.tcpsm = _TCPSM;
		this.cdc = _CDC;
	}
	
	public void startUDPBroadcast() throws Exception {
		
		IPTable = tcpsm.getClientIPTable();
		assert(IPTable.size()<=4 && IPTable.size()>=2);

		Timer t = new Timer();
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				
				//deleteQueue = cdc.getDeleteQueue();
				for (Object deleteItem : deleteQueue) {
					itemInfo = deleteItem.toString();
					int ID = Integer.parseInt(itemInfo.split(" ")[1]);
					if (deleteItem instanceof Dumpling){
						//cdc.removeDumpling(ID)
					} else {
						cdc.removeItem(ID);
					}
					itemInfo = "DELETE" + itemInfo;
					try {
						broadcastMessage(clientSocket, itemInfo, IPTable);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				itemVector = cdc.getUpdateInfo();
				assert(itemVector.size()>0);
				for (Object item : itemVector){
					if (item != null) {
						itemInfo = item.toString();
						
						if ( item instanceof String){ // TIME
							itemInfo = itemInfo.substring(1, itemInfo.length()-1);
						}
						
						itemInfo = "UPDATE " + itemInfo;
						try {
							broadcastMessage(clientSocket, itemInfo, IPTable);
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
		},0,200);
		
	}
	
	public void broadcastMessage(DatagramSocket socket, String message, Vector<String> IPTable) throws UnknownHostException{
		sendData = message.getBytes();
		for(String ip : IPTable){
			InetAddress IPAddress = InetAddress.getByName(ip);
			sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7654);
			try {
				socket.send(sendPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void closeUPDBC(){
		this.clientSocket.close();
	}
	
	public void startGenerateItem(){
		cdc.startItemPlacedThread();
	}
}
