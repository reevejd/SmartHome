package ca.jamesreeve.smarthome;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by james on 2017-03-31.
 */



public class ThermostatProcess implements Runnable {


    public Temperature temperature;

    public ThermostatProcess(Temperature temperature) {
        this.temperature = temperature;
    }



    public void run() {
        while (true) {
            if (temperature.getSimulationActive()) {
                Log.d("thermostat", "temp simulation activate");
                Log.d("thermostat", "temperature : " + temperature.getValue());
                if (Math.abs(temperature.getTarget()-temperature.getValue()) < 1) {
                    temperature.setValue(temperature.getTarget());
                    temperature.setSimulationActive(false);
                } else {
                    double change = (temperature.getTarget() > temperature.getValue() ? 0.1 : -0.1);
                    temperature.setValue(temperature.getValue()+change);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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