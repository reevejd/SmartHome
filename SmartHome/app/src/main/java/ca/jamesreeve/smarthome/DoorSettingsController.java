package ca.jamesreeve.smarthome;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by nicol on 2017-03-31.
 */

public class DoorSettingsController {

    static DoorSettings doorSettings;

    static final DoorSettingsController buildDoorSettingsController() {
        DoorSettingsController result = new DoorSettingsController();
        return result;
    };

    private DoorSettingsController(){doorSettings = new DoorSettings();}

    public void deactivate(){
        doorSettings.deactivate();
    }

    public boolean isActive(){
        return doorSettings.isActive();
    }

    public int[] getOnTime(){
        return doorSettings.getLockTime();
    }

    public int[] getOffTime(){
        return doorSettings.getUnlockTime();
    }
}
