package com.example.gavin.module2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by Gavin on 2017-03-15.
 */

public class SchedulingPin extends AppCompatActivity {
    TimePicker timePicker;
    String key;
    String new_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduling_pin);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            key = extras.getString("key");
            new_pin = extras.getString("new pin");
            Log.d("new pin", new_pin);
        }
        timePicker = (TimePicker)findViewById(R.id.timePicker_pin);
    }

    public void onClickSetPinTime(View v) {
        Button b = (Button) v;
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

        // change the pin and go back to menu
        Intent myIntent = new Intent(SchedulingPin.this, MainActivity.class);
        myIntent.putExtra("which", "Pin");
        myIntent.putExtra("preset time", hour + ":" + minute_str);
        //bluetooth_activity.WriteToBTDeviceAsync();
        startActivity(myIntent);
    }

}
