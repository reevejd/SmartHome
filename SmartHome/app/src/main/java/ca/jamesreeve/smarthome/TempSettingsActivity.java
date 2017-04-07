package ca.jamesreeve.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TempSettingsActivity extends AppCompatActivity {

    TimePicker tp;
    Switch s;
    EditText tv;
    TimePicker.OnTimeChangedListener timePickerListener;
    Switch.OnCheckedChangeListener switchListener;
    TextWatcher textWatcher;
    String recentVal;

    FirebaseDatabase database;
    DatabaseReference tempSettingsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_settings);

        s = (Switch) findViewById(R.id.active);
        tp = (TimePicker) findViewById(R.id.unlockTime);
        tv = (EditText) findViewById(R.id.tempValue);
        tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {

                    String text = tv.getText().toString();

                    Double value = Double.parseDouble(recentVal);
                    if (!text.toString().isEmpty()) {
                        value = Double.parseDouble(text);
                    }

                    if (text.isEmpty()) {
                        tv.setText(String.valueOf(recentVal));
                        Toast.makeText(getApplicationContext(), "If you wish to disable this feature, use the \"active\" switch below", Toast.LENGTH_SHORT).show();
                    } else if (value > 30.0) {
                        Toast.makeText(getApplicationContext(), "The maximum allowed value is 30.0 degrees", Toast.LENGTH_SHORT).show();
                        value = 30.0;
                        tv.setText(String.valueOf(value));
                    } else if (value < 0.0) {
                        Toast.makeText(getApplicationContext(), "The minimum allowed value is 0.0 degrees", Toast.LENGTH_SHORT).show();

                        value = 0.0;
                    }

                    Float floatvalue = value.floatValue();

                    tempSettingsRef.setValue(new TempSettingsHelper(
                            s.isChecked(),
                            tp.getCurrentHour(),
                            tp.getCurrentMinute(),
                            floatvalue
                    ));
                }

            }
        });






        database = FirebaseDatabase.getInstance();
        tempSettingsRef = database.getReference("tempsettings");
        tempSettingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("db", "dbok in tempsettings.java");
                tp.setOnTimeChangedListener(null);
                s.setOnCheckedChangeListener(null);

                tp.setCurrentHour(((Number) dataSnapshot.child("changeH").getValue()).intValue());
                tp.setCurrentMinute(((Number) dataSnapshot.child("changeM").getValue()).intValue());
                s.setChecked((boolean) dataSnapshot.child("active").getValue());
                recentVal = String.format("%.1f", ((Number) dataSnapshot.child("temp").getValue()).doubleValue());
                tv.setText(recentVal);

                //add listener to text fiedl
                tp.setOnTimeChangedListener(timePickerListener);
                s.setOnCheckedChangeListener(switchListener);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("db",databaseError.toString());

            }
        });

        timePickerListener = new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.d("deb","activity.updatesettings");

                tempSettingsRef.setValue(new TempSettingsHelper(
                        s.isChecked(),
                        tp.getCurrentHour(),
                        tp.getCurrentMinute(),
                        Float.parseFloat(tv.getText().toString())
                ));
            }

        };

        switchListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("deb","activity.updatesettings");

                tempSettingsRef.setValue(new TempSettingsHelper(
                        s.isChecked(),
                        tp.getCurrentHour(),
                        tp.getCurrentMinute(),
                        Float.parseFloat(tv.getText().toString())
                ));
            }
        };

        tp.setOnTimeChangedListener(timePickerListener);
        s.setOnCheckedChangeListener(switchListener);



    }
}
