

package tcpcm;


import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.print.event.PrintJobAdapter;
import javax.security.auth.SubjectDomainCombiner;
import javax.swing.JFrame;


import gameobject.Player;
import scenere.CharacterMoveListener;
import scenere.KeyActionPerformer;
import scenere.SceneData;
import scenere.ScenePanel;
import udpus.UDPUS;
import uim.MainFrame;

public class TCPCM {
	private int clientNo;
	private String serverIP;
	private int serverPort = 9876;
	private Socket socket;
	private Thread recieveThread;
	private BufferedReader in;
	private MainFrame frame;
	
	

	
	
	
	public TCPCM(MainFrame frame) {
		this.frame = frame;
		
		
	}
	
	
	
	public Boolean connectServer(String ip) {
		serverIP = ip;
		
		

		try {
			socket = new Socket(ip, serverPort);
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
	
	public void startRecieveMessage() {
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
						case "SETFRAMEID":
							playerID = Integer.parseInt(msg.split(",")[1]);
							frame.player.setID(playerID);
							frame.dom.updatePlayer(frame.player);
							break;
						case "ADDPLAYER":
							String playerName = msg.split(",")[1];
							int characterNum = Integer.parseInt(msg.split(",")[2]);
							boolean isReady = (Integer.parseInt(msg.split(",")[3])==1)?true:false;
							Player player = new Player(playerName);
							playerID = Integer.parseInt(msg.split(",")[4]);
							player.setID(playerID);
							player.getCharacter().setCharacterNum(characterNum);
							player.getCharacter().setCharacterNum(1);
							player.setIsReady(isReady);
							player.setDirection(3);
							player.setIsMoving(false);
							player.setAlive(true);
							
							System.out.println("player"+playerID+": "+playerName);
							
							frame.dom.updatePlayer(player);
							
							System.out.println("receieve add player "+playerID);
							frame.roomPanel.updateRoomInfo();
							break;
						case "SETMAP":
							int mapType = Integer.parseInt(msg.split(",")[1]);
							
							frame.dom.setMapType(mapType);
							frame.roomPanel.updateRoomInfo();
							break;
							
						case "SETCHARACTER":
							
							playerID = Integer.parseInt(msg.split(",")[1]);
							characterNum = Integer.parseInt(msg.split(",")[2]);
							
							frame.dom.getPlayer(playerID).getCharacter().setCharacterNum(characterNum);;
							break;
						case "SETISREADY":
							playerID = Integer.parseInt(msg.split(",")[2]);
							isReady = (Integer.parseInt(msg.split(",")[1])==1)?true:false;
							frame.dom.getPlayer(playerID).setIsReady(isReady);
							frame.roomPanel.updateRoomInfo();
							break;
						
						case "START":
							frame.udpus = new UDPUS(frame.dom);
							frame.udpus.initUDPServer();
							frame.getIntoGame();
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
						System.out.println("client recieveThread error");
						e.printStackTrace();
					}
				}
	        }
		};
		
		recieveThread.start();
	}
	

}


