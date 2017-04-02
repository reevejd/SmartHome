package ca.jamesreeve.smarthome;

/**
 * Created by james on 2017-03-30.
 */

public class DoorSettingsHelper {

    public int lockH, lockM, unlockH, unlockM;
    public boolean active;
    public DoorSettingsHelper(){};

    public DoorSettingsHelper(boolean active, int lockH, int lockM, int unlockH, int unlockM) {
        this.lockH = lockH; this.lockM = lockM; this.unlockH = unlockH; this.unlockM = unlockM; this.active = active;
    }

}
