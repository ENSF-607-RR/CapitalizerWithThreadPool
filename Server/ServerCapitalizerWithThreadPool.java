package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCapitalizerWithThreadPool {

	private Socket aSocket;
	private ServerSocket serverSocket;

	private ExecutorService pool;

	public ServerCapitalizerWithThreadPool() {

		try {
			serverSocket = new ServerSocket(9898);
			pool = Executors.newFixedThreadPool(2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void runServer() {

		try {
			// keep listening for client connections
			while (true) {
				aSocket = serverSocket.accept();
				Capitalizer cap = new Capitalizer(aSocket);
				pool.execute(cap);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.shutdown();

	}

	public static void main(String[] args) throws IOException {

		ServerCapitalizerWithThreadPool myServer = new ServerCapitalizerWithThreadPool();
		myServer.runServer();
	}

}
