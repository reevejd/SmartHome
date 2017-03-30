package ca.jamesreeve.smarthome;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 3/29/2017.
 */

public class LightSettingsController {

    static LightSettings lightSettings;


    static final LightSettingsController buildLightSettingsController() {
        LightSettingsController result = new LightSettingsController();
        return result;
    };

    private LightSettingsController(){
        lightSettings = new LightSettings();
    }

    public void deactivate(){
        lightSettings.deactivate();
    }

    public boolean isActive(){
        return lightSettings.isActive();
    }

    public int[] getOnTime(){
        return lightSettings.getOnTime();
    }

    public int[] getOffTime(){
        return lightSettings.getOffTime();
    }

}
