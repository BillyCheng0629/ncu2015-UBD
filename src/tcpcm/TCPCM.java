package tcpcm;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class TCPCM {

	public void inputAction(int i) {
		// TODO Auto-generated method stub
		
	}
	
	boolean connectServer(InetAddress serverip){
		try {
			client=new Socket(serverip.getHostAddress(), port);
		} catch (SocketTimeoutException e) {
	        client=null;
			e.printStackTrace();
			return false;
	    } catch (UnknownHostException e) {
			client=null;
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			client=null;
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	void inputMoves(int MoveCode){
		switch (MoveCode) {
		case 1:
			break;
		}
	}
	
	void sendRoomAction(String msg){
		
	}
	
	void recieveMessage(String msg){
		
	}
}
