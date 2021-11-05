package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Capitalizer implements Runnable{
	
	private PrintWriter socketOut;
	private BufferedReader socketIn;

	
	public Capitalizer (Socket socket) {

		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	
	private void capitalize () {
		String line = null;
		// keep listening to the client
		while (true) {
			try {
				line = socketIn.readLine();
				line = line.toUpperCase();
				socketOut.println(line);
				socketOut.flush(); // must flush so client knows when to stop reading
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		capitalize();
		
	}
	
	

}
