package com.vg.am;


import java.io.IOException;
import java.net.Socket;


public class TCPClient implements Runnable 
{

	String serverAddress;
	int serverPort;
	Socket serverSocket = null;

	TCPClient(String serverAddress, int serverPort)
	{
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;

		myConsole.tvOut.append("\n"+"Welcome to Android Messenger");
		myConsole.tvOut.append("\n"+"----------------------------");

		myConsole.tvOut.append("\n"+"This is the console view of the simple Client Server chat program to understand the Android APIs");
		myConsole.tvOut.append("\n"+"------------------------------------------------------------------------------------------------");
	}

	public void run()
	{
		try 
		{
			//while(true)
			{
				myConsole.tvOut.append("\n"+"Connecting To Server " + serverAddress + ":" + serverPort);
				serverSocket = new Socket(serverAddress, serverPort);
				
				new Thread(new Sender(serverSocket)).start();
				new Thread(new Receiver(serverSocket)).start();
			}
		} 
		catch (Exception e) 
		{
			myConsole.tvOut.append("\nTCP C: Error" + e);
			try {
				serverSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}