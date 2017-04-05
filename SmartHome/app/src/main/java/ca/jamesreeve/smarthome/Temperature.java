package ca.jamesreeve.smarthome;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

/**
 * Created by james on 2017-03-31.
 */

public class Temperature extends Observable {

    FirebaseDatabase database;
    DatabaseReference tempRef;
    DatabaseReference targetRef;

    private double value;
    private double target;
    private volatile boolean simulationStarting;
    private volatile boolean simulationActive;

    public Temperature() {

        database = FirebaseDatabase.getInstance();

        simulationActive = false;

        targetRef = database.getReference("targettemperature");
        targetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("temp","target change in temperature.java");

                if (target == ((Number) dataSnapshot.getValue()).doubleValue()) {return;}; // trying to fix issues with onDataChange being called multiple times
                target = ((Number) dataSnapshot.getValue()).doubleValue();

                if (simulationStarting) {
                    simulationStarting = false;
                    simulationActive = true;
                    Log.d("thermostat", "simulation active");
                } else {
                    simulationActive = false;
                    Log.d("thermostat", "simulation stopped");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        tempRef = database.getReference("temperature");
        tempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("thermostat", "setting in entity");


                value = ((Number) dataSnapshot.getValue()).doubleValue();
                setChanged();
                notifyObservers();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("dberror", "db error in Temperature.java");
            }
        });
    };

    public void setTarget(double target) {
        simulationStarting = true;
        Log.d("thermostat", "simulation starting");
        //this.target = target;
        targetRef.setValue(target);
    };

    public double getTarget(){ return target;};

    public boolean getSimulationActive() {
        return simulationActive;
    };

    public void setSimulationActive(boolean simulationActive) {
        this.simulationActive = simulationActive;
    };

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        tempRef.setValue(value);
    };
}
