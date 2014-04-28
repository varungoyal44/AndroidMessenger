package com.vg.am;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidMessenger extends Activity 
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		
		myConsole.tvOut = new TextView(this);
		setContentView(myConsole.tvOut);
		
		
		TCPClient client = new TCPClient("129.21.67.60", 7045);
		Thread cThread = new Thread(client);
		cThread.start();
		
	}
}
