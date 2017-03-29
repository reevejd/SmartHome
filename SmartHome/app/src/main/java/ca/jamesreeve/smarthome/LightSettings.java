package ca.jamesreeve.smarthome;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Nick on 3/29/2017.
 */

public class LightSettings {

    boolean active;
    int onH = 0;
    int onM = 0;
    int offH = 0;
    int offM = 0;

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

    public int[] getOnTime(){
        int[] onTime = new int[2];
        onTime[0] = onH;
        onTime[1] = onM;
        return onTime;
    }

    public int[] getOffTime(){
        int[] offTime = new int[2];
        offTime[0] = offH;
        offTime[1] = offM;
        return offTime;
    }
}
