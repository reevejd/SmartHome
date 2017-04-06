package ca.jamesreeve.smarthome;

/**
 * Created by james on 2017-03-30.
 */

public class TempSettingsHelper {

    public int changeH, changeM;
    public boolean active;
    public float temp;
    public TempSettingsHelper(){};

    public TempSettingsHelper(boolean active, int changeH, int changeM, float temp) {
        this.changeH = changeH; this.changeM = changeM; this.active = active; this.temp = temp;
    }

}
