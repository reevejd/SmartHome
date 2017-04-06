package ca.jamesreeve.smarthome;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by user on 2017-04-05.
 */

public class TemperatureSettingsActivity extends AppCompatActivity {

    SeekBar sb;
    TextView valuetxt;
    FirebaseDatabase database;
    DatabaseReference setpointRef;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_settings);


        sb = (SeekBar) findViewById(R.id.seekBar);
        sb.incrementProgressBy(1);
        valuetxt = (TextView) findViewById(R.id.value);



        database = FirebaseDatabase.getInstance();
        setpointRef = database.getReference("targettemperature");
        setpointRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double target = ((Number) dataSnapshot.getValue()).doubleValue();
                sb.incrementProgressBy(1);
                sb.setProgress((int) (target*10));
                valuetxt.setText(String.valueOf(target));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // firebase get single value here


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                Log.d("val","temp");
                Double d_progress = ((Number) progress).doubleValue() /10;
                //progress = progress;
                valuetxt.setText(String.valueOf(d_progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sb) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("setpoint", valuetxt.getText());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("setpoint", valuetxt.getText());
                setResult(RESULT_OK, intent);
                finish();
                //NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
