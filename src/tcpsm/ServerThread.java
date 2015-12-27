package tcpsm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
	
	private Socket fd;
	private PrintWriter out;
	private BufferedReader in;
	private String inputLine;
	
	public ServerThread(Socket fd) {
		this.fd = fd;
	}

	@Override
	public void run() {

		try {
			assert fd == null:"class ServerThread.run(): fd is null";
			//out = new PrintWriter(fd.getOutputStream(), true); 
			in = new BufferedReader( new InputStreamReader( fd.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				switch (inputLine) {
				case "MOVE":
					break;
				case "PLACE":
					break;
				case "SETNAME":
					break;
				case "SETMAP":
					break;
				case "SETCHARACTER":
					break;
				case "ISREADY":
					break;
				case "START":
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
