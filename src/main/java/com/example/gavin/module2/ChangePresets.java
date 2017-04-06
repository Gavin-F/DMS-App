package com.example.gavin.module2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Gavin on 2017-04-04.
 */

public class ChangePresets extends AppCompatActivity {
    String pin = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_presets);
        Bundle extras = getIntent().getExtras();
        pin = extras.getString("pin");
    }

    public void onClickChangeMessagePreset(View v) {
        Button b = (Button) v;
        Intent myIntent = new Intent(ChangePresets.this, ChangeMessagePresets.class);
        myIntent.putExtra("message number", b.getText());
        startActivity(myIntent);
    }

    public void onClickChangePinPreset(View v) {
        Button b = (Button) v;
        Intent myIntent = new Intent(ChangePresets.this, ChangePinPresets.class);
        myIntent.putExtra("pin number", b.getText());
        myIntent.putExtra("pin", pin);
        startActivity(myIntent);
    }
}
