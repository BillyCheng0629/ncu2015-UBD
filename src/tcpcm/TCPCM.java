

package tcpcm;


import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
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
import uim.ResultDialog;

public class TCPCM {
	private int clientNo;
	private String serverIP;
	private int serverPort = 9876;
	private Socket socket;
	private Thread recieveThread;
	private BufferedReader in;
	private MainFrame frame;
	ResultDialog resultDialog;
	

	
	
	
	public TCPCM(MainFrame frame) {
		this.frame = frame;
		
		
	}
	
	
	
	public Boolean connectServer(String ip) {
		serverIP = ip;
		
		

		try {
			//Socket socket = new Socket();
			//socket.connect(new InetSocketAddress(ip, serverPort), 1000);
			
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
				Thread.sleep(100);
				out.println("PLACE,");
				out.flush();

			default:
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
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
						String[] msgArray = msg.split(",");
						
						
						
						switch (msgArray[0]) {
						case "INITFRAME":
							playerID = Integer.parseInt(msgArray[1]);
							frame.player.setID(playerID);
							frame.dom.updatePlayer(frame.player);
							frame.dom.setClientPlayerID(playerID);
							frame.roomPanel.initLocal();
							break;
						case "ADDPLAYER":
							String playerName = msgArray[1];
							int characterNum = Integer.parseInt(msgArray[2]);
							boolean isReady = (Integer.parseInt(msgArray[3])==1);
							Player player = new Player(playerName);
							playerID = Integer.parseInt(msgArray[4]);
							player.setID(playerID);
							player.getCharacter().setCharacterNum(characterNum);
							player.setIsReady(isReady);
							player.setDirection(3);
							player.setIsMoving(false);
							player.setAlive(true);
							
							System.out.println("player"+playerID+": "+playerName);
							
							frame.dom.updatePlayer(player);
							
							System.out.println("receieve add player "+playerID);
							frame.roomPanel.updateRoomInfo();
							break;
						case "REMOVEPLAYER":
							frame.dom.removePlayer(Integer.parseInt(msgArray[1]));
							frame.roomPanel.updateRoomInfo();
							break;
						case "SETMAP":
							int mapType = Integer.parseInt(msgArray[1]);
							
							frame.dom.setMapType(mapType);
							frame.roomPanel.updateRoomInfo();
							break;
							
						case "SETCHARACTER":
							characterNum = Integer.parseInt(msgArray[1]);
							playerID = Integer.parseInt(msgArray[2]);
							
							
							frame.dom.getPlayer(playerID).getCharacter().setCharacterNum(characterNum);
							frame.roomPanel.updateRoomInfo();
							break;
						case "SETISREADY":
							playerID = Integer.parseInt(msgArray[2]);
							isReady = (Integer.parseInt(msgArray[1])==1);
							frame.dom.getPlayer(playerID).setIsReady(isReady);
							frame.roomPanel.updateRoomInfo();
							break;
						
						case "START":
							frame.udpus = new UDPUS(frame.dom);
							frame.udpus.initUDPServer();
							frame.getIntoGame();
							break;
							
						case "END":
							endGame();
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
	
	public void endGame() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.udpus.receiveThread.interrupt();
		frame.udpus.closeUDPUS();
		/*
		frame.timer.cancel();
		frame.keyTimer.cancel();
		*/
		resultDialog = new ResultDialog(frame);
		resultDialog.setTitle("Game Result");
		resultDialog.setVisible(true);
	}
	

}


