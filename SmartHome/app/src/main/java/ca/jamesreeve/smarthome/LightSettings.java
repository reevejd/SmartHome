package ca.jamesreeve.smarthome;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.sql.Time;
import java.util.Observable;

/**
 * Created by Nick on 3/29/2017.
 */

public class LightSettings {

    FirebaseDatabase database;
    DatabaseReference lightSettingsRef;

    boolean active;
    int onH;
    int onM;
    int offH;
    int offM;

    public LightSettings(){
        database = FirebaseDatabase.getInstance();
        lightSettingsRef = database.getReference("lightsettings");

        lightSettingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("db", "dbok in lightsettings.java");
                active = ((boolean) dataSnapshot.child("active").getValue());
                onH = ((Number) dataSnapshot.child("onH").getValue()).intValue();
                onM = ((Number) dataSnapshot.child("onM").getValue()).intValue();
                offH = ((Number) dataSnapshot.child("offH").getValue()).intValue();
                offM = ((Number) dataSnapshot.child("offM").getValue()).intValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("db", "dberror in lightsettings.java");
                Log.e("db",databaseError.toString());
                active = false;
            }
        });

    }

    public void deactivate(){
        active = false;
    }

    public boolean isActive(){
        return active;
    }

    public int[] getOnTime(){
        int[] onTime = new int[2];
        onTime[0] = onH;
        onTime[1] = onM;
        return onTime;
    }

    public int[] getOffTime(){
        int[] offTime = new int[2];
        offTime[0] = offH;
        offTime[1] = offM;
        return offTime;
    }
}
