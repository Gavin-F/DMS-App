package com.example.gavin.module2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * Created by Gavin on 2017-04-04.
 */

public class Scheduling extends AppCompatActivity{
    private String key;
    private String new_message;
    private TimePicker timePicker;
    private Button change_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduling);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            key = extras.getString("key");
            Log.d("key",key);
        }
        timePicker = (TimePicker)findViewById(R.id.timePicker1);
        change_time = (Button)findViewById(R.id.set_time_button);
    }

    public void onClickSetTime(View v) {
        int hour;
        int minute;
        if (Build.VERSION.SDK_INT >= 23 ) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }
        else {
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }
        int hour_utc = (hour + 7) % 24;
        String hour_str = Integer.toString(hour_utc);
        if (hour_str.length() == 1)
            hour_str = '0' + hour_str;
        String minute_str = Integer.toString(minute);
        if (minute_str.length() == 1) {
            minute_str = '0' + minute_str;
        }
        String time = hour_str + minute_str;
        Log.d("time", time);

        Intent myIntent = new Intent(Scheduling.this, MainActivity.class);

        String which = "";
        if (key.equalsIgnoreCase("pin1")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "Pin";
        }
        else if (key.equalsIgnoreCase("pin2")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "Pin";
        }
        else if (key.equalsIgnoreCase("pin3")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "Pin";
        }
        else if (key.equalsIgnoreCase("pin4")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "Pin";
        }
        else if (key.equalsIgnoreCase("message1")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "Message";
        }
        else if (key.equalsIgnoreCase("message2")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "Message";
        }
        else if (key.equalsIgnoreCase("message3")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "Message";
        }
        else if (key.equalsIgnoreCase("message4")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "Message";
        }
        else if (key.equalsIgnoreCase("lock")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "lock";
        }
        else if (key.equalsIgnoreCase("unlock")) {
            //bluetooth_activity.WriteToBTDeviceAsync("");
            which = "unlock";
        }
        myIntent.putExtra("which", which);
        myIntent.putExtra("preset time", hour + ":" + minute_str);
        startActivity(myIntent);
    }



}
