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

	private int listenPort =9876; //default port
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
	
	
	public TCPSM() {
		
	}
	
	public TCPSM(int listenPort) {
		assert (listenPort>0 && listenPort<=65535);
		this.listenPort = listenPort;
	}
	


	public void initTCPserver() {
		t = new Thread()
	    {
	        public void run()
	        {
	        	
	        	clientIPTable = new Vector<String>();
	    		clientSocketTable = new Vector<Socket>();
	    		clientIPTable = new Vector<String>();
	    		playerIDTable = new Vector<Integer>();
	    		freePlayerIDTable = new Vector<Integer>();
	    		freePlayerIDTable.add(3);
	    		freePlayerIDTable.add(2);
	    		freePlayerIDTable.add(1);
	    		freePlayerIDTable.add(0);
	    		
	    		
	    		
	    		//clientIPTable = new Vector<String>();
	    		connectiontable = new Vector();
	    		count = 0;
	    		
	    		try {
	    			//serverSocket = new S
	    			serverSocket = new ServerSocket(listenPort);
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			System.out.println("server socket error ");
	    			e1.printStackTrace();
	    		}
	    		

	    		System.out.println("the server start on" + serverSocket.getLocalSocketAddress());
	    		Thread thread = null;
	    		while (!serverSocket.isClosed()) {
	    			try {
	    				//System.out.println("wait to client....");
	    				
	    				while(clientIPTable.size()>=4) {
	    					//lock
	    				}
	    				clientSocket = serverSocket.accept();
	    				
	    				
	    				ServerThread now;
	    				
	    				thread = new Thread(now = new ServerThread(count++, clientSocket, cdc, clientIPTable, clientSocketTable, playerIDTable, freePlayerIDTable) );
	    				clientIPTable.add(clientSocket.getRemoteSocketAddress().toString().split("/")[1].split(":")[0]);
	    				clientSocketTable.add(clientSocket);
	    				connectiontable.addElement(now);
	    				
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
	
	
	Vector<String> clientIPTable;
	Vector<Socket> clientSocketTable;
	Vector<Integer> playerIDTable;
	Vector<Integer> freePlayerIDTable;
	
	// config
	


	public ServerThread(int clientNo, Socket clientSocket, CDC cdc, Vector<String> clientIPTable , Vector<Socket> clientSocketTable, Vector<Integer> playerIDTable, Vector<Integer> freePlayerIDTable) {
		// TODO Auto-generated constructor stub
		
		this.clientNo = clientNo;
		this.clientSocket = clientSocket;
		this.cdc = cdc ;
		this.clientIPTable = clientIPTable;
		this.clientSocketTable = clientSocketTable;
		this.playerIDTable = playerIDTable;
		this.freePlayerIDTable = freePlayerIDTable;

	}

	
	private void sendMessage(String msg) {
		try {
			System.out.println("server send  "+msg);
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
				System.out.println("server broadcst to "+i);
				out = new PrintStream(clientSocketTable.get(i).getOutputStream());
				out.println(msg);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("server broadcst Error");
				e.printStackTrace();
			}
			
		}
	}
	
	
	private void handleDisconnect() {
		broadcast("REMOVEPLAYER,"+playerID);
		int index = 0;
		for (int i=0;i<playerIDTable.size();i++) {
			if (playerID == playerIDTable.get(i)) {
				index =i;
				break;
			}
		}
		System.out.println(clientSocket.getRemoteSocketAddress() + " exit");
		
		clientIPTable.remove(index);
		clientSocketTable.remove(index);
		freePlayerIDTable.add(playerIDTable.get(index));
		playerIDTable.remove(index);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("the client information :"+ (clientSocket.getRemoteSocketAddress()+"").split("/")[1].split(":")[0]);
		
		

		assert clientSocket.isConnected();
		while (clientSocket.isConnected()) {
			try {
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String msg = in.readLine();
				String[] msgArray = msg.split(",");
				
				
				
				
				
				
				
				
				
				
				switch (msgArray[0]) {
					case "MOVE":
						int moveCode = Integer.parseInt(msgArray[1]);
						
						//System.out.println("move code: "+ moveCode);
						cdc.updateDirection(playerID, moveCode);
						break;
					case "PLACE":
						cdc.placedDumpling(playerID);
						break;
					case "ADDPLAYER":
						System.out.println("server recieve ADDPLAYER sucess");
						playerID = freePlayerIDTable.get(freePlayerIDTable.size()-1);
						freePlayerIDTable.remove(freePlayerIDTable.size()-1);
						int characterNum = Integer.parseInt(msgArray[2]);
						boolean isReady = Integer.parseInt(msgArray[3])==1;
						
						sendMessage("INITFRAME,"+playerID);
						
						playerName = msgArray[1];
						player = new Player(playerName);
						player.setID(playerID);
						player.getCharacter().setCharacterNum(characterNum);
						player.setIsReady(isReady);
						player.setDirection(3);
						player.setAlive(true);
						
						broadcast(msg+","+playerID);
						
						
						for (int i=0;i<playerIDTable.size();i++) {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Player player = cdc.getPlayer(playerIDTable.get(i));
							System.out.println("server init local  player data");
							sendMessage("ADDPLAYER,"+player.getName()+","
											+player.getCharacter().getCharacterNum()+","
											+(player.getIsReady()?1:0) + "," + playerIDTable.get(i));
						}
						
						sendMessage("SETMAP,"+cdc.getMapType());
						
						playerIDTable.add(playerID);
						
						cdc.addPlayer(player, playerID);
						break;
						
					case "SETNAME":
						String name = msgArray[1];
						cdc.getPlayer(clientNo).setName(name);
						break;
						
					case "SETMAP":
						int mapType = Integer.parseInt(msgArray[1]);
						cdc.setMapType(mapType);
						broadcast(msg);
						break;
						
					case "SETCHARACTER":
						characterNum = Integer.parseInt(msgArray[1]);
						cdc.getPlayer(playerID).getCharacter().setCharacterNum(characterNum);
						broadcast(msg+","+playerID);
						break;
					/*
					case "SETISMOVING":
						boolean isMoving = Integer.parseInt(msg.split(",")[1])==1;
						cdc.getPlayer(playerID).setIsMoving(isMoving);
						broadcast(msg+","+playerID);
						break;*/
					case "SETISREADY":
						isReady = Integer.parseInt(msgArray[1])==1;
						cdc.getPlayer(playerID).setIsReady(isReady);
						broadcast(msg+","+playerID);
						break;
					case "START":
						broadcast(msg);
						Thread gameMoniterThread;
						gameMoniterThread = new Thread() {
							
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								while(true) {
									/*
									
										
									
									if (cdc.getGameState()) {
										broadcast("END");
										break;
									}*/
									
									
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
				
				//e.printStackTrace();
				handleDisconnect();
				break;
			}
		}
	}
}
