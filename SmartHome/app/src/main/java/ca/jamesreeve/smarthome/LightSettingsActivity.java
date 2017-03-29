package ca.jamesreeve.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TimePicker;

public class LightSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_settings);
        Switch s = (Switch) findViewById(R.id.active);
        if(MainActivity.lightSettingsController.isActive()){
            s.setChecked(true);
        }
        else{
            s.setChecked(false);
        }
    }

    public void onClick(View v){
        Switch s = (Switch) findViewById(R.id.active);
        TimePicker tp = (TimePicker) findViewById(R.id.turnOnTime);
        TimePicker tp2 = (TimePicker) findViewById(R.id.turnOffTime);
        if(s.isChecked()){
            //save settings are active
            int onHour = tp.getCurrentHour();
            int onMinute = tp.getCurrentMinute();
            int offHour = tp.getCurrentHour();
            int offMinute = tp.getCurrentMinute();
            MainActivity.lightSettingsController.updateSettings(onHour,onMinute,offHour,offMinute);
        }
        else{
            MainActivity.lightSettingsController.deactivate();
        }
        finish();
    }
}
