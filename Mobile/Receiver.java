package com.vg.am;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Receiver implements Runnable
{
	private Socket server;
	private BufferedReader in = null;
	private String name = null;

	public Receiver (Socket server)
	{
		this.server = server;
		try 
		{
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));

			// To receive server's name.
			name = in.readLine();
		}
		catch (IOException e1) 
		{
			myConsole.tvOut.append("ERROR: IOException while getting the name...");
		}
	}

	public void run() 
	{
		String msg;
		try
		{
			synchronized (myConsole.tvOut)
			{
				while((msg = in.readLine()) != null)
				{
					myConsole.tvOut.append("\n" + name + ": " + msg);
				}
			}
		}
		catch (IOException e) 
		{
			myConsole.tvOut.append("ERROR: IOException while getting the data...");
			try {
				server.close();
			} catch (IOException e1) {
				myConsole.tvOut.append("ERROR: IOException while closing the socket");
			}
		}
	}
}
