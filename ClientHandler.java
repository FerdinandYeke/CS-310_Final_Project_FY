import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 *
 *Program source: https://www.youtube.com/watch?v=gLfuZrrfKes&t=1461s&ab_channel=WittCode
 */
public class ClientHandler implements Runnable {

	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
	private Socket socket;
	private BufferedReader br;
	private BufferedWriter bw;
	private String clientUsername;
	private String string;
	private String username;
	
	
	public ClientHandler (Socket socket)
	{
		try 
		{
			this.socket = socket;
			this.bw = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
			this.br = new BufferedReader ( new InputStreamReader(socket.getInputStream()));
			this.clientUsername = br.readLine();
			clientHandlers.add(this);
			broadcastMessage("SERVER: "+clientUsername+ "has entered the game.");
		}
		
		catch(IOException e)
		{
			closeEverything(socket,br, bw);
			System.out.println(e);
		}
	}
	
	private void broadcastMessage(String string) {
		// TODO Auto-generated method stub
		this.string = string;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		String messageFromClient;
		while (socket.isConnected())
		{
			try 
			{
				messageFromClient = br.readLine();
				broadcastMessage(messageFromClient);
			}
			
			catch(IOException e)
			{
				closeEverything(socket,br,bw);
				break;
			}
		}
	}

	public void broadcaseMessage(String messageToSend)
	{
		for(ClientHandler clientHandler : clientHandlers)
		{
			try 
			{
				if(!clientHandler.clientUsername.equals(clientUsername))
				{
					clientHandler.bw.write(messageToSend);
					clientHandler.bw.newLine();
					clientHandler.bw.flush();
				}
			}
			
			catch(IOException e)
			{
				System.out.println(e);
				closeEverything(socket,br,bw);
			}
		}
	}
	
	public void removeClientHandler()
	{
		clientHandlers.remove(this);
		broadcastMessage("SERVER: "+clientUsername+"has left the game.");
	}
	
	
	private void closeEverything(Socket socket, BufferedReader br, BufferedWriter bw)
	{
		removeClientHandler();
		try 
		{
			if(br != null)
			{
				br.close();
			}
			
			if(bw != null)
			{
				bw.close();
			}
			
			if(socket != null)
			{
				socket.close();
			}
		}
		
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}
	
	
