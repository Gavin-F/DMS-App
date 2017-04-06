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

public class ChangePinPresets extends AppCompatActivity {
    private TextView entered_pin, wrong_pin;
    private String display_entered_pin = "";
    private String display_wrong_pin = "";
    private String pin;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pin_presets);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            number = extras.getString("pin number");
            pin = extras.getString("pin");
        }
        entered_pin = (TextView)findViewById(R.id.old_pin_input);
        wrong_pin = (TextView)findViewById(R.id.wrong_password);
    }

    private void updatePin() {
        entered_pin.setText(display_entered_pin);
        wrong_pin.setText(display_wrong_pin);
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
        display_wrong_pin = "";
        updatePin();
    }

    public void onClickEnter(View v) {
        Button b = (Button) v;
        if (display_entered_pin.equalsIgnoreCase(pin)) {
            // pin is correct
            Intent myIntent = new Intent(ChangePinPresets.this, NewPinPresets.class);
            myIntent.putExtra("pin number", number);
            Log.d("number", number);
            startActivity(myIntent);
        }
        else {
            Log.d("wrong pin", display_entered_pin);
            display_wrong_pin = "Incorrect Pin";
            updatePin();
        }
    }
}
