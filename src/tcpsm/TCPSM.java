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

	private int listenPort =8888; //default port
	private ServerSocket serverSocket;

	private Socket clientSocket; // the socket
	private Vector<Socket> clientSocketTable;

	private Vector<String> clientIPTable;
	private Vector connectiontable;
	private Vector<Integer> playerIDTable;
	private Vector<Integer> freePlayerIDTable;
	private int count;
	private Thread t;
	private int clientLimit = 4;
	private CDC cdc;
	private PrintStream out = null;
	private ArrayList<Integer> aList;
	
	
	public TCPSM(int listenPort) {
		assert (listenPort>0 && listenPort<=65535);
		this.listenPort = listenPort;
		freePlayerIDTable.add(0);
		freePlayerIDTable.add(1);
		freePlayerIDTable.add(2);
		freePlayerIDTable.add(3);
		
		
		
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
	    		while (!serverSocket.isClosed()) {
	    			try {
	    				//System.out.println("wait to client....");
	    				while(true) {
	    					if (count<clientLimit)
	    						break;
	    				}
	    				clientSocket = serverSocket.accept();
	    				
	    				clientSocketTable.add(clientSocket);
	    				ServerThread now;
	    				
	    				thread = new Thread(now = new ServerThread(count++, clientSocket, cdc, clientSocketTable, playerIDTable, freePlayerIDTable) );
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
	Player player;
	int playerID;
	String playerName;
	Socket clientSocket = null;
	PrintStream out = null;
	BufferedReader in = null;
	int clientNo;
	CDC cdc;
	
	Vector<Socket> clientSocketTable;
	Vector<Integer> playerIDTable;
	Vector<Integer> freePlayerIDTable;
	
	// config
	


	public ServerThread(int clientNo, Socket clientSocket, CDC cdc, Vector<Socket> clientSocketTable, Vector<Integer> playerIDTable, Vector<Integer> freePlayerIDTable) {
		// TODO Auto-generated constructor stub
		
		this.clientNo = clientNo;
		this.clientSocket = clientSocket;
		this.cdc = cdc ;
		this.clientSocketTable = clientSocketTable;
		this.playerIDTable = playerIDTable;
		this.freePlayerIDTable = freePlayerIDTable;

	}
	
	
	
	private void sendMessage(String msg) {
		try {
			out = new PrintStream(clientSocket.getOutputStream());
			out.println(msg);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void  broadcast(String msg)  {
		for (int i=0;i<clientSocketTable.size();i++) {
			try {
				out = new PrintStream(clientSocketTable.get(i).getOutputStream());
				out.println(msg);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("the client information :"+ (clientSocket.getRemoteSocketAddress()+"").split("/")[1].split(":")[0]);


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
						cdc.updateDirection(playerID, moveCode);
						break;
					case "PLACE":
						cdc.placedDumpling(playerID);
						break;
					case "ADDPLAYER":
						playerID = freePlayerIDTable.get(freePlayerIDTable.size()-1);
						freePlayerIDTable.remove(freePlayerIDTable.size()-1);
						
						
						playerName = msg.split(",")[1];
						player = new Player(playerName);
						
						broadcast(msg+","+playerID);
						
						
						for (int i=0;i<playerIDTable.size();i++) {
							sendMessage("ADDPLAYER,"+playerName+","+playerIDTable.get(i));
						}
						//sendMessage("SETMAP,"+cdc.getMapType);
						
						playerIDTable.add(playerID);
						cdc.addPlayer(player, playerID);
						break;
						
					case "SETNAME":
						String name = msg.split(",")[1];
						cdc.getPlayer(clientNo).setName(name);
						break;
						
					case "SETMAP":
						
						broadcast(msg);
						break;
						
					case "SETCHARACTER":
						int characterNum = Integer.parseInt(msg.split(",")[1]);
						cdc.getPlayer(playerID).getCharacter().setCharacterNum(characterNum);;;
						broadcast(msg+","+playerID);
						break;
					case "SETISMOVING":
						boolean isMoving = Integer.parseInt(msg.split(",")[1])==1;
						cdc.getPlayer(playerID).setIsMoving(isMoving);
						broadcast(msg+","+playerID);
						break;
					case "SETISREADY":
						boolean isReady = Integer.parseInt(msg.split(",")[1])==1;
						cdc.getPlayer(playerID).setIsReady(isReady);
						broadcast(msg+","+playerID);
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
				break;
			}

		}
		

	}

	




}
