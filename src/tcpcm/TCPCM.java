

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
				out.println("SETISMOVEING,"+0);
				break;
			case 1:
			case 2:
			case 3:
			case 4:
				
				out.println("MOVE,"+actionCode);
				out.flush();
				
				out.println("SETISMOVEING,"+1);
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
		/**
		 * ADDPLAYER
		 * SETNAME
		 * SETMAP
		 * SETCHARACTER
		 * SETISREADY
		 * START
		 */
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
							String playName = msg.split(",")[1];
							Player player = new Player(playName);
							playerID = Integer.parseInt(msg.split(",")[2]);
							player.setID(playerID);
							dom.updatePlayer(player);
							break;
						case "SETMAP":
							int mapType = Integer.parseInt(msg.split(",")[1]);
							dom.setMapType(mapType);
							break;
							
						case "SETCHARACTER":
							
							playerID = Integer.parseInt(msg.split(",")[1]);
							int characterNum = Integer.parseInt(msg.split(",")[2]);
							
							dom.getPlayer(playerID).getCharacter().setCharacterNum(characterNum);;
							break;
						case "SETISREADY":
							playerID = Integer.parseInt(msg.split(",")[1]);
							boolean isReady = (Integer.parseInt(msg.split(",")[2])==1);
							dom.getPlayer(playerID).setIsReady(isReady);
							break;
							
						case "END":
							/**
							 * 
							close now gamePanel frame
							show resultPanel
							frm.frm.
							*
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


