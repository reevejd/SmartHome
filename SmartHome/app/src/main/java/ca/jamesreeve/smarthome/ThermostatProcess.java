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

    private Double target;
    public Temperature temperature;
    volatile boolean on;
    volatile boolean justSet;

    FirebaseDatabase database;
    DatabaseReference targetRef;

    public ThermostatProcess(Temperature temperature) {
        on = false;
        justSet = false;
        this.temperature = temperature;
        database = FirebaseDatabase.getInstance();
        targetRef = database.getReference("targettemperature");

        targetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double newTarget = ((Number) dataSnapshot.getValue()).doubleValue();
                if (newTarget != target) {
                    on = false;
                }
                if (!justSet) {
                    //on = false;
                } else {
                    target = ((Number) dataSnapshot.getValue()).doubleValue();
                    justSet = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setTarget(Double target) {
        Log.d("thermostat", "entered settarget");
        on = true;
        Log.d("thermostat", "on : " + on);
        justSet = true;
        targetRef.setValue(target);
    }



    public void run() {
        while (true) {
            Log.d("thermostat", "thread looping");
            Log.d("thermostat", "on : " + on);
            Log.d("thermostat", "temperature : " + temperature.getValue());


            while (on) {
                Log.d("temp", "looping while on");

                while (target != temperature.getValue()) {
                    if (Math.abs(target - temperature.getValue()) < 1) {
                        temperature.setValue(target);
                        return;
                    }
                    double change = (target > temperature.getValue() ? 0.5 : -0.5);
                    temperature.setValue(temperature.getValue()+change);
                    try {
                        Thread.sleep(500);
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

/*
public class ThermostatProcess extends AsyncTask<String, String, String> {
    Temperature temperature;
    double target;
    FirebaseDatabase database;
    DatabaseReference targetRef;
    boolean shouldTerminate;
    boolean justSet;
    Thread sim;

    ThermostatProcess(Temperature temperature, double target) {
        Log.d("thermostat", "enteredconstructor");
        this.temperature = temperature;
        database = FirebaseDatabase.getInstance();
        targetRef = database.getReference("targettemperature");
        this.target = target;

        shouldTerminate = false;
        justSet = true;
        sim = new Thread(new SimulateThermostat());
    }

/*
    public void run() {
        while (target != temperature.getValue()) {
            if (Math.abs(target - temperature.getValue()) < 1) {
                temperature.setValue(target);
                return;
            }
            double change = (target > temperature.getValue() ? 1.0 : -1.0);
            temperature.setValue(temperature.getValue()+change);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    protected String doInBackground(String... params) {
        targetRef.addValueEventListener(new ValueEventListener() {
            @Override


            public void onDataChange(DataSnapshot dataSnapshot) {
                if (justSet) {
                    Log.d("thermostat", "starting");
                    sim.start();
                    justSet = false;
                } else {
                    Log.d("thermostat", "interrupting");
                    sim.interrupt();
                }


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        targetRef.setValue(target);
        return "0";

    };

    class SimulateThermostat implements Runnable {

        SimulateThermostat() {

        }

        public void run() {
            while (target != temperature.getValue()) {
                if (Math.abs(target - temperature.getValue()) < 1) {
                    temperature.setValue(target);
                    return;
                }
                double change = (target > temperature.getValue() ? 1.0 : -1.0);
                temperature.setValue(temperature.getValue()+change);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
*/
}