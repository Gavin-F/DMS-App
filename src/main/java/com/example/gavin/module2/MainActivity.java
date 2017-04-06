package com.example.gavin.module2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.gavin.module2.R.id.status_bar_latest_event_content;
import static com.example.gavin.module2.R.id.text;
import static com.example.gavin.module2.R.id.time;

public class MainActivity extends AppCompatActivity {
    private TextView current_message; // full message
    private TextView current_pin; // 1,2,3,4, 0 for custom
    private TextView lock_status; //
    private TextView door_status;
    public static String pin = "1111";
    String new_pin = "";
    String message = "";
    String new_message = "";
    String which = "";
    String time_preset = "";
    String number = "";
    String preset_changed = "";
    int duration = Toast.LENGTH_SHORT;
    CharSequence toast_text;

    Button pin1;
    Button pin2;
    Button pin3;
    Button pin4;
    Button pinC;
    Button message1;
    Button message2;
    Button message3;
    Button message4;
    Button messageC;
    Button lock_unlock;
    Button doorbell_1;
    Button doorbell_2;
    Button set;
    Button set_with_time;
    Button change_presets;
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        current_message = (TextView)findViewById(R.id.current_message);
        current_pin = (TextView)findViewById(R.id.current_pin);
        lock_status = (TextView)findViewById(R.id.lock_status);
        door_status = (TextView)findViewById(R.id.door_status);

        pin1 = (Button)findViewById(R.id.pin_button1);
        pin2 = (Button)findViewById(R.id.pin_button2);
        pin3 = (Button)findViewById(R.id.pin_button3);
        pin4 = (Button)findViewById(R.id.pin_button4);
        pinC = (Button)findViewById(R.id.pin_buttonc);
        message1 = (Button)findViewById(R.id.message_button1);
        message2 = (Button)findViewById(R.id.message_button2);
        message3 = (Button)findViewById(R.id.message_button3);
        message4 = (Button)findViewById(R.id.message_button4);
        messageC = (Button)findViewById(R.id.message_buttonc);
        lock_unlock = (Button)findViewById(R.id.lock_unlock_button);
        doorbell_1 = (Button)findViewById(R.id.doorbell1);
        doorbell_2 = (Button)findViewById(R.id.doorbell2);
        set = (Button)findViewById(R.id.set_button);
        set_with_time = (Button)findViewById(R.id.set_with_time_button);
        change_presets = (Button)findViewById(R.id.change_preset_button);

        set.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        set_with_time.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        change_presets.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            new_pin = extras.getString("new pin");
            new_message = extras.getString("message");
            preset_changed = extras.getString("preset changed");
            number = extras.getString("number");

            which = extras.getString("which");
            time_preset = extras.getString("preset time");
            if (which != null && time_preset != null) {
                if (which.equalsIgnoreCase("unlock") || which.equalsIgnoreCase("lock")) {
                    toast_text = "Door will " + which + " at " + time_preset;
                    Toast toast = Toast.makeText(MainActivity.this, toast_text, duration);
                    toast.show();
                }
                else {
                    toast_text = which + " will change at " + time_preset;
                    Toast toast = Toast.makeText(MainActivity.this, toast_text, duration);
                    toast.show();
                }
            }
            else if (new_pin != null) {
                pin = new_pin;
                Log.d("new pin", new_pin);
                toast_text = "Pin Changed";
                Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
                toast.show();
                bluetooth_activity.WriteToBTDeviceAsync("p" + new_pin);
            }
            else if (new_message != null) {
                message = new_message;
                Log.d("new message", message);
                toast_text = "Message Changed";
                Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
                toast.show();
                bluetooth_activity.WriteToBTDeviceAsync("m" + new_message);
            }
            else if (number != null && preset_changed != null) {
                toast_text = preset_changed + " preset " + number + " has been changed";
                Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
                toast.show();
            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        bluetooth_activity.WriteToBTDeviceAsync("a");
        new ReadInitialDataAsync(current_message, current_pin, lock_status, door_status, lock_unlock).execute();
        // write asynchronous task to read data
        // parse the data properly and set to the appropriate textviews
//        if (lock_status.getText().toString().equalsIgnoreCase("locked")) {
//            lock_unlock.setText("Unlock");
//        }
//        else
//            lock_unlock.setText("Lock");
    }

    public void onClickChangePresets (View v) {
        Intent myIntent = new Intent(MainActivity.this, ChangePresets.class);
        myIntent.putExtra("pin",pin);
        startActivity(myIntent);
    }

    public void onClickHighlight(View v) {
        Button b = (Button) v;
        resetButtons();
        b.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        selected = b.getId();
    }

    public void resetButtons() {
        pin1.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        pin2.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        pin3.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        pin4.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        pinC.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);

        message1.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        message2.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        message3.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        message4.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        messageC.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);

        lock_unlock.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);

        doorbell_1.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        doorbell_2.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
    }

    public void onClickSet(View v) {
        if (selected == R.id.pin_button1) {
            bluetooth_activity.WriteToBTDeviceAsync("t1");
            toast_text = "Pin Changed";
            Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
            toast.show();
        }
        else if (selected == R.id.pin_button2) {
            bluetooth_activity.WriteToBTDeviceAsync("t2");
            toast_text = "Pin Changed";
            Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
            toast.show();
        }
        else if (selected == R.id.pin_button3) {
            bluetooth_activity.WriteToBTDeviceAsync("t3");
            toast_text = "Pin Changed";
            Toast toast = Toast.makeText(MainActivity.this, toast_text, duration);
            toast.show();
        }
        else if (selected == R.id.pin_button4) {
            bluetooth_activity.WriteToBTDeviceAsync("t4");
            toast_text = "Pin Changed";
            Toast toast = Toast.makeText(MainActivity.this, toast_text, duration);
            toast.show();
        }
        else if (selected == R.id.pin_buttonc) {
            Intent myIntent = new Intent(MainActivity.this, change_pin.class);
            myIntent.putExtra("pin",pin);
            startActivity(myIntent);
        }
        else if (selected == R.id.message_button1) {
            bluetooth_activity.WriteToBTDeviceAsync("s1");
            toast_text = "Message Changed";
            Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
            toast.show();
        }
        else if (selected == R.id.message_button2) {
            bluetooth_activity.WriteToBTDeviceAsync("s2");
            toast_text = "Message Changed";
            Toast toast = Toast.makeText(MainActivity.this, toast_text, duration);
            toast.show();
        }
        else if (selected == R.id.message_button3) {
            bluetooth_activity.WriteToBTDeviceAsync("s3");
            toast_text = "Message Changed";
            Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
            toast.show();
        }
        else if (selected == R.id.message_button4) {
            bluetooth_activity.WriteToBTDeviceAsync("s4");
            toast_text = "Message Changed";
            Toast toast = Toast.makeText(MainActivity.this, toast_text, duration);
            toast.show();
        }
        else if (selected == R.id.message_buttonc) {
            Intent myIntent = new Intent(MainActivity.this, change_message.class);
            startActivity(myIntent);
        }
        else if (selected == R.id.lock_unlock_button) {
            if (lock_unlock.getText().toString().equalsIgnoreCase("lock")) {
                Log.d("lock", "before1");
                lock_unlock.setText("Unlock");
                toast_text = "Door Locked";
                Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
                toast.show();
                Log.d("lock", "before");
                bluetooth_activity.WriteToBTDeviceAsync("l");
                Log.d("lock", "after");
            }
            else if (lock_unlock.getText().toString().equalsIgnoreCase("unlock")) {
                lock_unlock.setText("Lock");
                toast_text = "Door Unlocked";
                Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
                toast.show();
                bluetooth_activity.WriteToBTDeviceAsync("u");
            }
        }
        else if (selected == R.id.doorbell1) {
            bluetooth_activity.WriteToBTDeviceAsync("d1");
            toast_text = "Doorbell Changed";
            Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
            toast.show();
        }
        else if (selected == R.id.doorbell2) {
            bluetooth_activity.WriteToBTDeviceAsync("d2");
            toast_text = "Doorbell Changed";
            Toast toast = Toast.makeText(MainActivity.this,toast_text, duration);
            toast.show();
        }
        updateData();
    }

    public void onClickSetWithTime(View v) {
        String key = "";
        if (selected == R.id.pin_button1) {
            key = "pin1";
            Intent myIntent = new Intent(MainActivity.this, Scheduling.class);
            myIntent.putExtra("pin",pin);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.pin_button2) {
            key = "pin2";
            Intent myIntent = new Intent(MainActivity.this, Scheduling.class);
            myIntent.putExtra("pin",pin);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.pin_button3) {
            key = "pin3";
            Intent myIntent = new Intent(MainActivity.this, Scheduling.class);
            myIntent.putExtra("pin",pin);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.pin_button4) {
            key = "pin4";
            Intent myIntent = new Intent(MainActivity.this, Scheduling.class);
            myIntent.putExtra("pin",pin);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.pin_buttonc) {
            key = "pinC";
            Intent myIntent = new Intent(MainActivity.this, change_pin.class);
            myIntent.putExtra("pin",pin);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.message_button1) {
            key = "message1";
            Intent myIntent = new Intent(MainActivity.this, Scheduling.class);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.message_button2) {
            key = "message2";
            Intent myIntent = new Intent(MainActivity.this, Scheduling.class);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.message_button3) {
            key = "message3";
            Intent myIntent = new Intent(MainActivity.this, Scheduling.class);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.message_button4) {
            key = "message4";
            Intent myIntent = new Intent(MainActivity.this, Scheduling.class);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.message_buttonc) {
            key = "messageC";
            Intent myIntent = new Intent(MainActivity.this, SchedulingMessage.class);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
        else if (selected == R.id.lock_unlock_button) {
            key = lock_unlock.getText().toString();
            Intent myIntent = new Intent(MainActivity.this, Scheduling.class);
            myIntent.putExtra("key",key);
            startActivity(myIntent);
        }
    }

    public void updateData() {
        bluetooth_activity.WriteToBTDeviceAsync("a");
        new ReadInitialDataAsync(current_message, current_pin, lock_status, door_status, lock_unlock).execute();
//        if (lock_status.getText().toString().equalsIgnoreCase("locked")) {
//            lock_unlock.setText("Unlock");
//        }
//        else
//            lock_unlock.setText("Lock");
    }

}
