package tcpcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


public class TCPCM {
	Socket client; 
	ListenThread listen;
	PrintStream out;
	final int port = 1234;
	
	TCPCM(){}
	
	/**
	 * called by main program of a client computer to initialize the connection with server
	 * this function should use the serverIP and the port to connect the server
	 * if the connection succeed, the function return true 
	 * it return false if the connection is failed

	@param serverip the IP address to connect
	@return         connection succeed or failed
	 */
	public boolean connectServer(InetAddress serverip) {
		try {
			client = new Socket(serverip.getHostAddress(), port);
			out = new PrintStream(client.getOutputStream());
			listen = new ListenThread(client);
		} catch (Exception e) {
	        client = null;
			e.printStackTrace();
			return false;
	    }
		return true;
	}
	
	/**
	 * called by UIM
	 * recall that in this design, the client machine is treated as a input
	 * processing machine. UIM processes input from keyboard and mouse
	 * and then translate the event into a actionCode and pass to TCP client
	 * Currently, you only need to implement the following actionCode:
	 *  0   - (passed by UIM) the main character stop
	 *  1   - (passed by UIM) the main character turn north
	 *  2   - (passed by UIM) the main character turn east
	 *  3   - (passed by UIM) the main character turn west
	 *  4   - (passed by UIM) the main character turn south
	 *  5   - (passed by UIM) the main character place dumpling
	 *  Once TCP client module receives a actionCode, it wraps it into a message 
	 *  and transmit to TCP server module via the established connection

	 */
	public void inputAction(int actionCode) {
		switch (actionCode) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			out.println("MOVE,"+actionCode);
			out.flush();
			break;
		}
	}
	
	/**
	 * call by UIM
	 * send player action in room  to sever
	 * 
	 * @param  msg send message to server
	 */
	public void sendRoomAction(String msg) {
		try {
			assert client == null:"class ServerThread.run(): fd is null";           
			PrintWriter out = new PrintWriter(client.getOutputStream(), true); 
			out.write(msg);

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public void recieveMessage(String msg) {
	}
}
