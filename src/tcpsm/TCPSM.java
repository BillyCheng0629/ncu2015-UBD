package tcpsm;

import java.io.IOException;
import java.util.Vector;

import cdc.CDC;

public class TCPSM {
	Thread listenTread;
	TCPSM() {}
	
	/**
	 * called by UIM to start TCP server
	 * and begin to listen connections from client computers.
	 * The server should maintain a table to keep the ip addresss
	 * of client computers which connects to this server. 
	 */
	void initTCPServer() throws IOException{
		listenTread = new Thread(Acceptor.getInstance());
		listenTread.start();
	}
	
	/**
	 * called by UDPBC
	 * After all the connections are established
	 * UDPBC may starts to broadcast.
	 * Before it starts to broadcast, it will need the IP addresses
	 * of the client computer
	 * this method return all the IP addresses of client computer
	 * and put them in a vector
	 * 
	 * @return      IP table(String)
	 */
	Vector<String> getClientIPTable() {
		assert listenTread!= null: "getClientIPTable() :need to init TCP server first";
		return Acceptor.getInstance().getIPTable();
	}
	
	/**
	 * call by UIM
	 * set a new data center to TCP server
	 * 
	 * @param  cdc  xxx
	 */
	void setCDC(CDC cdc){
		
	}
	
	/** 
	 * send message to all client
	 * 
	 * @param message the string to send
	 */
	void broadcast(String message) {
		
	}
}
