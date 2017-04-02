package ca.jamesreeve.smarthome;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by nicol on 2017-03-31.
 */

public class DoorSettings {

    FirebaseDatabase database;
    DatabaseReference doorSettingsRef;

    boolean active;
    int lockH;
    int lockM;
    int unlockH;
    int unlockM;

    public DoorSettings(){
        database = FirebaseDatabase.getInstance();
        doorSettingsRef = database.getReference("doorsettings");

        doorSettingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("db", "dbok in doorsettings.java");
                active = ((boolean) dataSnapshot.child("active").getValue());
                lockH = ((Number) dataSnapshot.child("lockH").getValue()).intValue();
                lockM = ((Number) dataSnapshot.child("lockM").getValue()).intValue();
                unlockH = ((Number) dataSnapshot.child("unlockH").getValue()).intValue();
                unlockM = ((Number) dataSnapshot.child("unlockM").getValue()).intValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("db", "dberror in doorsettings.java");
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

    public int[] getLockTime(){
        int[] lockTime = new int[2];
        lockTime[0] = lockH;
        lockTime[1] = lockM;
        return lockTime;
    }

    public int[] getUnlockTime(){
        int[] unlockTime = new int[2];
        unlockTime[0] = unlockH;
        unlockTime[1] = unlockM;
        return unlockTime;
    }

}
