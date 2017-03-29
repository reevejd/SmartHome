package ca.jamesreeve.smarthome;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Nick on 3/29/2017.
 */

public class LightSettings {

    boolean active;
    int onH;
    int onM;
    int offH;
    int offM;

    public LightSettings(){
        active = false;
    }

    public void updateSettings(int onH,int onM,int offH,int offM){
        this.onH = onH;
        this.onM = onM;
        this.offH = offH;
        this.offM = offM;
        active = true;
    }

    public void deactivate(){
        active = false;
    }

    public boolean isActive(){
        return active;
    }
}
