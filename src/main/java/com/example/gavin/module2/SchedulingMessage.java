package com.example.gavin.module2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by Gavin on 2017-04-05.
 */

public class SchedulingMessage extends AppCompatActivity {
    private EditText message_input;
    String message = "";
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduling_message);
        message_input = (EditText) findViewById(R.id.message_input_time);
        timePicker = (TimePicker)findViewById(R.id.timePicker_message);
    }

    public void onClickEnterMsgTime(View v) {
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

        message = message_input.getText().toString();
        Intent myIntent = new Intent(SchedulingMessage.this, MainActivity.class);
        myIntent.putExtra("which", "Message");
        myIntent.putExtra("preset time", hour + ":" + minute_str);
        Log.d("message",message);
        bluetooth_activity.WriteToBTDeviceAsync("i0"+time+message);
        startActivity(myIntent);
    }
}
