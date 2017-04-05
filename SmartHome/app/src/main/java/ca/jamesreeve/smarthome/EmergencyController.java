package ca.jamesreeve.smarthome;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by James Reeve on 4/4/2017.
 */

public class EmergencyController implements Observer{

    MainActivity viewController;
    static EmergencyState emergencyState;

    public static EmergencyController buildEmergencyController(MainActivity viewController) {
        EmergencyController result = new EmergencyController(viewController);
        emergencyState.addObserver(result);
        return result;

    }

    private EmergencyController(MainActivity viewController) {
        this.viewController = viewController;
        emergencyState = new EmergencyState();
    }

    public void beginEmergency() {
        if (emergencyState.isActive()) {
            // already active
            viewController.displayRepeatedEmergency();
        } else {
            emergencyState.activate();
        }

    }

    public void update(Observable obs, Object arg) {
        Log.d("emergency", "update function called, arg: " + arg);
        EmergencyState emerg = (EmergencyState) obs;
        if (arg.equals("start")) {
            viewController.displayEmergency();
        } else if (arg.equals("countdown")) {
            viewController.updateEmergencySeconds();
        }
    }
}
