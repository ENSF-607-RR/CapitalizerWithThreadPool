package Client;

import Server.Capitalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCapitalizer {
	
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private CapitalizerView capView;
	
	public ClientCapitalizer (String serverName, int portNumber, CapitalizerView theView) {

		capView = theView;
		try {
			aSocket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader (new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter (aSocket.getOutputStream(), true);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void communicate () {

		capView.addCapitalizeButton(e -> {
			String line = "";
			String response = "";
			try {
				line = capView.getInputField();
				socketOut.println(line);
				response = socketIn.readLine();
				capView.setOutputField(response);
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	// this could be in a different file
	public static void main (String [] args)  throws IOException {
		CapitalizerView theView = new CapitalizerView();
		ClientCapitalizer myClient = new ClientCapitalizer ("localhost", 9898, theView);
		myClient.communicate();
		theView.setVisible(true);

	}

}
