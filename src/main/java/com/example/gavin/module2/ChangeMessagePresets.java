package com.example.gavin.module2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Gavin on 2017-04-04.
 */

public class ChangeMessagePresets extends AppCompatActivity{
    private EditText message_input;
    private TextView current_preset_message;
    String message = "";
    String number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_message_presets);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            number = extras.getString("message number");
            Log.d("change number",number);
        }
        message_input = (EditText) findViewById(R.id.message_preset_input);
        current_preset_message = (TextView) findViewById(R.id.current_preset_message);

        if (number != null) {
            if (number.equalsIgnoreCase("1")) {
                bluetooth_activity.WriteToBTDeviceAsync("e");
            }
            else if (number.equalsIgnoreCase("2")) {
                bluetooth_activity.WriteToBTDeviceAsync("f");
            }
            else if (number.equalsIgnoreCase("3")) {
                bluetooth_activity.WriteToBTDeviceAsync("g");
            }
            else if (number.equalsIgnoreCase("4")) {
                bluetooth_activity.WriteToBTDeviceAsync("h");
            }
            //write asynchronous task to read data, and get current message to display
            new ReadMessageDataAsync(current_preset_message).execute();
        }
    }

    public void onClickEnterMsg(View v) {
        message = message_input.getText().toString();
        Log.d("message",message);
        if (number.equalsIgnoreCase("1")) {
            bluetooth_activity.WriteToBTDeviceAsync("n"+message);
        }
        else if (number.equalsIgnoreCase("2")) {
            bluetooth_activity.WriteToBTDeviceAsync("o"+message);
        }
        else if (number.equalsIgnoreCase("3")) {
            bluetooth_activity.WriteToBTDeviceAsync("q"+message);
        }
        else if (number.equalsIgnoreCase("4")) {
            bluetooth_activity.WriteToBTDeviceAsync("r"+message);
        }
        Intent myIntent = new Intent(ChangeMessagePresets.this, MainActivity.class);
        myIntent.putExtra("preset changed", "Message");
        myIntent.putExtra("number", number);
        startActivity(myIntent);
    }
}
