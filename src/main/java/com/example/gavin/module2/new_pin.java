package com.example.gavin.module2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gavin on 2017-03-15.
 */

public class new_pin extends AppCompatActivity {
    private TextView entered_pin, too_short;
    private String display_entered_pin = "";
    private String display_too_short = "";
    private String old_pin;
    private String key, which;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pin);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            old_pin = extras.getString("old pin");
            key = extras.getString("key");
            which = extras.getString("which");
        }
        entered_pin = (TextView)findViewById(R.id.new_pin_input);
        too_short = (TextView)findViewById(R.id.too_short);
    }

    private void updatePin() {
        entered_pin.setText(display_entered_pin);
        too_short.setText(display_too_short);
    }

    public void onClickNumber(View v) {
        Button b = (Button) v;
        if (display_entered_pin.length() < 4)
            display_entered_pin += b.getText();
        updatePin();
    }

    public void onClickDel(View v) {
        if (display_entered_pin.length() > 0)
            display_entered_pin = display_entered_pin.substring(0, display_entered_pin.length() - 1);
        display_too_short = "";
        updatePin();
    }

    public void onClickEnter(View v) {
        Button b = (Button) v;
        if (display_entered_pin.equalsIgnoreCase(old_pin)) {
            display_too_short = "Pin cannot be the same as old Pin";
            updatePin();
        }
        else if (display_entered_pin.length() < 4){
            display_too_short = "Pin is too short";
            updatePin();
        }
        else {
            if (key != null) {
                if (key.equalsIgnoreCase("pinC")) {
                    Intent myIntent = new Intent(new_pin.this, SchedulingPin.class);
                    myIntent.putExtra("key", key);
                    myIntent.putExtra("new pin", display_entered_pin); //Optional parameters
                    startActivity(myIntent);
                }
            }
            else if (which != null) {
                if (which.equalsIgnoreCase("bt")) {
                    Log.d("bluetooth_pin", display_entered_pin);
                    Intent myIntent = new Intent(new_pin.this,  MainActivity.class);
                    bluetooth_activity.WriteToBTDeviceAsync("b"+display_entered_pin);
                    String toast_text = "Bluetooth Pin Changed";
                    Toast toast = Toast.makeText(new_pin.this,toast_text, Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(myIntent);
                }
            }
            else {
                // change the pin and go back to menu
                Log.d("new pin", display_entered_pin);
                Intent myIntent = new Intent(new_pin.this, MainActivity.class);
                //myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                myIntent.putExtra("new pin", display_entered_pin); //Optional parameters
                startActivity(myIntent);
            }
        }
    }

}
