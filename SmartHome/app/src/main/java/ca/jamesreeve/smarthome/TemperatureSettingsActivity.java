package ca.jamesreeve.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by user on 2017-04-05.
 */

public class TemperatureSettingsActivity extends AppCompatActivity {

    SeekBar sb;
    TextView valuetxt;

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_settings);


        /*sb = (SeekBar) findViewById(R.id.seekBar);
        valuetxt = (TextView) findViewById(R.id.value);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                Log.d("val","temp");
                valuetxt.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sb) {

            }
        });*/
    }


}
