package udpus;

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

import dom.DOM;
import gameobject.Dumpling;
import gameobject.Item;
import gameobject.Player;

public class UDPUS {
	private int BUFFERSIZE;
	private DatagramSocket serverSocket;
	public DatagramPacket receivePacket;
	private String message;
	public Thread receiveThread;
	private String[] messageSplit;
	private DOM dom;
	private Timer t;
	private TimerTask task;
	
	public UDPUS(DOM _DOM) throws SocketException{
		this.serverSocket = new DatagramSocket(7654);
		this.BUFFERSIZE = 256;
		this.receivePacket = new DatagramPacket(new byte[BUFFERSIZE],BUFFERSIZE);
		this.message = "";
		this.task = null;
		this.t = null;
		this.messageSplit = null;
		
		this.dom = _DOM;
	}
	
	public void initUDPServer() throws IOException{
		receiveThread = new Thread() {
			public void run() {
				while(true){
					String classify = "";
					try {
						serverSocket.receive(receivePacket);
						message = new String(receivePacket.getData(), 0, receivePacket.getLength());
						//System.out.println(message);
						assert(message.length()>=0);
						
						messageSplit = message.split(" ");
				        assert(messageSplit.length>=2);
				        
				        classify = messageSplit[0]+" "+messageSplit[1];
				        switch(classify){
				        	case "UPDATE PLAYER":
				        		// message = "UPDATE PLAYER ID name character alive location.x location.y isMoving direction \
				        		//            power maxCurrentDumplingCount currentDumplingCount "
				        		// example : "UPDATE PLAYER 3 name2 character TRUE 20 30 FALSE 2 5 6 3
				        		int playerID = Integer.parseInt(messageSplit[2]);
				        		Player player = dom.getPlayer(playerID);
				        		setPlayerGamingInfo(player, messageSplit[5], messageSplit[6], messageSplit[7],
				        				messageSplit[8], messageSplit[9], messageSplit[10], messageSplit[11],messageSplit[12]);
				        		break;
				        		
				        	case "UPDATE DUMPLING":
				        		// message = "UPDATE DUMPLING ID location.x location.y power"
				        		// example : "UPDATE DUMPLING  2 50         100        3
				        		Dumpling dumpling = setDumplingGamingInfo(messageSplit);
				        		dom.addDumpling(dumpling);
				        		break;
				        		
				        	case "UPDATE ITEM":
				        		// message = "UPDATE ITEM ID TYPE location.x location.y"
				        		// example : "UPDATE ITEM 2  3    20         50
				        		Item item = setItemGamingInfo(messageSplit);
				        		dom.addItem(item);
				        		break;
				        		
				        	case "UPDATE TIME":
				        		// message = "UPDATE TIME time" ( time = ms)
				        		// example = "UPDATE TIME 90000"
				        		int intTime = Integer.parseInt(messageSplit[2]);
				        		String gameTime = intTime / 1000 / 60 + ":" + intTime / 1000 % 60;
				        		dom.setGameTime(gameTime);
				        		break;
				        		
				        	case "DELETE DUMPLING":
				        		//dom.removeDumpling(Integer.parseInt(messageSplit[2]));
				        		break;
				        		
				        	case "DELETE ITEM":
				        		dom.removeItem(Integer.parseInt(messageSplit[2]));
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
		receiveThread.start();
	}
	
	public void setPlayerGamingInfo(Player player, String alive, String locX, String locY, String isMoving, String dir, 
			String power, String maxCurrentDumplingCount, String currentDumplingCount){
		player.setAlive(Boolean.parseBoolean(alive));
		player.setIsMoving(Boolean.parseBoolean(isMoving));
		player.location.x = Integer.parseInt(locX);
		player.location.y = Integer.parseInt(locY);
		player.setDirection(Integer.parseInt(dir));
		player.setPower(Integer.parseInt(power));
		player.setMaxDumplingCount(Integer.parseInt(maxCurrentDumplingCount));
		player.setCurrentDumplingCount(Integer.parseInt(currentDumplingCount));
	}
	
	public Item setItemGamingInfo(String[] info){
		Item item = new Item();
		item.setID(Integer.parseInt(info[2]));
		item.setType(Integer.parseInt(info[3]));
		item.location.x = Integer.parseInt(info[4]);
		item.location.y = Integer.parseInt(info[5]);
		return item;
	}
	
	public Dumpling setDumplingGamingInfo(String[] info){
		Dumpling dumpling = new Dumpling();
		dumpling.setID(Integer.parseInt(info[2]));
		dumpling.location.x = Integer.parseInt(info[3]);
		dumpling.location.y = Integer.parseInt(info[4]);
		dumpling.setPower(Integer.parseInt(info[5]));
		return dumpling;
	}
	
	public void closeUDPUS(){
		this.serverSocket.close();
	}
}

