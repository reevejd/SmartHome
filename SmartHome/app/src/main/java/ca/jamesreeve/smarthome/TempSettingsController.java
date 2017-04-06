package ca.jamesreeve.smarthome;

/**
 * Created by nicol on 2017-03-31.
 */

public class TempSettingsController {

    static TempSettings tempSettings;

    static final TempSettingsController buildTempSettingsController() {
        TempSettingsController result = new TempSettingsController();
        return result;
    };

    private TempSettingsController(){tempSettings = new TempSettings();}

    public void deactivate(){
        tempSettings.deactivate();
    }

    public boolean isActive(){
        return tempSettings.isActive();
    }

    public int[] getChangeTime(){
        return tempSettings.getChangeTime();
    }

    public float getTemp(){ return tempSettings.getTemp();}
}
