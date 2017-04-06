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

    }

    public void onClickEnterMsg(View v) {
        message = message_input.getText().toString();
        Intent myIntent = new Intent(change_message.this, MainActivity.class);
        //myIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        myIntent.putExtra("message", message);
        startActivity(myIntent);
    }

}
