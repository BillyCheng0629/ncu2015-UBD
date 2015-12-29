package tcpsm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import cdc.CDC;

class ServerThread implements Runnable{
	
	private Socket fd;
	private PrintWriter out;
	private String inputLine;
	private CDC cdc;
	
	public ServerThread(Socket fd, CDC cdc) {
		this.fd = fd;
		this.cdc = cdc;
	}

	@Override
	public void run() {
		BufferedReader in; 
		while (true) {
			try {
				assert fd == null:"class ServerThread.run(): fd is null";
				in = new BufferedReader(new InputStreamReader(fd.getInputStream()));
				String msg = in.readLine();
				String token[] =  msg.split(",");
					switch (token[0]) {
					case "MOVE":
						// TODO how to get player id
						cdc.updateDirection(123, Integer.parseInt(token[1]));
						break;
					case "PLACE":
						break;
					case "SETNAME":
						// TODO how to get player id
						cdc.getPlayer(123).setName(token[1]);
						break;
					case "SETMAP":
						
					case "SETCHARACTER":
					case "ISREADY":
					case "START":
						
						break;
					}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

}
