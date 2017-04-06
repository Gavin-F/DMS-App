package com.example.gavin.module2;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Gavin on 2017-04-04.
 */

public class ReadMessageDataAsync extends AsyncTask<String,Integer,String>{

    TextView current_message;

    public ReadMessageDataAsync (TextView t) {
        //t.setText("");
        this.current_message = t;
    }

    @Override
    protected String doInBackground(String... commands ) {
        String s = "";
        while(true) {
            s = bluetooth_activity.ReadFromBTDevice();
            if(!s.equalsIgnoreCase("")) break;
            try {
                Thread.sleep(1000);
                Log.d("message async","Sleep");
            }
            catch (InterruptedException e) {
                Log.d("message async","Failed to Sleep");
            }
        }
        return s;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("result_preset_msg",result);
        current_message.setText(result);
    }
}
