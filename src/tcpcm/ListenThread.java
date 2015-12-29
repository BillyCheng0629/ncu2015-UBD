package tcpcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListenThread implements Runnable{
	private Socket fd; 
	private BufferedReader in;
	private String inputLine;
	
	public ListenThread(Socket client) {
		this.fd = client; 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				assert fd == null:"class ServerThread.run(): fd is null";
				in = new BufferedReader( new InputStreamReader( fd.getInputStream()));
				while ((inputLine = in.readLine()) != null) {
					switch (inputLine) {
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
	

}
