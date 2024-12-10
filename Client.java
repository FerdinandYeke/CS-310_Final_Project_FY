import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 * @author Ferdinand Yeke
 *CS-310 Operating Systems
 *Bellarmine University
 *
 *Program source: https://www.youtube.com/watch?v=gLfuZrrfKes&t=1461s&ab_channel=WittCode
 */
public class Client {
	
	private Socket socket;
	private BufferedReader br;
	private BufferedWriter bw;
	private String username;
	
	public Client(Socket socket, String username)
	{
		try {
			this.socket = socket;
			this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.br =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.username = username;
		}
		
		catch(IOException e)
		{
			closeEverything(socket, br, bw);
		}
	}
	
	public void sendMessage()
	{
		try 
		{
			bw.write(username);
			bw.newLine();
			bw.flush();
			
			Scanner scan = new Scanner(System.in);
			while (socket.isConnected())
			{
				String messageToSend = scan.nextLine();
				bw.write(username+":"+messageToSend);
				bw.newLine();
				bw.flush();
			}
		}
		
		catch(IOException e)
		{
			closeEverything(socket, br, bw);
		}
	}
	
	public void listenForMessage()
	{
		new Thread (new Runnable() 
		{
			@Override
			public void run() 
			{
				String msgFromPlayers;
				
				while(socket.isConnected())
				{
					try
					{
						msgFromPlayers = br.readLine();
						System.out.println(msgFromPlayers);
					}
					catch(IOException e)
					{
						closeEverything(socket, br, bw);
					}
				}
			}
		}).start();
		
	}

	public void closeEverything(Socket socket, BufferedReader br, BufferedWriter bw)
	{
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
	
	
	public static void main (String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your username for the game: ");
		String username = scan.nextLine();
		Socket socket = new Socket("localhost",1234);
		Client client = new Client(socket, username);
		client.listenForMessage();
		client.sendMessage();
	}
	
}

