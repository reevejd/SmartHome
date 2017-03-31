package ca.jamesreeve.smarthome;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by james on 2017-03-31.
 */

public class TemperatureController implements Observer{

    static Temperature temperature;
    MainActivity viewController;

    static final TemperatureController buildTemperatureController(MainActivity viewController) {
        TemperatureController result = new TemperatureController(viewController);

        temperature.addObserver(result);
        return result;

    };

    private TemperatureController(MainActivity viewController) {
        temperature = new Temperature();
        this.viewController = viewController;
        this.temperature = temperature;
    }

    public void update(Observable temperature, Object arg) {
        Log.d("temp", "update function called within temperaturecontroller");
        // update temp display
    }

    public void setThermostat(double target) {
        // logic here to gradually move towards target value
        // need some delays here
        while (target != temperature.getValue()) {
            if (target > temperature.getValue()) {
                temperature.setValue(temperature.getValue()+1);
            } else {
                temperature.setValue(temperature.getValue()-1);
            }

        };
    }

}
