package com.example.gavin.module2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gavin on 2017-03-15.
 */

public class NewPinPresets extends AppCompatActivity {
    private TextView entered_pin, too_short;
    private String display_entered_pin = "";
    private String display_too_short = "";
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pin_presets);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            number = extras.getString("pin number");

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
        if (display_entered_pin.length() < 4){
            display_too_short = "Pin is too short";
            updatePin();
        }
        else {
            // change the pin and go back to menu
            Log.d("new pin", display_entered_pin);
            if (number.equalsIgnoreCase("1")) {
                bluetooth_activity.WriteToBTDeviceAsync("w"+display_entered_pin);
            }
            else if (number.equalsIgnoreCase("2")) {
                bluetooth_activity.WriteToBTDeviceAsync("x"+display_entered_pin);
            }
            else if (number.equalsIgnoreCase("3")) {
                bluetooth_activity.WriteToBTDeviceAsync("y"+display_entered_pin);
            }
            else if (number.equalsIgnoreCase("4")) {
                bluetooth_activity.WriteToBTDeviceAsync("z"+display_entered_pin);
            }
            Intent myIntent = new Intent(NewPinPresets.this, MainActivity.class);
            myIntent.putExtra("preset changed", "Pin");
            myIntent.putExtra("number", number);
            startActivity(myIntent);
        }
    }
}
