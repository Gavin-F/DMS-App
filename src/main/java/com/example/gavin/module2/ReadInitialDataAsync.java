package com.example.gavin.module2;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gavin on 2017-04-04.
 */

public class ReadInitialDataAsync extends AsyncTask<String,Integer,String>{

    TextView current_message;
    TextView current_pin;
    TextView lock_status;
    TextView door_status;
    Button lock_unlock;

    public ReadInitialDataAsync (TextView m, TextView p, TextView l, TextView d, Button b) {
        this.current_message = m;
        this.current_pin = p;
        this.lock_status = l;
        this.door_status = d;
        this.lock_unlock = b;
    }

    @Override
    protected String doInBackground(String... commands ) {
        String s = "";
        while(true) {
            s = bluetooth_activity.ReadFromBTDevice();
            if(!s.equalsIgnoreCase("")) break;
            try {
                Thread.sleep(1000);
                Log.d("message async","Sleeping");
            }
            catch (InterruptedException e) {
                Log.d("message async","Failed to Sleep");
            }
        }
        return s;
    }

    @Override
    protected void onPostExecute(String result) {
        String[] Results = result.split(",");
//        int index = 0;
//        int array_index = 0;



//        int i;
//        for (i = 0; i < result.length(); i++) {
//            if (result.charAt(i) == '\0') {
//                Results[array_index] = result.substring(index, i - 1);
//                array_index++;
//                index = i + 1;
//            }
//        }
        Log.d("result",result);
        Log.d("result1",Results[0]);
        Log.d("result2",Results[1]);
        Log.d("result3",Results[2]);
        Log.d("result4",Results[3]);
        Log.d("result5",Results[4]);

        current_message.setText(Results[0]);

        if (Results[1].equalsIgnoreCase("0"))
            current_pin.setText("Custom Pin");
        else
            current_pin.setText("Preset Pin " + Results[1]);

        if (Results[2].equalsIgnoreCase("1")) {
            lock_status.setText("Locked");
            lock_unlock.setText("Unlock");
        }
        else {
            lock_status.setText("Unlocked");
            lock_unlock.setText("Lock");
        }

        if (Results[3].equalsIgnoreCase("1"))
            door_status.setText("Open");
        else
            door_status.setText("Closed");

        MainActivity.pin = Results[4];
    }
}
