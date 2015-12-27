package tcpsm;


import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.Vector;


import cdc.CDC;
import gameobject.Player;


public class TCPSM {
	private int listenPort =8001; //default port
	private ServerSocket serverSocket;

	private Socket clientSocket; // the socket
	private Vector<Socket> clientSocketTable;

	private Vector<String> clientIPTable;
	private Vector connectiontable;
	private ArrayDeque<Integer> freePlayerIDTable;
	private int count;
	private Thread t;
	private int clientLimit = 4;
	private CDC cdc;
	private PrintStream out = null;
	
	
	public TCPSM(int listenPort) {
		assert (listenPort>0 && listenPort<=65535);
		this.listenPort = listenPort;
		
		
	}
	
	private void assertPlayerID() {
		
	}
	
	

	public void initTCPserver() {
		t = new Thread()
	    {
	        public void run()
	        {
	        	
					
	    		clientIPTable = new Vector<String>();
	    		connectiontable = new Vector();
	    		count = 0;
	    		
	    		try {
	    			serverSocket = new ServerSocket(listenPort);
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			System.out.println("server socket error ");
	    			e1.printStackTrace();
	    		}
	    		

	    		//System.out.println("the server information:" + serverSocket.getLocalSocketAddress());
	    		Thread thread = null;
	    		while (count<clientLimit && !serverSocket.isClosed()) {
	    			try {
	    				//System.out.println("wait to client....");
	    				while(true) {
	    					if (count<clientLimit)
	    						break;
	    				}
	    				clientSocket = serverSocket.accept();
	    				
	    				clientSocketTable.add(clientSocket);
	    				ServerThread now;
	    				
	    				thread = new Thread(now = new ServerThread(count++, clientSocket, cdc, clientSocketTable, freePlayerIDTable) );
	    				connectiontable.addElement(now);
	    				clientIPTable.add(clientSocket.getRemoteSocketAddress().toString().split("/")[1].split(":")[0]);
	    				thread.start();

	    			} catch (IOException e) {
	    				System.out.println("some error:" + e.getMessage());
	    			}

	    		}
	            
	        }
	        
	    };
	    t.start();
		
		

	}
	
	public void setCDC(CDC cdc) {
		this.cdc = cdc;
	}
	
	public Vector<String> getClientIPTable() {
		return clientIPTable;
	}
	
	public boolean isBound() {
		
		return serverSocket.isBound();
	}
	
	public int getListenPort() {
		return listenPort;
	}
	
	
		
	
	
	public void closeTCP() throws IOException {
		//serverSocket.setReuseAddress(true);
		serverSocket.close();
		t.interrupt();
		
	}
}


class ServerThread implements Runnable {

	Socket clientSocket = null;
	PrintStream out = null;
	BufferedReader in = null;
	int clientNo;
	CDC cdc;
	
	Vector<Socket> clientSocketTable;
	ArrayDeque<Integer> freePlayerIDTable;
	
	// config
	


	public ServerThread(int clientNo, Socket clientSocket, CDC cdc, Vector<Socket> clientSocketTable, ArrayDeque<Integer> freePlayerIDTable) {
		// TODO Auto-generated constructor stub
		this.clientNo = clientNo;
		this.clientSocket = clientSocket;
		this.cdc = cdc ;
		this.clientSocketTable = clientSocketTable;
		this.freePlayerIDTable = freePlayerIDTable;

	}
	
	public void  broadcast(String msg) throws IOException {
		for (int i=0;i<clientSocketTable.size();i++) {
			out = new PrintStream(clientSocketTable.get(i).getOutputStream());
			out.println(msg);
			out.flush();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("the client information :"+ (clientSocket.getRemoteSocketAddress()+"").split("/")[1].split(":")[0]);
		Player player = new Player("");
		int playerID = freePlayerIDTable.pop();
		player.setID(playerID);
		cdc.addPlayer(player, playerID);
		
		
		try {
			broadcast("ADDPLAYER,"+playerID);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		assert clientSocket.isConnected();
		while (clientSocket.isConnected()) {
			try {
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String msg = in.readLine();
				
				String action = msg.split(",")[0];
				
				
				
				
				
				
				
				
				switch (action) {
					case "MOVE":
						int moveCode = Integer.parseInt(msg.split(",")[1]);
						
						//System.out.println("move code: "+ moveCode);
						cdc.updateDirection(clientNo, moveCode);
						break;
					case "PLACE":
						//cdc.placedDumpling(clientNo);
						break;
						
					case "SETNAME":
						String name = msg.split(",")[1];
						cdc.getPlayer(clientNo).setName(name);
						break;
						
					case "SETMAP":
						
						broadcast(msg);
						break;
						
					case "SETCHARACTER":
						
						//cdc.getPlayer(clientNo).getCharacter().setCharacterImg(characterImg);;
						//broadcast("SETCHARACTER, clientNo, mapType");
						break;
					case "SETREADYSTATE":
						
						//broadcast("SETREADYSTATE, clientNo, 0 or 1");
						break;
					case "START":
						Thread gameMoniterThread;
						gameMoniterThread = new Thread() {
							
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								while(true) {
									/*
									if (check game) {
										broadcast("END");
										break;
									}
									
										
									*/
									
									
								}
							}
							
						};
						//
						break;
					
	
					default:
						
						break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	



}
