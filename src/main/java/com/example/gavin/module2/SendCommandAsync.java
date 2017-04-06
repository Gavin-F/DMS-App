package com.example.gavin.module2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Andy on 2017-03-21.
 */

public class SendCommandAsync extends AsyncTask<String, Integer, Integer> {

	@Override
	protected Integer doInBackground(String... command_array){
		bluetooth_activity.WriteToBTDevice("(");

		while(true){
			String s = bluetooth_activity.ReadFromBTDevice();
			Log.i("USER_TAG", s);
			if(s.equalsIgnoreCase("(")) break;
			try{
				Log.i("USER_TAG", "SLEEPING");
				Thread.sleep(1000);
			} catch (InterruptedException e){
				//Toast.makeText(, "Socket Creation Failed", Toast.LENGTH_LONG).show();
				Log.i("USER_TAG", "FAILED TO SLEEP");
			}
		}
		Log.i("USER_TAG", command_array[0]);
		bluetooth_activity.WriteToBTDevice(command_array[0] + ")");
		return 0;
	}
}