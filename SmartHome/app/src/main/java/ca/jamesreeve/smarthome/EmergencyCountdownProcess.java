package ca.jamesreeve.smarthome;

import android.util.Log;

/**
 * Created by james on 2017-04-05.
 */

public class EmergencyCountdownProcess implements Runnable {

    public EmergencyState emergencyState;

    public EmergencyCountdownProcess(EmergencyState emergencyState) {
        this.emergencyState = emergencyState;
    }

    public void run() {
        while (true) {
            if (emergencyState.simulationActive()) {
                Log.d("timer", ""+ emergencyState.getEmergencyCountdown());
                if (emergencyState.getEmergencyCountdown() == 0) {
                    emergencyState.deactivateSimulation();
                    emergencyState.setInactive();
                } else {
                    emergencyState.decrementTimer();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
