package ca.jamesreeve.smarthome;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by james on 2017-03-31.
 */

public class TemperatureController implements Observer{

    static Temperature temperature;
    static ThermostatProcess thermostat;
    static Thread thermostatThread;
    MainActivity viewController;

    static final TemperatureController buildTemperatureController(MainActivity viewController) {
        TemperatureController result = new TemperatureController(viewController);
        temperature.addObserver(result);
        /*
        thermostat = new ThermostatProcess(temperature);
        thermostatThread = new Thread(thermostat);
        thermostatThread.start();
*/

        return result;


    };

    private TemperatureController(MainActivity viewController) {
        this.temperature = new Temperature();
        this.viewController = viewController;
    }

    public void update(Observable temperature, Object arg) {
        Log.d("temp", "update function called within temperaturecontroller");
        // update temp display
    }

    public void setThermostat(final double target) {
        // logic here to gradually move towards target value
        // need some delays here
        //temperature.setValue(75);
        Log.d("thermostat", "contval : "+temperature.getValue());
        thermostat.setTarget(target);
        //ThermostatProcess thermo = new ThermostatProcess(temperature, target);
        //thermo.execute();
    }
/*
        while (target != temperature.getValue()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if (Math.abs(target - temperature.getValue()) < 1) {
                        temperature.setValue(target);
                        return;
                    }
                    double change = (target > temperature.getValue() ? -1.0 : 1.0);
                    temperature.setValue(change);
                }
            }, 2000);
        }

    };
    */


}
