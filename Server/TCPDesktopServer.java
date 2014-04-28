import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPDesktopServer implements Runnable
{

	public String serverIP;
	public int serverPort;
	Socket client = null;

	TCPDesktopServer()
	{
		String add;
		try 
		{
			add = InetAddress.getLocalHost().toString();
			String [] serverAddress = add.split("/");
			serverIP = serverAddress[1];
			serverPort = 7045;
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
	}

	public void run() 
	{
		try 
		{
			System.out.println("Server Adress: "+ serverIP + "\nPort: "+ serverPort);
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("Server online...");

			while (true) 
			{      	 
				client = serverSocket.accept();
				new Thread(new Receiver(client)).start();
				new Thread(new Sender(client)).start();
			}       
		} 
		catch (IOException e) 
		{
			System.err.println("ERROR: IOException: client could not be accepted.");
		}
	}

	public static void main (String a[]) 
	{
		Thread desktopServerThread = new Thread(new TCPDesktopServer());
		desktopServerThread.start();
	}
}
