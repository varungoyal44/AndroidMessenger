import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressWarnings("unused")
public class Sender implements Runnable
{
	private Socket client;
	private PrintWriter out = null;
	private String name = null;

	public Sender (Socket client)
	{
		this.client = client;
		try 
		{
			out = new PrintWriter(client.getOutputStream(), true);
		}
		catch (IOException e1) 
		{
			System.err.println("ERROR: IOException while getting the name...");
		}
	}

	public void run() 
	{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String msg;

		// To send server name to client...
		String name = "Illuminati_Server";
		System.out.println("\nName sent: Illuminati_Server");
		out.println("Illuminati_Server");

		msg = "Hello";
		System.out.println("\n" + name + ": " + msg);
		out.println(msg);

		msg = "I am good";
		System.out.println("\n" + name + ": " + msg);
		out.println(msg);
	}
}
