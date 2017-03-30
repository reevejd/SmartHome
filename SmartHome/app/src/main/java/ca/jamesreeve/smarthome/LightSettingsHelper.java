package ca.jamesreeve.smarthome;

/**
 * Created by james on 2017-03-30.
 */

public class LightSettingsHelper {

    public int onH, onM, offH, offM;
    public boolean active;
    public LightSettingsHelper(){};

    public LightSettingsHelper(boolean active, int onH,int onM,int offH,int offM) {
        this.onH = onH; this.onM = onM; this.offH = offH; this.offM = offM; this.active = active;
    }

}
