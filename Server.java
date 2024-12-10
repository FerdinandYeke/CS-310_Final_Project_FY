import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 /**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 *
 *Program source: https://www.youtube.com/watch?v=gLfuZrrfKes&t=1461s&ab_channel=WittCode
 */
public class Server {
	
	private ServerSocket serverSocket;
	
	/*
	 * This Server constructor sets the serverSocket based on the paramater here.
	 */
	public Server(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}//Server constructor ends here.

	/*
	 * startServer method starts the server while the socket is not closed.
	 */
	public void startServer()
	{
		try {
			while(!serverSocket.isClosed())
			{
				Socket socket = serverSocket.accept();
				System.out.println("A new player has connected to to the game.");
				ClientHandler clientHandler = new ClientHandler(socket);
				
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		}
		
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	}//Start server ends here.
	
	/*
	 * The method closedServerSocket() closes the server socket.
	 */
	public void closedServerSocket()
	{
		try 
		{
			if(serverSocket != null)
			{
				serverSocket.close();
			}
		}
		
		catch(IOException e)
		{
			System.out.println(e);
		}
	}//closedServerSocket method ends here.
	
	
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(1234);
		Server server = new Server(serverSocket);
		server.startServer();
	}
}
