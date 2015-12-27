package udpus;

import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

import dom.DOM;
import gameobject.Item;
import gameobject.Player;

public class UDPUS {
	private int BUFFERSIZE;
	private DatagramSocket serverSocket;
	public DatagramPacket receivePacket;
	private String message;
	private String[] messageSplit;
	private DOM dom;
	private Timer t;
	private TimerTask task;
	
	public UDPUS(DOM _DOM) throws SocketException{
		this.serverSocket = new DatagramSocket(9876);
		this.BUFFERSIZE = 256;
		this.receivePacket = new DatagramPacket(new byte[BUFFERSIZE],BUFFERSIZE);
		this.message = "";
		this.task = null;
		this.t = null;
		this.messageSplit = null;
		
		this.dom = _DOM;
	}
	
	public void initUDPServer() throws IOException{
		t = new Timer();
		task = new TimerTask(){
			@Override
			public void run() {
				String classify = "";
				try {
					serverSocket.receive(receivePacket);
					message = new String(receivePacket.getData(), 0, receivePacket.getLength());
					assert(message.length()>=0);
					
					messageSplit = message.split(" ");
			        assert(messageSplit.length>=2);
			        
			        classify = messageSplit[0]+" "+messageSplit[1];
			        switch(classify){
			        	case "UPDATE PLAYER":
			        		// message = "UPDATE PLAYER ID name character alive location.x location.y direction \
			        		//            power maxCurrentDumplingCount currentDumplingCount "
			        		// example : "UPDATE PLAYER 3 name2 character TRUE 20 30 2 5 6 3
			        		int playerID = Integer.parseInt(messageSplit[2]);
			        		Player player = dom.getPlayer(playerID);
			        		setPlayerGamingInfo(player, messageSplit[5], messageSplit[6], messageSplit[7],
			        				messageSplit[8], messageSplit[9], messageSplit[10], messageSplit[11]);
			        		break;
			        	case "UPDATE ITEM":
			        		// message = "UPDATE ITEM ID TYPE location.x location.y"
			        		// example : "UPDATE ITEM 2  3    20         50
			        		int itemID = Integer.parseInt(messageSplit[2]);
			        		Item item = dom.getItem(itemID);
			        		setItemGamingInfo(item, messageSplit[2], messageSplit[3], messageSplit[4], messageSplit[5]);
			        		break;
			        	case "UPDATE TIME":
			        		// message = "UPDATE TIME time" ( time = seconds)
			        		// example = "UPDATE TIME 90"
			        		int intTime = Integer.parseInt(messageSplit[2]);
			        		String gameTime = intTime / 60 + ":" + intTime % 60;
			        		dom.setGameTime(gameTime);
			        		break;
			        	default:
			        		break;
			        }
			        
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.scheduleAtFixedRate(task, 0, 10);
	}
	
	public void setPlayerGamingInfo(Player player, String alive, String locX, String locY, String dir, 
			String power, String maxCurrentDumplingCount, String currentDumplingCount){
		player.setAlive(Boolean.parseBoolean(alive));
		player.location.x = Integer.parseInt(locX);
		player.location.y = Integer.parseInt(locY);
		player.setDirection(Integer.parseInt(dir));
		player.setPower(Integer.parseInt(power));
		player.setMaxDumplingCount(Integer.parseInt(maxCurrentDumplingCount));
		player.setCurrentDumplingCount(Integer.parseInt(currentDumplingCount));
	}
	
	public void setItemGamingInfo(Item item, String ID, String type, String locX, String locY){
		item.setID(Integer.parseInt(ID));
		item.setType(Integer.parseInt(type));
		item.location.x = Integer.parseInt(locX);
		item.location.y = Integer.parseInt(locY);
	}
	
	public void closeUDPUS(){
		this.serverSocket.close();
	}
}