

package tcpcm;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.print.event.PrintJobAdapter;
import javax.security.auth.SubjectDomainCombiner;

import dom.DOM;
import gameobject.Player;

public class TCPCM {
	private int clientNo;
	private String serverIP;
	private int serverPort = 8888;
	private Socket socket;
	private Thread recieveThread;
	private BufferedReader in;
	private DOM dom;
	private Frame frame;
	
	

	
	
	
	public TCPCM(Frame f) {
		this.frame = frame;
		
		
	}
	
	
	
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
	
	public void inputAction(int actionCode) {
		PrintStream out;
		try {
			out = new PrintStream(socket.getOutputStream());
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public void sendRoomAction(String msg) throws IOException {
		PrintStream out = new PrintStream(socket.getOutputStream());
		out.println(msg);
		out.flush();
	}
	
	public void recieveMessage() {
		recieveThread = new Thread() {
			private int playerID;
			public void run()
	        {
				while(true) {
					try {
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String msg = in.readLine();
						
						String action = msg.split(",")[0];
						
						switch (action) {
						case "ADDPLAYER":
							Player player = new Player("");
							playerID = Integer.parseInt(msg.split(",")[1]);
							player.setID(playerID);
							//dom.setPlayer(playerID, player);
							break;
						case "SETMAP":
							int mapType = Integer.parseInt(msg.split(",")[1]);
							dom.setMapType(mapType);
							break;
							
						case "SETCHARACTER":
							
							playerID = Integer.parseInt(msg.split(",")[1]);
							int charcterType = Integer.parseInt(msg.split(",")[2]);
							//dom.getPlayer(playerID).setCharacter(character);
							break;
						case "SETREADYSTATE":
							playerID = Integer.parseInt(msg.split(",")[1]);
							boolean readyState = (Integer.parseInt(msg.split(",")[2])==1);
							//dom.getPlayer(playerID).setReadyState(readyState);
							break;
							
						case "END":
							/*
							close now gamePanel frame
							show resultPanel
							*/
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
		};
	}
	

}


