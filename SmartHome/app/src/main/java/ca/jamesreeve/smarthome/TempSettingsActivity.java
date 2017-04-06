package ca.jamesreeve.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

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
                    tempSettingsRef.setValue(new TempSettingsHelper(
                            s.isChecked(),
                            tp.getCurrentHour(),
                            tp.getCurrentMinute(),
                            Float.parseFloat(tv.getText().toString())
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
                tv.setText( dataSnapshot.child("temp").getValue().toString());

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
