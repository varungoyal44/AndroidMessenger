package com.vg.am;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressWarnings("unused")
public class Sender implements Runnable
{
	private Socket server;
	private PrintWriter outToServer = null;
	private String name = null;

	public Sender (Socket server)
	{
		this.server = server;
		try 
		{
			outToServer = new PrintWriter(server.getOutputStream(), true);
		}
		catch (IOException e1) 
		{
			myConsole.tvOut.append("ERROR: IOException while getting the name...");
		}
	}

	public void run() 
	{
		synchronized(myConsole.tvOut)
		{
			String message;

			String name = "Android_Client";
			myConsole.tvOut.append("\nSending Client Name: " + name);
			//myConsole.println("\nSending Client Name: " + name);
			outToServer.println(name);

			message = "Hello from android Client";
			myConsole.tvOut.append("\n" + name + ": " + message);
			//myConsole.println("\n" + name + ": " + message);
			outToServer.println(message);

			message = "How you doin??";
			myConsole.tvOut.append("\n" + name + ": " + message);
			//myConsole.println("\n" + name + ": " + message);
			outToServer.println(message);
		}

		//myConsole.println("\n\nDone...");
	}
}
