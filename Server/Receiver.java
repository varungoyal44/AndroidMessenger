import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver implements Runnable
{
	private Socket client;
	private BufferedReader in = null;
	private String name = null;

	public Receiver (Socket client)
	{
		this.client = client;
		try 
		{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			// To receive Client's name.
			name = in.readLine();
		}
		catch (IOException e1) 
		{
			System.err.println("ERROR: IOException while getting the name...");
		}
	}
	
	public void run() 
	{
		String msg;

		try
		{
			while((msg = in.readLine()) != null)
			{
				System.out.println("\n" + name + ": " + msg);
			}
		}
		catch (IOException e) 
		{
			//System.err.println("ERROR: IOException while getting the data...");
			try {
				System.out.println("Closing Connection...");
				client.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
