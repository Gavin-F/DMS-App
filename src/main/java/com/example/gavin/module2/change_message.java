package com.example.gavin.module2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;

/**
 * Created by Gavin on 2017-03-15.
 */

public class change_message extends AppCompatActivity {
    private TextView entered_message;
    private EditText message_input;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_message);
        entered_message = (TextView)findViewById(R.id.message_input);
        message_input = (EditText) findViewById(R.id.message_input);

        message_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    message = message_input.getText().toString();
                    Intent myIntent = new Intent(change_message.this, MainActivity.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    myIntent.putExtra("message", message);
                    startActivity(myIntent);
                }
                return false;
            }
        });
    }

//    public void onClickEnter(View v) {
//        Button b = (Button) v;
//        if (display_entered_pin.equalsIgnoreCase(pin)) {
//            // pin is correct
//            Intent myIntent = new Intent(change_pin.this, new_pin.class);
//            myIntent.putExtra("old pin", pin); //Optional parameters
//            startActivity(myIntent);
//        }
//        else {
//            Log.d("wrong pin", display_entered_pin);
//            display_wrong_pin = "Incorrect Pin";
//            updatePin();
//        }
//    }
}
