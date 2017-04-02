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

    private double value;

    public Temperature() {

        database = FirebaseDatabase.getInstance();

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
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        tempRef.setValue(value);
    }
}
