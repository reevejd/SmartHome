package ca.jamesreeve.smarthome;

/**
 * Created by Nick on 3/29/2017.
 */

public class LightSettingsController {

    LightSettings lightSettings;

    public LightSettingsController(){
        lightSettings = new LightSettings();
    }

    public void updateSettings(int onH,int onM,int offH,int offM){
        lightSettings.updateSettings(onH,onM,offH,offM);
    }

    public void deactivate(){
        lightSettings.deactivate();
    }

    public boolean isActive(){
        return lightSettings.isActive();
    }
}
