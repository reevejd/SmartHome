package ca.jamesreeve.smarthome;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

/**
 * Created by James Reeve on 4/4/2017.
 */

public class EmergencyState extends Observable {

    FirebaseDatabase database;
    DatabaseReference emergencyStateRef, emergencyCountdownRef;

    private Integer emergencyCountdown;
    private Boolean active;
    private Boolean simulationActive;

    public EmergencyState() {
        simulationActive = false;
        database = FirebaseDatabase.getInstance();
        emergencyStateRef = database.getReference("emergency/active");
        emergencyCountdownRef = database.getReference("emergency/countdown");

        initialDeactivateReplaceThisFunctionLater();

        emergencyStateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("emergency", "state listener function called");
                //acknowledged = false;
                active = ((Boolean) dataSnapshot.getValue());
                if (active) {
                    Log.d("emergency", "state is active");
                    setChanged();
                    notifyObservers("start");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        emergencyCountdownRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("emergency", "countdown listener function called");

                emergencyCountdown = ((Number) dataSnapshot.getValue()).intValue();
                setChanged();
                notifyObservers("countdown");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void initialDeactivateReplaceThisFunctionLater() {
        active = false;
        emergencyStateRef.setValue(false);
    }

    public void activate() {
        simulationActive = true;
        active = true;
        emergencyStateRef.setValue(true);
    }

   /* public void acknowledgeAlarm() {
        acknowledged = true;
    };*/

    public int getEmergencyCountdown() {
        return emergencyCountdown;
    }

    public boolean isActive() {
        return active;
    }

    public boolean simulationActive() {
        return simulationActive;
    };

    public void deactivateSimulation() {
        simulationActive = false;
    }

    public void setInactive() {
        active = false;
    };

    public void decrementTimer() {
        emergencyCountdown--;
    }

    /*public boolean isAcknowledged() {
        return acknowledged;
    }*/
}
