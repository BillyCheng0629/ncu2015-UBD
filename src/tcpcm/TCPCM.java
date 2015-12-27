package tcpcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.print.event.PrintJobAdapter;
import javax.security.auth.SubjectDomainCombiner;

import dom.DOM;

public class TCPCM {
	private int clientNo;
	private String serverIP;
	private int serverPort = 8888;
	private Socket socket;
	private Thread recieveThread;
	private BufferedReader in;
	private DOM dom;
	
	

	
	
	/*
	public TCPClient(InetAddress serverIP, int serverPort) {
		this.serverIP =serverIP;
		this.serverPort = serverPort;
		
		
	}
	*/
	
	
	public Boolean connectServer(String ip, int port) {
		serverIP = ip;
		serverPort = port;
		

		try {
			socket = new Socket(ip, port);
			return true;
		} catch (IOException e) {
			System.out.println("Error:"+e.getMessage());
			return false;
		}
		
	}
	
	public void inputAction(int actionCode) throws IOException {
		PrintStream out = new PrintStream(socket.getOutputStream());
		
		assert (actionCode >= 0 && actionCode<=5);
		switch (actionCode) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			
			out.println("MOVE,"+actionCode);
			out.flush();
			
			break;
		case 5:
			out.println("PLACE,");
			out.flush();

		default:
			break;
		}
		
		
	}
	
	
	public void sendRoomAction(String msg) throws IOException {
		PrintStream out = new PrintStream(socket.getOutputStream());
		out.println(msg);
		out.flush();
	}
	
	public void recieveMessage() {
		recieveThread = new Thread() {
			public void run()
	        {
				while(true) {
					try {
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String msg = in.readLine();
						
						
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        }
		};
	}
	
	public void setDOM(DOM dom) {
		this.dom = dom;
	}
}
