package com.example.gavin.module2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.gavin.module2.R.id.text;

public class MainActivity extends AppCompatActivity {
    private TextView current_message;
    String pin = "1111";
    String new_pin = "";
    String message = "";
    String new_message = "";
    String display_current_message = "";
    int duration = Toast.LENGTH_SHORT;
    CharSequence toast_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        current_message = (TextView)findViewById(R.id.current_message);
        // GET DATA FROM DE1 FOR PIN AND MESSAGE AND LOCK STATE
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            new_pin = extras.getString("new pin");
            if (new_pin != null) {
                pin = new_pin;
                Log.d("new pin", message);
                toast_text = "Pin Changed";
                Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
                toast.show();
                // SEND PACKET TO CHANGE THE PIN
            }
            new_message = extras.getString("message");
            if (new_message != null) {
                message = new_message;
                Log.d("new message", message);
                toast_text = "Message Changed";
                Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
                toast.show();
                // SEND PACKET TO CHANGE THE MESSAGE
            }
        }
        Log.d("current message", message);
        current_message.setText(message);
    }

    public void onClickLock(View v) {
        Button b = (Button) v;
        if (b.getText().toString().equalsIgnoreCase("lock")) {
            b.setText("Unlock");
            toast_text = "Door Locked";
            Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
            toast.show();
            // SEND PACKET TO LOCK
            bluetooth_activity.WriteToBTDevice("(l)");
        }
        else if (b.getText().toString().equalsIgnoreCase("unlock")) {
            b.setText("Lock");
            toast_text = "Door Unlocked";
            Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
            toast.show();
            // SEND PACKET TO UNLOCK
            bluetooth_activity.WriteToBTDevice("(u)");
        }
    }

    public void onClickChangePin (View v) {
        Intent myIntent = new Intent(MainActivity.this, change_pin.class);
        myIntent.putExtra("pin", pin);
        startActivity(myIntent);
    }

    public void onClickChangeMessage (View v) {
        Intent myIntent = new Intent(MainActivity.this, change_message.class);
        startActivity(myIntent);
    }


}
